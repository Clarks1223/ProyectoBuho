import javax.swing.*;

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
    JFrame addproducto =  new JFrame("Nuevo Producto");
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
