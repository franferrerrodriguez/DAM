package videojuegos.data;

public class PCVideoJuego extends VideoJuego {

	private int RAMminima;

	private int HDminimo;

	public PCVideoJuego() {
		//
	}

	public PCVideoJuego(String titulo, String genero, Double precio, Compania compania) {
		super(titulo, genero, precio, compania);
	}

	public PCVideoJuego(String titulo, String genero, Double precio, Compania compania, int RAMminima, int HDminimo) {
		super(titulo, genero, precio, compania);
		this.RAMminima = RAMminima;
		this.HDminimo = HDminimo;
	}

	public int getRAMminima() {
		return RAMminima;
	}

	public void setRAMminima(int rAMminima) {
		RAMminima = rAMminima;
	}

	public int getHDminimo() {
		return HDminimo;
	}

	public void setHDminimo(int hDminimo) {
		HDminimo = hDminimo;
	}

}
