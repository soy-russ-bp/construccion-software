package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

import DAO.PedidoDAO;
import modelo.DetallePedido;
import modelo.Empleado;
import modelo.Pedido;
import modelo.Producto;
import vista.VistaCliente;
import vista.VistaDetallesHecho;
import vista.VistaEmpleado;

public class ControladorEmpleado implements ActionListener{

    private Empleado empleado;
    private VistaEmpleado vistaEmpleado;
    private VistaDetallesHecho vistaDetallesHecho;
    private VistaCliente vistaCliente;

    
    public ControladorEmpleado( VistaEmpleado vistaEmpleado, VistaCliente vistaCliente, VistaDetallesHecho vistaDetalles, Empleado empleado) {
        this.vistaEmpleado = vistaEmpleado;
        this.vistaCliente = vistaCliente;
        this.vistaDetallesHecho = vistaDetalles;

        this.empleado = empleado;

        this.vistaCliente.setVisible(false);
        this.vistaEmpleado.setVisible(true);
        this.vistaDetallesHecho.setVisible(false);

        this.vistaEmpleado.getBotonVerDetalles().addActionListener(this);
        this.vistaCliente.getBotonEnviarPedido().addActionListener(this); 
        this.vistaDetallesHecho.getBotonMarcarHecho().addActionListener(this);
        this.vistaDetallesHecho.getBotonVolver().addActionListener(this);
        
        actualizarTablaPedidos();
    }


    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaCliente.getBotonEnviarPedido()) {
        	actualizarTablaPedidos();
        }

        if (e.getSource() == vistaDetallesHecho.getBotonVolver()) {
            JTable tablaProductos = vistaDetallesHecho.getTablaProductos();
            // Obtener el índice de la fila seleccionada
            int filaSeleccionada = this.vistaEmpleado.getTablaProductos().getSelectedRow();
            
            if (filaSeleccionada >= 0 ) { // Verificar que hay una fila seleccionada
                // Procesar los datos de la fila seleccionada
                DefaultTableModel modelo = (DefaultTableModel) tablaProductos.getModel();
                String producto = (String) modelo.getValueAt(filaSeleccionada, 0);
                Boolean hecho = (Boolean) modelo.getValueAt(filaSeleccionada, 1);
                
            }

            // Limpiar la selección antes de cerrar la vista
            tablaProductos.clearSelection();

            // Cerrar la ventana
            vistaDetallesHecho.setVisible(false);
        }

        if (e.getSource() == vistaEmpleado.getBotonVerDetalles()) {
            // Obtener el id del pedido de la fila seleccionada
            int filaSeleccionada = vistaEmpleado.getTablaProductos().getSelectedRow();
            int idPedido = (int) vistaEmpleado.getModeloTablaProductos().getValueAt(filaSeleccionada, 1);
            
            // Cargar los detalles del pedido
            cargarDetallesPedido(vistaDetallesHecho, idPedido);
            vistaDetallesHecho.setVisible(true);
        }
    

       

 
    }

    public void actualizarTablaPedidos() {
        // Limpiar los datos actuales de la tabla
        DefaultTableModel modelo = vistaEmpleado.getModeloTablaProductos();
        modelo.setRowCount(0);

        // Obtener la lista de pedidos desde el DAO
        PedidoDAO pedidoDAO = new PedidoDAO();
        for (Pedido pedido : pedidoDAO.obtenerPedidos()) {
            // Agregar cada pedido a la Jtabla
            modelo.addRow(new Object[]{
                    pedido.getNombreCliente(),
                    pedido.getId(),
                    pedido.getTotalProductosHechos(),
                    pedido.getTotal(),
                    pedido.getEstado()
            });
        }
    }

    private void cargarDetallesPedido(VistaDetallesHecho detallesVista, int idPedido) {
        // Obtener los productos del pedido desde la base de datos
        List<DetallePedido> productos = obtenerProductosPorPedido(idPedido);
        
        // Calcular el número total de filas necesarias
        int totalFilas = 0;
        for (DetallePedido producto : productos) {
            totalFilas += producto.getCantidad();
        }
        
        // Crear un arreglo de datos basado en el totalFilas calculado
        Object[][] datos = new Object[totalFilas][2];
        int filaActual = 0;
        
        // Llenar el arreglo de datos
        for (DetallePedido producto : productos) {
            for (int i = 0; i < producto.getCantidad(); i++) {
                datos[filaActual][0] = producto.getNombreProducto(); // Nombre del producto
                datos[filaActual][1] = false; // Inicialmente, "Hecho" es falso
                filaActual++;
            }
        }
        
        detallesVista.setIdProducto(String.valueOf(idPedido));
        detallesVista.cargarDatosTabla(datos);
    }
    
    private List<DetallePedido> obtenerProductosPorPedido(int idPedido) {
        // Obtener los productos del pedido desde la base de datos
        PedidoDAO pedidoDAO = new PedidoDAO();
        return pedidoDAO.obtenerProductosPorPedido(idPedido);
    }
}
