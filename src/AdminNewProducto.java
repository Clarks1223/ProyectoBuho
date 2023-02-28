import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminNewProducto extends ventanas{
    private JPanel JPadmpag2;
    private JButton JBcerrarsesion;
    private JButton JBregresaradm;
    private JLabel JLiconoempresa;
    private JLabel JLnewproducto;
    private JButton JBborracampos;
    private JButton JBguardar;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JLabel JLtitulo;
    //objetos globales
    static JFrame addproducto =  new JFrame("Nuevo Producto");

    public AdminNewProducto() {
        JBcerrarsesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login venLogin = new Login();
                venLogin.abrirVentana();
                cerrarVentana();
            }
        });
        JBregresaradm.addActionListener(new ActionListener() {
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
        addproducto.setContentPane(new AdminNewProducto().JPadmpag2);
        addproducto.setDefaultCloseOperation(addproducto.EXIT_ON_CLOSE);
        addproducto.pack();
        addproducto.setLocationRelativeTo(null);
        addproducto.setResizable(false);
        addproducto.setVisible(true);
    }
    @Override
    public void cerrarVentana(){
        addproducto.setVisible(false);
    }
}
