import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Cajero extends ventanas{
    private JPanel PanelCajero;
    private JButton CERRARButton;
    private JComboBox comboBox1;
    private JTextField TFsetUnidades;
    private JButton buttonRegistrar;
    private JLabel JLid;
    private JLabel JLnombre;
    private JLabel JLgetNombre;
    private JLabel JLgetID;
    private JLabel JLnombreProd;
    private JLabel JTGetNombrePro;
    private JLabel JTgetPrecio;
    private JLabel JTgetUnidad;
    private JLabel JLtitulo2;
    private JButton BUSCARButton;
    PreparedStatement pd;

    static JFrame addcajero =  new JFrame("Cajero");

    public Cajero() {
        Connection cn;
        ConxBD con = new ConxBD();
        try {
            cn = con.estbConexion();
            Statement consultaPro = cn.createStatement();
            ResultSet resultadoPro = consultaPro.executeQuery("Select * from productos");
            while (resultadoPro.next()) {
                comboBox1.addItem(resultadoPro.getString(1));
            }
            cn.close();
        } catch (HeadlessException | SQLException f) {
            System.err.println(f);
        }
        CERRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login loginCaj = new Login();
                loginCaj.abrirVentana();
                addcajero.setVisible(false);
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection cx;
                ConxBD con = new ConxBD();
                try{
                    cx = con.estbConexion();
                    String qr = "select * from productos  where codProd = "+ comboBox1.getSelectedItem()+";";
                    Statement s = cx.createStatement();
                    ResultSet rs = s.executeQuery(qr);
                    System.out.println(rs);
                    while(rs.next()) {
                        JTgetPrecio.setText(rs.getString(2));
                        JTgetPrecio.setText(rs.getString(3));
                        JTgetUnidad.setText(rs.getString(4));
                    }
                    cx.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        BUSCARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection cx;
                ConxBD con = new ConxBD();
                try{
                    cx = con.estbConexion();
                    String qr = "select * from productos  where codProd = "+ comboBox1.getSelectedItem()+";";
                    Statement s = cx.createStatement();
                    ResultSet rs = s.executeQuery(qr);
                    System.out.println(rs);
                    while(rs.next()) {
                        JLnombreProd.setText(rs.getString(2));
                        JTgetUnidad.setText(rs.getString(3));
                        JTgetPrecio.setText(rs.getString(4));
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        buttonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection cn;
                ConxBD con = new ConxBD();
                int codFact = 0, numFact = 0 ;
                try{
                    cn = con.estbConexion();
                    String qr = "select max(codDet) from detalle_factura;";
                    Statement s = cn.createStatement();
                    ResultSet rs = s.executeQuery(qr);
                    System.out.println(rs);
                    while(rs.next()) {
                        codFact = Integer.parseInt(rs.getString(1));
                    }
                    codFact += 1;
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                try{
                    cn = con.estbConexion();
                    String qr = "select max(numFacFK) from detalle_factura;";
                    Statement s = cn.createStatement();
                    ResultSet rs = s.executeQuery(qr);
                    System.out.println(rs);
                    while(rs.next()) {
                        numFact = Integer.parseInt(rs.getString(1));
                    }
                    numFact += 1;
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    cn = con.estbConexion();
                    pd = cn.prepareStatement("Insert into detalle_factura VALUES (?,?,?,?,?,?)");
                    pd.setString(1, String.valueOf(codFact));
                    pd.setString(2, String.valueOf(numFact));
                    pd.setString(3, comboBox1.getSelectedItem().toString());
                    pd.setString(4, JLnombreProd.getText());
                    pd.setString(5, TFsetUnidades.getText());
                    pd.setString(6, JTgetPrecio.getText());
                    System.out.println(pd);
                    int res = pd.executeUpdate();
                    if (res > 0) {
                        JOptionPane.showMessageDialog(null,"Producto guardado con exito");
                    } else {
                        JOptionPane.showMessageDialog(null,"Error");
                    }
                    cn.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                //Abrir la ventana de ventas
                Venta ventLogin = new Venta();
                ventLogin.abrirVentana();
                addcajero.setVisible(false);
            }
        });
    }
    @Override
    public void abrirVentana(){
        addcajero.setContentPane(new Cajero().PanelCajero);
        addcajero.setDefaultCloseOperation(addcajero.EXIT_ON_CLOSE);
        addcajero.pack();
        addcajero.setLocationRelativeTo(null);
        addcajero.setResizable(false);
        addcajero.setVisible(true);
    }
    @Override
    public void cerrarVentana(){
        addcajero.setVisible(false);
    }
    public static void main(String[] args) {
        JFrame fr = new JFrame("Cajero");
        fr.setContentPane(new Cajero().PanelCajero);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.pack();
        fr.setVisible(true);
        fr.setResizable(false);
        fr.setLocationRelativeTo(null);
    }
}
