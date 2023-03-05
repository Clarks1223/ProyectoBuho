import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    //Variables globales
    static JFrame addcajero =  new JFrame("Nuevo Empleado");

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
