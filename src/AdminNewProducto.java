import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminNewProducto extends ventanas{
    private JPanel JPadmpag2;
    private JButton JBcerrarsesion;
    private JButton JBregresaradm;
    private JLabel JLiconoempresa;
    private JLabel JLnewproducto;
    private JButton JBborracampos;
    private JButton JBguardar;
    private JTextField JTFcodigo;
    private JTextField JTFnombre;
    private JTextField JTFstock;
    private JLabel JLtitulo;
    private JLabel JLcodigo;
    private JLabel JLnombre;
    private JLabel JLstock;
    private JLabel JLprecio;
    private JTextField JFTprecio;
    //objetos globales
    static JFrame addproducto =  new JFrame("Nuevo Producto");

    ConxBD conectarBD = new ConxBD();
    Connection con;
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
        JBguardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resultado=0;
                try {
                    PreparedStatement ps;
                    con=conectarBD.estbConexion();
                    ps = con.prepareStatement("Insert into productos values (?,?,?,?)");
                    ps.setString(1, JTFcodigo.getText());
                    ps.setString(2, JTFnombre.getText());
                    ps.setString(3, JTFstock.getText());
                    ps.setString(4, JFTprecio.getText());
                    resultado=ps.executeUpdate();
                    if (resultado>0){
                        JOptionPane.showMessageDialog(null,"Proucto agregado");
                    }else {
                        JOptionPane.showMessageDialog(null,"Producto no agregado, Ya ha ingresado ese codigo");
                    }
                    con.close();
                }catch (HeadlessException | SQLException f){
                    System.err.println(f);
                }
            }
        });
        JBborracampos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTFcodigo.setText("");
                JTFnombre.setText("");
                JTFstock.setText("");
                JFTprecio.setText("");
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
