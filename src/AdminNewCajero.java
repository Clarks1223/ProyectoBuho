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
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JLabel JLtitulo;
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
