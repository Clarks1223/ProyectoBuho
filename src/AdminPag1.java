import javax.swing.*;

public class AdminPag1 extends ventanas{
    private JPanel JPadmpg1;
    private JButton JBagregarproducto;
    private JButton JBverventas;
    private JButton JBagregarempleado;
    private JLabel JLnombre;
    private JLabel JLapellido;
    private JLabel JLcargo;
    private JLabel JLsetnombre;
    private JLabel JLsetapellido;
    private JLabel JLsetcargo;
    private JButton JBbacklogin;
    private JLabel JLseticonoempresa;
    private JLabel JLsetimagenperfil;
    private JLabel JLfunciones;
    private JLabel JLbienvenido;
    //objetos globales
    JFrame adminpage =  new JFrame("Administrador");
    @Override
    public void abrirVentana(){
        adminpage.setContentPane(new AdminPag1().JPadmpg1);
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