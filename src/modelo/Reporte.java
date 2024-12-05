package modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import DAO.ProductoDAO;

public class Reporte {
	 private String periodo;
	    private List<Producto> productos;

	    public Reporte(String periodo, List<Producto> productos) {
	        this.periodo = periodo;
	        this.productos = productos;
	    }
	    

	    public String generarReporte() {
	    	StringBuilder sb = new StringBuilder();
	        sb.append("Reporte de Ventas - Periodo: ").append(periodo).append("\n");
	        sb.append("------------------------------------------------\n");
	        sb.append("ID\tNombre\t\tCantidad\tTotal\n");
	        sb.append("------------------------------------------------\n");

	        for (Producto producto : productos) {
	            sb.append(producto.getId()).append("\t")
	              .append(producto.getNombre()).append("\t\t")
	              .append(producto.getCantidadVendida()).append("\t\t")
	              .append(producto.getTotalVendido()).append("\n");
	        }

	        sb.append("------------------------------------------------\n");
	        return sb.toString();
	    }
	    
	    final String filePath= "";

	    public void guardarComoTxt(String filePath) {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
	            writer.write(generarReporte());
	            System.out.println("Reporte guardado correctamente en: " + filePath);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}

