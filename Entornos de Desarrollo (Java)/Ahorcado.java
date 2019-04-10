package ahorcado;

import java.util.Scanner;

public class Ahorcado {

	public static void main(String[] args) {
		new JuegoAhorcado();
	}

}

class JuegoAhorcado {

	Scanner reader = new Scanner(System.in);

	private String palabra_secreta;
	private String palabra_secreta_encriptada = "";
	private Boolean acierto = false;

	public static final String OK = "\nEnhorabuena, ¡Has acertado!";
	public static final String KO = "\nNo has acertado en los intentos permitidos.";
	public static final String mensajeFinPrograma = "\nFin de programa.";

	public String getPalabraSecreta() {
		return palabra_secreta;
	}

	public void setPalabraSecreta(String palabra_secreta) {
		this.palabra_secreta = palabra_secreta;
	}

	public String getPalabraSecretaEncriptada() {
		return palabra_secreta_encriptada;
	}

	public void setPalabraSecretaEncriptada(String palabra_secreta_encriptada) {
		this.palabra_secreta_encriptada = palabra_secreta_encriptada;
	}

	public Boolean getAcierto() {
		return acierto;
	}

	public void setAcierto(Boolean acierto) {
		this.acierto = acierto;
	}

	JuegoAhorcado() {

		preguntarPalabra();

		int intento = 0;
		while (intento < 10 && !this.getAcierto()) {
			try {
				this.nuevoIntento(intento);
				intento++;
			} catch (IllegalArgumentException e) {
				System.out.println("Letra no válida.");
			}
		}

		reader.close();

		this.mensajeFinal();

		System.out.println(mensajeFinPrograma);

	}

	public void preguntarPalabra() {
		System.out.print("Introduce la palabra secreta: ");
		this.setPalabraSecreta(reader.next().toUpperCase());
	}

	public void nuevoIntento(int intento) throws IllegalArgumentException {
		System.out.print(String.format("\nIndica una letra [intento %d]: ", intento));
		String letra = reader.next();

		this.setPalabraSecretaEncriptada(compruebaLetra(this.palabra_secreta, letra));
		System.out.println(this.getPalabraSecretaEncriptada());

	}

	public String compruebaLetra(String palabra_secreta, String letra) throws IllegalArgumentException {

		if (!Character.isLetter(letra.charAt(0)) || letra.length() > 1) {
			throw new IllegalArgumentException();
		}

		String s = "";
		this.setAcierto(true);
		for (char c : palabra_secreta.toCharArray()) {
			if (c == letra.toUpperCase().charAt(0) || palabra_secreta_encriptada.indexOf(c) != -1) {
				s += c;
			} else {
				this.setAcierto(false);
				s += "-";
			}
		}
		return palabra_secreta_encriptada = s;
	}

	public void mensajeFinal() {
		if (this.getAcierto()) {
			System.out.println(OK);
		} else {
			System.out.println(String.format("%s\n\nLa palabra a acertar era: %s.", KO, this.getPalabraSecreta()));
		}
	}

}
