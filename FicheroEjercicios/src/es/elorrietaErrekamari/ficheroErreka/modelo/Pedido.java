package es.elorrietaErrekamari.ficheroErreka.modelo;

import java.io.Serializable;

public class Pedido implements Serializable {

	private static final long serialVersionUID = -2249871634639166752L;

	private String nombre = null;
	
	private int cantidad = 0;
	
	
	public Pedido() {
		super();
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Pedido [nombre=" + nombre + ", cantidad=" + cantidad + "]";
	}
	
	
}
