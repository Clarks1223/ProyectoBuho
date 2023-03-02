import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Venta extends ventanas {
    private JPanel panel1;
    private JTable table1;
    private JButton ATRASButton;
    private JButton CERRARButton;
    private JButton button3;
    static JFrame addproduct =  new JFrame("Venta");

    public Venta() {
        ATRASButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cajero RegistroP = new Cajero();
                RegistroP.abrirVentana();
                addproduct.setVisible(false);
            }
        });
        CERRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login loginCaj = new Login();
                loginCaj.abrirVentana();
                addproduct.setVisible(false);
            }
        });
    }

    @Override
    public void abrirVentana(){
        addproduct.setContentPane(new Venta().panel1);
        addproduct.setDefaultCloseOperation(addproduct.EXIT_ON_CLOSE);
        addproduct.pack();
        addproduct.setLocationRelativeTo(null);
        addproduct.setResizable(false);
        addproduct.setVisible(true);
    }
    @Override
    public void cerrarVentana(){
        addproduct.setVisible(false);
    }
}
