package JunitTests;
 
import modelo.Producto;
import modelo.Administrador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
 
import java.util.List;
 
import static org.junit.jupiter.api.Assertions.*;
 
public class AdministradorTest {
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
 
        boolean productoEncontrado = productosActualizados.stream()
                .anyMatch(producto -> producto.getNombre().equals("Chocolate") && producto.getPrecio() == 20.0);
        assertTrue(productoEncontrado, "El producto no fue agregado correctamente al sistema");
    }
}
