package modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import DAO.PedidoDAO;

public class Reporte {
	 private LocalDate fechaInicio;
	 private LocalDate fechaFin;
	 private List<Pedido> pedidos;
	 private double totalVentas;
	    
	 public Reporte(LocalDate fechaInicio, LocalDate fechaFin) {
		 this.fechaInicio = fechaInicio;
		 this.fechaFin = fechaFin;
		 this.pedidos = PedidoDAO.seleccionarPedidosPorFecha(fechaInicio, fechaFin);
	 }

	 public String generarReporte() {
		 setTotalVentas(0);
		 StringBuilder reporte = new StringBuilder();
		 reporte.append("Reporte de Ventas - Periodo: ");
		 reporte.append(fechaInicio).append(" a ");
		 reporte.append(fechaFin).append("\n");
		 reporte.append("------------------------------------------------\n");
		 reporte.append("ID\tFecha\tTotal\n");
		 reporte.append("------------------------------------------------\n");

	     for (Pedido pedido : pedidos) {
	    	 reporte.append(pedido.getId()).append("\t")
	         .append(pedido.getFecha()).append("\t\t")
	         .append("$").append(pedido.getTotal()).append("\n");
	    	 
	    	 this.totalVentas += pedido.getTotal();
	     }

	     reporte.append("------------------------------------------------\n");
	     reporte.append("Total :  $" + getTotalVentas() + "\n");
	     reporte.append("------------------------------------------------\n");
	     return reporte.toString();
	}

	public void guardarComoTxt(String filePath) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
	    	writer.write(generarReporte());
	    } catch (IOException e) {
	    	System.out.println("Hubo problema par aguardar el reporte");
	    }
	}
	
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public double getTotalVentas() {
		return totalVentas;
	}

	public void setTotalVentas(double totalVentas) {
		this.totalVentas = totalVentas;
	}
}