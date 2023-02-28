import javax.swing.*;

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
    JFrame addcajero =  new JFrame("Nuevo Empleado");
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
