import javax.swing.*;

public class Login extends ventanas{
    private JPanel FLogin;
    private JLabel LNomEmpresa;
    private JLabel LBienvenido;
    private JTextField ingreseSuUsuarioTextField;
    private JLabel JLRoldeUsuario;
    private JLabel JLingreseUsuario;
    private JPasswordField contraseniaPasswordField;
    private JLabel JLingreseContrasenia;
    private JComboBox CBRoldeUsuario;
    private JLabel JLImagenBuho;
    private JButton JBiniciarsecion;
    static JFrame frame=new JFrame("Login");
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
