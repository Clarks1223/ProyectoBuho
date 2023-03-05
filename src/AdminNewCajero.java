import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminNewCajero extends ventanas{
    private JPanel JPadmpag4;
    private JButton JBcerrarsesion;
    private JButton JBatras;
    private JLabel JLnewempleado;
    private JLabel JLiconoempresa;
    private JButton JBborracampos;
    private JButton JBagregarempleado;
    private JTextField JTFcedula;
    private JTextField JTFnombre;
    private JTextField JTFdireccion;
    private JLabel JLtitulo;
    private JLabel JLTCedula;
    private JLabel JLNombre;
    private JLabel JLdireccion;
    private JLabel JLcelular;
    private JTextField JTFcelular;
    private JTextField JTFcontrasenia;
    private JLabel JLcontrasenia;
    //Variables globales
    static JFrame addcajero =  new JFrame("Nuevo Empleado");
    //Objeto para la contrasenia
    ConxBD conectarBD = new ConxBD();
    Connection con;

    public AdminNewCajero() {
        JBcerrarsesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login venLogin = new Login();
                venLogin.abrirVentana();
                cerrarVentana();
            }
        });
        JBatras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminPag1 back = new AdminPag1();
                back.abrirVentana();
                cerrarVentana();
            }
        });
        JBagregarempleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resultado=0;
                int resultado1=0;
                try {
                    PreparedStatement ps;
                    PreparedStatement ps1;
                    con=conectarBD.estbConexion();
                    ps = con.prepareStatement("Insert into datos_cajero values (?,?,?,?)");
                    ps.setString(1, JTFcedula.getText());
                    ps.setString(2, JTFnombre.getText());
                    ps.setString(3, JTFdireccion.getText());
                    ps.setString(4, JTFcelular.getText());
                    resultado=ps.executeUpdate();
                    ps1 = con.prepareStatement("Insert into login_cajero values (?,?)");
                    ps1.setString(1, JTFcedula.getText());
                    ps1.setString(2, JTFcontrasenia.getText());
                    resultado1=ps1.executeUpdate();
                    if (resultado>0 && resultado1>0){
                        JOptionPane.showMessageDialog(null,"Cajero agregado");
                    }else {
                        JOptionPane.showMessageDialog(null,"Cajero no agregado, Ya ha ingresado el numero de ceula");
                    }
                    con.close();
                }catch (HeadlessException | SQLException f){
                    System.err.println(f);
                }
            }
        });
        JBborracampos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTFcedula.setText("");
                JTFnombre.setText("");
                JTFdireccion.setText("");
                JTFcelular.setText("");
                JTFcontrasenia.setText("");
            }
        });
    }

    @Override
    public void abrirVentana(){
        addcajero.setContentPane(new AdminNewCajero().JPadmpag4);
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
}
