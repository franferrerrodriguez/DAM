package videojuegos.data;

public class VideoJuego {

	private String titulo;

	private String genero;

	private double precio;

	private Compania compania;

	private static int numero_juegos;

	public VideoJuego() {
		numero_juegos++;
	}

	public VideoJuego(String titulo, String genero, Double precio, Compania compania) {
		numero_juegos++;
		this.titulo = titulo;
		this.genero = genero;
		this.precio = precio;
		this.compania = compania;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Compania getCompania() {
		return compania;
	}

	public void setCompania(Compania compania) {
		this.compania = compania;
	}

	public static int getNumero_juegos() {
		return numero_juegos;
	}

	public static void setNumero_juegos(int numero_juegos) {
		VideoJuego.numero_juegos = numero_juegos;
	}

	@Override
	public String toString() {
		return String.format("\nTítulo: %s | Género: %s | Precio: %s €", this.titulo, this.genero, this.precio);
	}

	public static VideoJuego juegoMasBarato(VideoJuego[] juegos) {

		VideoJuego juego = new VideoJuego();
		int s = 0;

		for (VideoJuego j : juegos) {
			if (s == 0) {
				juego = j;
				s++;
			} else if (j.getPrecio() < juego.getPrecio()) {
				juego = j;
			}
		}

		return juego;
	}

	public static VideoJuego juegoMasCaro(VideoJuego[] juegos) {

		VideoJuego juego = new VideoJuego();
		int s = 0;

		for (VideoJuego j : juegos) {
			if (s == 0) {
				juego = j;
				s++;
			} else if (j.getPrecio() > juego.getPrecio()) {
				juego = j;
			}
		}

		return juego;
	}

	public static int numeroMasRepetido(int[] numeros) {
		int numero = 0;
		int s = 0;

		for (int n : numeros) {
			if (s == 0) {
				numero = n;
				s++;
			} else if (n > numero) {
				numero = n;
			}
		}

		return numero;
	}

}