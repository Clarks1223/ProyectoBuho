import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminNewVistaVentas extends ventanas{
    private JPanel JPadmpag3;
    private JButton JBcerrarsesion;
    private JButton JBatras;
    private JLabel JLtitulo;
    private JLabel JLicono;
    private JRadioButton JRBcajero;
    private JRadioButton JRBresumen;
    private JComboBox JCBcajeros;
    private JButton JBfiltar;
    private JTable JTdatoscajero;
    private JLabel JLtitulo2;
    private JTable table1;
    static JFrame busqueda = new JFrame();
    DefaultTableModel dtm = new DefaultTableModel();
    String[] titulo = new String[]{"ID","Nombre","Apellido"};
public AdminNewVistaVentas(){
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
    JRBcajero.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JCBcajeros.setEnabled(true);
        }
    });
    JRBresumen.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JCBcajeros.setEnabled(false);
        }
    });
}
    @Override
    public void abrirVentana(){
        busqueda.setContentPane(new AdminNewVistaVentas().JPadmpag3);
        busqueda.setDefaultCloseOperation(busqueda.EXIT_ON_CLOSE);
        busqueda.pack();
        busqueda.setLocationRelativeTo(null);
        busqueda.setResizable(false);
        busqueda.setVisible(true);
    }
    @Override
    public void cerrarVentana(){
    busqueda.setVisible(false);
    }
}