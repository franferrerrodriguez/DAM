package data;

public class Reserva {

	private String fecha_entrada;
	private int num_dias;
	private Alojamiento alojamiento;

	public Reserva(String fecha_entrada, int num_dias, Alojamiento alojamiento) {
		this.fecha_entrada = fecha_entrada;
		this.num_dias = num_dias;
		this.alojamiento = alojamiento;
	}

	public String getFechaEntrada() {
		return fecha_entrada;
	}

	public void setFechaEntrada(String fecha_entrada) {
		this.fecha_entrada = fecha_entrada;
	}

	public int getNumDias() {
		return num_dias;
	}

	public void setNumDias(int num_dias) {
		this.num_dias = num_dias;
	}

	public Alojamiento getAlojamiento() {
		return alojamiento;
	}

	public void setAlojamiento(Alojamiento alojamiento) {
		this.alojamiento = alojamiento;
	}

	@Override
	public String toString() {
		return "Reserva - Nombre alojamiento:  " + alojamiento.getNombre() + " - Fecha de entrada: " + fecha_entrada
				+ " - Número de días: " + num_dias;
	}

}