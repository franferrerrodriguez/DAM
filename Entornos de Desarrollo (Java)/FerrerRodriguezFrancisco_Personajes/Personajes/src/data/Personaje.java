package data;

public abstract class Personaje implements Comparable<Personaje> {

	private String nombre;
	private int edad;

	public enum tiposOrdenacion {
		NO, EDAD
	}

	public Personaje(String nombre, int edad) {
		this.nombre = nombre;
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = (edad > 0) ? edad : 0;
	}

	@Override
	public String toString() {
		return "";
	}

	@Override
	public int compareTo(Personaje p) {
		return this.getEdad() - p.getEdad();
	}

}
