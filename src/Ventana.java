/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.List;
/**
 *
 * @author thoma
 */
public class Ventana extends JFrame{
    private JTable tablaFacturas;
    private DefaultTableModel modeloTabla;
    private GestorFactura gestorFactura;

    public Ventana() {
        gestorFactura = new GestorFactura();

        setTitle("Registro de Facturas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Facturas");
        lblTitulo.setBounds(20, 20, 100, 30);
        add(lblTitulo);

        modeloTabla = new DefaultTableModel(new String[]{"Código", "Nombre", "Cantidad", "Precio", "Impuesto", "Total"}, 0);
        tablaFacturas = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaFacturas);
        scrollPane.setBounds(20, 60, 740, 300);
        add(scrollPane);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(20, 400, 100, 30);
        btnAgregar.addActionListener(this::accionAgregar);
        add(btnAgregar);

        JButton btnModificar = new JButton("Modificar");
        btnModificar.setBounds(140, 400, 100, 30);
        btnModificar.addActionListener(this::accionModificar);
        add(btnModificar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(260, 400, 100, 30);
        btnEliminar.addActionListener(this::accionEliminar);
        add(btnEliminar);

        cargarTabla();
    }

    private void cargarTabla() {
        modeloTabla.setRowCount(0);
        List<Factura> facturas = gestorFactura.getFacturas();
        for (Factura factura : facturas) {
            modeloTabla.addRow(new Object[]{factura.getCodigoProducto(), factura.getNombreProducto(),
                    factura.getCantidad(), factura.getPrecio(), factura.getImpuesto(), factura.getTotal()});
        }
    }

    private void accionAgregar(ActionEvent e) {
        Factura nueva = obtenerFacturaDesdeFormulario();
        if (nueva != null) {
            gestorFactura.agregarFactura(nueva);
            cargarTabla();
        }
    }

    private void accionModificar(ActionEvent e) {
        int fila = tablaFacturas.getSelectedRow();
        if (fila >= 0) {
            Factura modificada = obtenerFacturaDesdeFormulario();
            if (modificada != null) {
                gestorFactura.modificarFactura(fila, modificada);
                cargarTabla();
            }
        }
    }

    private void accionEliminar(ActionEvent e) {
        int fila = tablaFacturas.getSelectedRow();
        if (fila >= 0) {
            gestorFactura.eliminarFactura(fila);
            cargarTabla();
        }
    }

    private Factura obtenerFacturaDesdeFormulario() {
        String codigo = JOptionPane.showInputDialog("Código del Producto:");
        String nombre = JOptionPane.showInputDialog("Nombre del Producto:");
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad:"));
        double precio = Double.parseDouble(JOptionPane.showInputDialog("Precio:"));

        return new Factura(codigo, nombre, cantidad, precio);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Ventana().setVisible(true);
        });
    }
    
}
