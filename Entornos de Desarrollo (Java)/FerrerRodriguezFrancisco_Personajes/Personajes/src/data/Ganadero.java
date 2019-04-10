package data;

public class Ganadero extends Personaje {

	private String ganado;
	private String transporte;
	private int velocidad = 1;

	public Ganadero(String nombre, int edad, String ganado, String transporte, int velocidad) {
		super(nombre, edad);
		this.ganado = ganado;
		this.transporte = transporte;
		this.velocidad = velocidad;
	}

	public String getGanado() {
		return ganado;
	}

	public void setGanado(String ganado) {
		this.ganado = ganado;
	}

	public String getTransporte() {
		return transporte;
	}

	public void setTransporte(String transporte) {
		this.transporte = transporte;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = (velocidad >= 1 && velocidad <= 7) ? velocidad : 1;
	}

	@Override
	public String toString() {
		return String.format("Ganadero - Nombre: %s, edad: %d años. Ganado: %s. Transporte: %s(%d)", super.getNombre(),
				super.getEdad(), ganado, transporte, velocidad);
	}

}