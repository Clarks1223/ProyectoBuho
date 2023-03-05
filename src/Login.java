import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends ventanas{
    private JPanel FLogin;
    private JLabel LNomEmpresa;
    private JLabel LBienvenido;
    private JTextField JTFusuario;
    private JLabel JLRoldeUsuario;
    private JLabel JLingreseUsuario;
    private JPasswordField JTFpassword;
    private JLabel JLingreseContrasenia;
    private JComboBox JCBroldeUsuario;
    private JLabel JLImagenBuho;
    private JButton JBiniciarsecion;
    static JFrame frame=new JFrame("Login");
    String tipousuario = "";
    public Login() {
        JBiniciarsecion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tipousuario=JCBroldeUsuario.getSelectedItem().toString();
                if (tipousuario.equals("Administrador")){
                    AdminPag1 inicioAdmin = new AdminPag1();
                    inicioAdmin.abrirVentana();
                    cerrarVentana();
                }else{
                    Cajero inicioCajero = new Cajero();
                    inicioCajero.abrirVentana();
                    cerrarVentana();
                }
            }
        });
        JTFusuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JTFusuario.setText("");
            }
        });
        JTFpassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JTFpassword.setText("");
            }
        });
    }

    @Override
    public void abrirVentana(){
        frame.setContentPane(new Login().FLogin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true); //Visualizar ventana
    }
    @Override
    public void cerrarVentana(){
        frame.setVisible(false); //Cerrar ventana
    }
}
