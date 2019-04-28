package data;

public class CasaRural extends Alojamiento {

	private int max_personas;
	private int num_habitaciones;

	public CasaRural(String nombre, Double precio, int max_personas, int num_habitaciones) {
		super(nombre, precio);
		this.max_personas = max_personas;
		this.num_habitaciones = num_habitaciones;
		super.tipo = TipoAlojamiento.CASARURAL;
	}

	@Override
	public int getMaxPersonas() {
		return max_personas;
	}

	public void setMaxPersonas(int max_personas) {
		this.max_personas = max_personas;
	}

	public int getNumHabitaciones() {
		return num_habitaciones;
	}

	public void setNumHabitaciones(int num_habitaciones) {
		this.num_habitaciones = num_habitaciones;
	}

	@Override
	public String toString() {
		return "Casa Rural: " + super.getNombre() + " - Precio: " + super.getPrecio() + " - Máximo personas: "
				+ max_personas + " - Nº Habitaciones: " + num_habitaciones;
	}

	public String toCsv() {
		return String.format("casarural;%s;%s;%s;%s;", super.getNombre(), super.getPrecio(), max_personas,
				num_habitaciones);
	}

}