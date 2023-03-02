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
    private JLabel JLdescripcionProd;
    private JLabel JTsetDescripoPro;
    private JLabel JTsetPrecio;
    private JLabel JTsetUnidad;
    PreparedStatement pd;

    static JFrame addcajero =  new JFrame("Cajero");

    public Cajero() {
        Connection cn;
        ConxBD con = new ConxBD();
        try {
            cn = con.estbConexion();
            Statement consultaPro = cn.createStatement();
            ResultSet resultadoPro = consultaPro.executeQuery("Select * from anioD");
            while (resultadoPro.next()) {
                comboBox1.addItem(resultadoPro.getString(1));
            }
            cn.close();
        } catch (HeadlessException | SQLException f) {
            System.err.println(f);
        }
        buttonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Abrir la ventana de ventas
                Venta ventLogin = new Venta();
                ventLogin.abrirVentana();
                addcajero.setVisible(false);
            }
        });
        CERRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login loginCaj = new Login();
                loginCaj.abrirVentana();
                addcajero.setVisible(false);
            }
        });
        buttonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection cn;
                ConxBD con = new ConxBD();
                try {
                    cn = con.estbConexion();
                    pd = cn.prepareStatement("Insert into datosVehiculo VALUES (?,?,?,?,?,?)");
                    pd.setString(1, TFsetUnidades.getText());
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
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection cx;
                ConxBD con = new ConxBD();
                try{
                    cx = con.estbConexion();
                    String qr = "select * from datosVehiculo  where matricula = "+ comboBox1.getAction()+";";
                    Statement s = cx.createStatement();
                    ResultSet rs = s.executeQuery(qr);
                    System.out.println(rs);
                    while(rs.next()) {
                        JLdescripcionProd.setText(rs.getString(2));
                        JTsetPrecio.setText(rs.getString(3));
                        JTsetUnidad.setText(rs.getString(4));
                    }
                    cx.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
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
