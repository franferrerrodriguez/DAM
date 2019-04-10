package data;

public class Estado {

	String nombre;
	String codigo;

	public Estado(String nombre, String codigo) {
		this.nombre = nombre;
		setCodigo(codigo);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		if (comprobarCodigo(codigo))
			this.codigo = codigo;
	}

	public static Boolean comprobarCodigo(String codigo) {
		Boolean valido = false;

		if (codigo.length() == 2)
			valido = true;

		return valido;
	}

	public String toCsv() {
		return String.format("%s;%s;", nombre, codigo);
	}

	@Override
	public String toString() {
		return "Código: " + codigo + " - Nombre: " + nombre;
	}

}