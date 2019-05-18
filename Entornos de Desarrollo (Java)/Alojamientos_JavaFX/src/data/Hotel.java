package data;

public class Hotel extends Alojamiento {

	private int num_estrellas = 1;
	private Boolean tiene_piscina;

	public Hotel(String nombre, Double precio, int num_estrellas, Boolean tiene_piscina) {
		super(nombre, precio);
		setNumEstrellas(num_estrellas);
		this.tiene_piscina = tiene_piscina;
		super.tipo = TipoAlojamiento.HOTEL;
	}

	public int getNumEstrellas() {
		return num_estrellas;
	}

	public void setNumEstrellas(int num_estrellas) {
		if (num_estrellas >= 1 && num_estrellas <= 5)
			this.num_estrellas = num_estrellas;
	}

	public Boolean getTienePiscina() {
		return tiene_piscina;
	}

	public void setTienePiscina(Boolean tiene_piscina) {
		this.tiene_piscina = tiene_piscina;
	}

	@Override
	public String toString() {
		return super.getNombre();
	}

	public String toCsv() {
		return String.format("hotel;%s;%s;%s;%s;", super.getNombre(), super.getPrecio(), num_estrellas,
				tiene_piscina ? "true" : "false");
	}

}