package videojuegos.data;

public class Compania {

	private String nombre;

	private String ano_fundacion;

	public Compania() {
		//
	}

	public Compania(String nombre, String ano_fundacion) {
		this.nombre = nombre;
		this.ano_fundacion = ano_fundacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAno_fundacion() {
		return ano_fundacion;
	}

	public void setAno_fundacion(String ano_fundacion) {
		this.ano_fundacion = ano_fundacion;
	}

	@Override
	public String toString() {
		return String.format("\nNombre: %s | Año fundación: %s", this.nombre, this.ano_fundacion);
	}

}
