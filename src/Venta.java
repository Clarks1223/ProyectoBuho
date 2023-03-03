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
    static JFrame addproduct =  new JFrame("Venta");
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
                /*
                Connection cn;
                ConxBD con = new ConxBD();
                cn = con.estbConexion();
                String qr  = "select * from productos";
                Statement st;
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("codigo");
                model.addColumn("nombre");
                model.addColumn("precio");
                table1.setModel(model);
                String [] datos = new String[5];
                try {
                    st = cn.createStatement();
                    ResultSet rs =  st.executeQuery(qr);
                    while(rs.next()){
                        datos[0] = rs.getString(1);
                        datos[1] = rs.getString(2);
                        datos[2] = rs.getString(3);
                        model.addRow(datos);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                */

            }
        });
        ELIMINARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                Connection cn;
                ConxBD con = new ConxBD();
                try {
                    cn = con.estbConexion();
                    pd = cn.prepareStatement("DELETE FROM datosVehiculo WHERE matricula="+ TFgetCodElim);
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
                */
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
