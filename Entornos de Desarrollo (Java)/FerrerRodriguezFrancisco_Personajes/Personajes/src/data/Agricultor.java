package data;

public class Agricultor extends Personaje {

	private String cultivo;
	private String herramienta;
	private int nivel_danyo = 1;

	public Agricultor(String nombre, int edad, String cultivo, String herramienta, int nivel_danyo) {
		super(nombre, edad);
		this.cultivo = cultivo;
		this.herramienta = herramienta;
		this.nivel_danyo = nivel_danyo;
	}

	public String getCultivo() {
		return cultivo;
	}

	public void setCultivo(String cultivo) {
		this.cultivo = cultivo;
	}

	public String getHerramienta() {
		return herramienta;
	}

	public void setHerramienta(String herramienta) {
		this.herramienta = herramienta;
	}

	public int getNivel_danyo() {
		return nivel_danyo;
	}

	public void setNivel_danyo(int nivel_danyo) {
		this.nivel_danyo = (nivel_danyo >= 1 && nivel_danyo <= 10) ? nivel_danyo : 1;
	}

	@Override
	public String toString() {
		return String.format("Agricultor - Nombre: %s, edad: %d años. Cultivo: %s. Herramienta: %s(%d)",
				super.getNombre(), super.getEdad(), cultivo, herramienta, nivel_danyo);
	}

}
