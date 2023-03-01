import javax.swing.*;

public class Venta extends ventanas {
    private JPanel panel1;
    private JButton CERRARButton;
    private JButton ATRASButton;
    static JFrame adminpage =  new JFrame("Administrador");
    @Override
    public void abrirVentana(){
        adminpage.setContentPane(new Venta().panel1);
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
