package JunitTests;

import modelo.Producto;
import modelo.Administrador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class modificarPedidoTest {
    private Administrador administrador;

    @BeforeEach
    public void setUp() {
        administrador = new Administrador(1, "admin@email.com", "ola1234", "admin");
    }

    @Test
    public void testAgregarProductoAlSistema() {
        // Agregar un nuevo producto usando el administrador
        administrador.agregarProducto("Chocolate", 20.0, "Chocolate delicioso");
        List<Producto> productosActualizados = administrador.mostrarProductos();

        // Verificar que el nuevo producto esté en la lista de productos
        boolean productoEncontrado = productosActualizados.stream()
                .anyMatch(producto -> producto.getNombre().equals("Chocolate") && producto.getPrecio() == 20.0);
        assertTrue(productoEncontrado, "El producto no fue agregado correctamente al sistema");
    }

    @Test
    public void testActualizarProducto() {
        // Agregar un nuevo producto para actualizar
        administrador.agregarProducto("Té", 15.0, "Té verde");
        List<Producto> productos = administrador.mostrarProductos();
        Producto producto = productos.stream().filter(p -> p.getNombre().equals("Té")).findFirst().orElse(null);
        assertNotNull(producto, "El producto no fue encontrado para actualizar");

        // Actualizar el producto
        administrador.actualizarProducto(producto.getId(), "Té Negro", 18.0, "Té negro");
        List<Producto> productosActualizados = administrador.mostrarProductos();

        // Verificar que el producto haya sido actualizado
        boolean productoActualizado = productosActualizados.stream()
                .anyMatch(p -> p.getId() == producto.getId() && p.getNombre().equals("Té Negro") && p.getPrecio() == 18.0);
        assertTrue(productoActualizado, "El producto no fue actualizado correctamente");
    }

    @Test
    public void testEliminarProducto() {
        // Agregar un nuevo producto para eliminar
        administrador.agregarProducto("Café", 25.0, "Café colombiano");
        List<Producto> productos = administrador.mostrarProductos();
        Producto producto = productos.stream().filter(p -> p.getNombre().equals("Café")).findFirst().orElse(null);
        assertNotNull(producto, "El producto no fue encontrado para eliminar");

        // Eliminar el producto
        administrador.eliminarProducto(producto.getId());
        List<Producto> productosActualizados = administrador.mostrarProductos();

        // Verificar que el producto haya sido eliminado
        boolean productoEliminado = productosActualizados.stream()
                .noneMatch(p -> p.getId() == producto.getId());
        assertTrue(productoEliminado, "El producto no fue eliminado correctamente");
    }
}