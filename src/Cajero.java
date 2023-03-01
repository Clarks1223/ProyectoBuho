import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JLabel JLgetDescripoPro;

    static JFrame addcajero =  new JFrame("Cajero");

    public Cajero() {
        buttonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Abrir la ventana de ventas
                Venta ventLogin = new Venta();
                ventLogin.abrirVentana();
                addcajero.setVisible(false);
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
}
