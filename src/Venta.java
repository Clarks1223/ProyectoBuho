import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.*;

public class Venta extends ventanas {
    private JPanel panel1;
    private JTable table1;
    private JButton ATRASButton;
    private JButton CERRARButton;
    private JButton ELIMINARButton;
    private JButton REGISTRARButton;
    private JTextField TFgetCodElim;
    private JLabel JLCodigoElim;
    private JLabel JLgetSub;
    private JLabel JLgetIva;
    private JLabel JLgetTotal;
    private JLabel JLgetNumFact;
    private JTable table2;
    static JFrame addproduct =  new JFrame("Venta");

    double subW = 1;

    PreparedStatement pd;

    public Venta() {
        ATRASButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cajero RegistroP = new Cajero();
                RegistroP.abrirVentana();
                addproduct.setVisible(false);
            }
        });
        CERRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login loginCaj = new Login();
                loginCaj.abrirVentana();
                addproduct.setVisible(false);
            }
        });
        table1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                double datos1[] = new double[30];
                Connection cn;
                ConxBD con = new ConxBD();
                cn = con.estbConexion();
                String qr  = "select * from detalle_factura";
                Statement st;
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("codDet");
                model.addColumn("numFacFK");
                model.addColumn("codProdFK");
                model.addColumn("nomProd");
                model.addColumn("cantProd");
                model.addColumn("preProd");
                table1.setModel(model);
                String [] datos = new String[10];
                try {
                    st = cn.createStatement();
                    ResultSet rs =  st.executeQuery(qr);
                    int j = 0;
                    while(rs.next()){
                        datos[0] = rs.getString(1);
                        datos[1] = rs.getString(2);
                        datos[2] = rs.getString(3);
                        datos[3] = rs.getString(4);
                        datos[4] = rs.getString(5);
                        datos[5] = rs.getString(6);
                        datos1[j] = Double.parseDouble(String.valueOf((datos[4])));
                        j++;
                        datos1[j] = Double.parseDouble(String.valueOf((datos[5])));
                        j++;
                        model.addRow(datos);
                    }
                    int i = 0;
                    while(i < j){
                        subW = datos1[i] * subW;
                        i++;
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        ELIMINARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection cn;
                ConxBD con = new ConxBD();
                try {
                    cn = con.estbConexion();
                    pd = cn.prepareStatement("DELETE FROM detalle_factura WHERE codProdFK="+ TFgetCodElim);
                    pd.executeUpdate();
                    int res = pd.executeUpdate();
                    if (res > 0) {
                        JOptionPane.showMessageDialog(null,"Error");
                    } else {
                        JOptionPane.showMessageDialog(null,"Coche eliminado con exito");
                    }
                    cn.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        REGISTRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection cn;
                ConxBD con = new ConxBD();
                double ivaW;
                double totW = 0;
                try {
                    cn = con.estbConexion();
                    String cod = "1121", codDet = "1220";
                    pd = cn.prepareStatement("Insert into total_factura VALUES (?,?,?,?,?)");
                    pd.setString(1, cod);
                    pd.setString(2, codDet);
                    pd.setString(3, String.valueOf(subW));
                    pd.setString(4, String.valueOf(ivaW = (subW * 0.12)));
                    pd.setString(5, String.valueOf(totW = ivaW + subW));
                    System.out.println(pd);
                    int res = pd.executeUpdate();
                    if (res > 0) {
                        JOptionPane.showMessageDialog(null,"Datos guardados con exito");
                    } else {
                        JOptionPane.showMessageDialog(null,"Error");
                    }
                    try{
                        cn = con.estbConexion();
                        String qr = "select * from total_factura  where codTot = "+ cod+";";
                        Statement s = cn.createStatement();
                        ResultSet rs = s.executeQuery(qr);
                        System.out.println(rs);
                        while(rs.next()) {
                            JLgetSub.setText(rs.getString(3));
                            JLgetIva.setText(rs.getString(4));
                            JLgetTotal.setText(rs.getString(5));
                        }
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    cn.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        JLgetNumFact.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                Connection cx;
                ConxBD con = new ConxBD();
                try{
                    cx = con.estbConexion();
                    String qr = "select * from total_factura ;";
                    Statement s = cx.createStatement();
                    ResultSet rs = s.executeQuery(qr);
                    System.out.println(rs);
                    while(rs.next()) {
                        JLgetNumFact.setText(rs.getString(1));
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    @Override
    public void abrirVentana(){
        addproduct.setContentPane(new Venta().panel1);
        addproduct.setDefaultCloseOperation(addproduct.EXIT_ON_CLOSE);
        addproduct.pack();
        addproduct.setLocationRelativeTo(null);
        addproduct.setResizable(false);
        addproduct.setVisible(true);
    }
    @Override
    public void cerrarVentana(){
        addproduct.setVisible(false);
    }
}
