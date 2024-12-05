package JunitTests;

import modelo.Reporte;
import modelo.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReporteTest {
    private Reporte reporte;
    private List<Producto> productos;

    @BeforeEach
    public void setUp() {
        productos = new ArrayList<>();
        productos.add(new Producto(1, "Café", 50.0));
        productos.add(new Producto(2, "Té", 30.0));
        reporte = new Reporte("Enero 2024", productos);
    }

    @Test
    public void testGenerarReporte() {
        String contenidoReporte = reporte.generar();
        assertNotNull(contenidoReporte);
        assertTrue(contenidoReporte.contains("Reporte de Ventas - Periodo: Enero 2024"));
        assertTrue(contenidoReporte.contains("Café"));
        assertTrue(contenidoReporte.contains("Té"));
    }

    @Test
    public void testGuardarReporteComoTxt() {
        String filePath = "reporte_test.txt";
        reporte.guardarComoTxt(filePath);
        File archivo = new File(filePath);
        assertTrue(archivo.exists());

        try {
            String contenidoArchivo = new String(Files.readAllBytes(Paths.get(filePath)));
            assertTrue(contenidoArchivo.contains("Reporte de Ventas - Periodo: Enero 2024"));
        } catch (IOException e) {
            fail("No se pudo leer el archivo generado");
        } finally {
            // Eliminar el archivo después de la prueba
            archivo.delete();
        }
    }

}
