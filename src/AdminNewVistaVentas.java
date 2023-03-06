import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminNewVistaVentas extends ventanas{
    private JPanel JPadmpag3;
    private JButton JBcerrarsesion;
    private JButton JBatras;
    private JLabel JLtitulo;
    private JLabel JLicono;
    private JRadioButton JRBcajero;
    private JRadioButton JRBresumen;
    private JComboBox JCBcajeros;
    private JButton JBfiltrar;
    private JTable JTdatoscajero;
    private JLabel JLtitulo2;
    private JTable table1;
    private JTextField cédulaTextField;
    private JTextField nombreTextField;
    private JTextField numFacturaTextField;
    private JTextField numDetalleTextField;
    private JTextField totalTextField;
    //objeto para el frame
    static JFrame busqueda = new JFrame();
    //objetos para la coneccion
    ConxBD conectarBD = new ConxBD();
    Connection con;
    DefaultTableModel dtm = new DefaultTableModel();
    String[] titulo = new String[]{"ID","Nombre","Apellido"};
    public AdminNewVistaVentas(){
    //Seteo resumen total como predefinido
    JRBresumen.setSelected(true);
    //Cargo los datos de los cajeros
    try{
        con = conectarBD.estbConexion();
        Statement consultaCajeros = con.createStatement();
        ResultSet resultadoCajero = consultaCajeros.executeQuery("select cedCaj from datos_cajero");
        while (resultadoCajero.next()){
            JCBcajeros.addItem(resultadoCajero.getString(1));
        }
        con.close();
    }catch (HeadlessException | SQLException f){
        System.err.println(f);
    }
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
    JBfiltrar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (JRBcajero.isEnabled()) {
                try {
                    DefaultTableModel modelo = new DefaultTableModel();
                    table1.setModel(modelo);
                    con = conectarBD.estbConexion();
                    String ced;
                    ced = JCBcajeros.getSelectedItem().toString();
                    Statement qrCaj = con.createStatement();
                    ResultSet resqrCaj = qrCaj.executeQuery("select dc.cedCaj, dc.nomCaj, cf.numFac, df.codDet, tf.totalTot from datos_cajero dc, " +
                            "cabecera_factura cf, detalle_factura df, total_factura tf where dc.cedCaj = cf.cedCajFK and cf.numFac = df.numFacFK " +
                            "and df.codDet = tf.codDetFK and dc.cedCaj = "+ced);
                    ResultSetMetaData rsMD = resqrCaj.getMetaData();
                    int col = rsMD.getColumnCount();
                    modelo.addColumn("Cédula");
                    modelo.addColumn("Nombre");
                    modelo.addColumn("Num. Factura");
                    modelo.addColumn("Num. Detalle");
                    modelo.addColumn("Total");
                    while (resqrCaj.next()) {
                        Object[] filas = new Object[col];
                        for (int i=0;i<col;i++) {
                            filas[i] = resqrCaj.getObject(i+1);
                        }
                        modelo.addRow(filas);
                    }
                    con.close();
                } catch (HeadlessException | SQLException f) {
                    System.err.println(f);
                }
            }
            else {
                try {
                    DefaultTableModel model = new DefaultTableModel();
                    table1.setModel(model);
                    con = conectarBD.estbConexion();
                    Statement qrRes = con.createStatement();
                    ResultSet resqrRes = qrRes.executeQuery("select dc.cedCaj, dc.nomCaj, cf.numFac, df.codDet, tf.totalTot from datos_cajero dc, " +
                            "cabecera_factura cf, detalle_factura df, total_factura tf where dc.cedCaj = cf.cedCajFK and cf.numFac = df.numFacFK " +
                            "and df.codDet = tf.codDetFK");
                    ResultSetMetaData rs = resqrRes.getMetaData();
                    int col = rs.getColumnCount();
                    model.addColumn("Cédula");
                    model.addColumn("Nombre");
                    model.addColumn("Num. Factura");
                    model.addColumn("Num. Detalle");
                    model.addColumn("Total");
                    while (resqrRes.next()) {
                        Object[] filas = new Object[col];
                        for (int i=0;i<col;i++) {
                            filas[i] = resqrRes.getObject(i+1);
                        }
                        model.addRow(filas);
                    }
                    con.close();
                } catch (HeadlessException | SQLException f) {
                    System.err.println(f);
                }
            }
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