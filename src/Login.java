import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    //objetos para la conexion con la BD
    ConxBD conectarBD = new ConxBD();
    Connection con;
    //Variables que ingresa el usuario
    String tipousuario [] = new String[2];
    String usuario="";
    String contrasenia="";
    int intentos=3;

    public Login() {
        JBiniciarsecion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //obtengo el tipo de usuario
                tipousuario=tipoUsuario(JCBroldeUsuario.getSelectedItem().toString());
                //obtengo el usuario
                usuario = JTFusuario.getText();
                //obtengo la contrasenia
                char [] password = JTFpassword.getPassword();
                for (int x=0; x<password.length; x++){
                    contrasenia+=password[x];
                }
                //variables para los datos que enviara la BD
                String usBD="";
                String passBD="";
                //
                int resultado=0;
                    try{
                        con = conectarBD.estbConexion();
                        Statement consultaUsuario = con.createStatement();
                        ResultSet resultadoUsuario = consultaUsuario.executeQuery("select * from "+tipousuario[0]+" where "+tipousuario[1]+"="+usuario);
                        while(resultadoUsuario.next()) {
                            usBD = resultadoUsuario.getString(1);
                            passBD = resultadoUsuario.getString(2);
                            resultado++;
                        }
                        if (resultado==0) {
                            intentos--;
                            JOptionPane.showMessageDialog(null,"Usuario NO encontrado, tiene "+intentos+" intentos");
                        }
                        con.close();
                    }catch(HeadlessException | SQLException f){
                        System.err.println(f);
                    }

                    if(JCBroldeUsuario.getSelectedItem().toString().equals("Administrador")) {
                        if (usuario.equals(usBD)) {
                            if (contrasenia.equals(passBD)) {
                                AdminPag1 inicioAdmin = new AdminPag1();
                                inicioAdmin.abrirVentana();
                                cerrarVentana();
                            } else {
                                intentos--;
                                JOptionPane.showMessageDialog(null, "Datos incorrectos, tienen " + intentos + " intentos");
                                if (intentos == 0) {
                                    System.exit(0);
                                }
                            }
                        }
                    }
                    else {
                        if(usuario.equals(usBD)) {
                            if (contrasenia.equals(passBD)) {
                                Cajero inicioCajero = new Cajero();
                                inicioCajero.abrirVentana();
                                cerrarVentana();
                            } else {
                                intentos--;
                                JOptionPane.showMessageDialog(null, "Datos incorrectos, tienen " + intentos + " intentos");
                                if (intentos == 0) {
                                    System.exit(0);
                                }
                            }
                        }
                    }
                contrasenia="";
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

    static String[] tipoUsuario(String tipo){
        String tabla[]= new String[2];
        if (tipo.equals("Administrador")){
            tabla[0]="login_administrador";
            tabla[1]="usuAdmin";
        }
        else if (tipo.equals("Cajero")){
            tabla[0]="login_cajero";
            tabla[1]="usuCaj";
        }
        return tabla;
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
