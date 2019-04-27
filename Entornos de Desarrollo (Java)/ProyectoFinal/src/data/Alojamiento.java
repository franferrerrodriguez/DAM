package data;

import java.util.Scanner;

public abstract class Alojamiento implements Comparable<Alojamiento> {

	public enum TipoAlojamiento {
		ALOJAMIENTO, HOTEL, CASARURAL
	}

	static Scanner scan = new Scanner(System.in);
	private String nombre;
	private Double precio;
	protected TipoAlojamiento tipo;
	private Boolean reservado;

	public Alojamiento(String nombre, Double precio) {
		this.nombre = nombre;
		this.precio = precio;
		tipo = TipoAlojamiento.ALOJAMIENTO;
		reservado = false;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public TipoAlojamiento getTipo() {
		return tipo;
	}

	public Boolean getReservado() {
		return reservado;
	}

	public void setReservado(Boolean reservado) {
		this.reservado = reservado;
	}

	public int getNumEstrellas() {
		return 0;
	}

	public int getMaxPersonas() {
		return 0;
	}

	public static Boolean comprobarNombre(String nombre) {

		Boolean control = false;

		if (null != nombre)
			if (nombre.length() > 0)
				control = true;

		return control;

	}

	public String toCsv() {
		return "";
	}

	@Override
	public int compareTo(Alojamiento a) {
		if (this.precio > a.getPrecio()) {
			return 1;
		} else if (a.precio > this.getPrecio()) {
			return -1;
		} else {
			return 0;
		}
	}

}
