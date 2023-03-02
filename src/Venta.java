import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Venta extends ventanas {
    private JPanel panel1;
    private JTable table1;
    private JButton ATRASButton;
    private JButton CERRARButton;
    private JButton button3;
    static JFrame addproduct =  new JFrame("Venta");
    String nombreTabla;

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
                String qr  = "select * from " + nombreTabla;
                Statement st;
                ConxBD con = new ConxBD();
                Connection conexion = con.estbConexion();
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("product1");
                model.addColumn("product2");
                model.addColumn("product3");
                model.addColumn("product4");
                model.addColumn("product5");
                table1.setModel(model);
                String [] datos = new String[5];
                try {
                    st = conexion.createStatement();
                    ResultSet re = st.executeQuery(qr);
                    while(re.next()){
                        datos[0] = re.getString(1);
                        datos[1] = re.getString(2);
                        datos[2] = re.getString(3);
                        datos[3] = re.getString(4);
                        datos[4] = re.getString(5);
                        model.addRow(datos);
                    }
                } catch (SQLException ex) {
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
