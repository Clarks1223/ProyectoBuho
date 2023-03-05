import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class AdminPag1 extends ventanas{
    private JPanel JPadmpg1;
    private JButton JBagregarproducto;
    private JButton JBverventas;
    private JButton JBagregarempleado;
    private JLabel JLnombre;
    private JLabel JLcedula;
    private JLabel JLcargo;
    private JLabel JLsetnombre;
    private JLabel JLsetcedula;
    private JLabel JLsetcargo;
    private JButton JBbacklogin;
    private JLabel JLseticonoempresa;
    private JLabel JLsetimagenperfil;
    private JLabel JLfunciones;
    private JLabel JLbienvenido;
    //objetos globales
    ConxBD conectarBase = new ConxBD();
    Connection con;
    static JFrame adminpage =  new JFrame("Administrador");
    public AdminPag1() {
        try {
            con = conectarBase.estbConexion();
            Statement qr = con.createStatement();
            ResultSet resultqr = qr.executeQuery("Select * from datos_administrador;");
            while(resultqr.next()) {
                JLsetnombre.setText(resultqr.getString(2));
                JLsetcedula.setText(resultqr.getString(1));
                JLsetcargo.setText("Administrador");
            }
            con.close();
        } catch (HeadlessException | SQLException f) {
            System.err.println(f);
        }
        JBbacklogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login ventLogin = new Login();
                ventLogin.abrirVentana();
                adminpage.setVisible(false);
            }
        });
        JBagregarproducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminNewProducto opc1= new AdminNewProducto();
                opc1.abrirVentana();
                cerrarVentana();
            }
        });
        JBagregarempleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminNewCajero opc3 = new AdminNewCajero();
                opc3.abrirVentana();
                cerrarVentana();
            }
        });
        JBverventas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminNewVistaVentas ventabusquedas = new AdminNewVistaVentas();
                ventabusquedas.abrirVentana();
                cerrarVentana();
            }
        });
    }

    @Override
    public void abrirVentana(){
        adminpage.setContentPane(new AdminPag1().JPadmpg1);
        adminpage.setDefaultCloseOperation(adminpage.EXIT_ON_CLOSE);
        adminpage.pack();
        adminpage.setLocationRelativeTo(null);
        adminpage.setResizable(false);
        adminpage.setVisible(true);
    }
    @Override
    public void cerrarVentana(){
        adminpage.setVisible(false);
    }
}