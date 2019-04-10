package ordenar.notas;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OrdenarNotas {

	public static void main(String[] args) {

		Notas notas = new Notas();

		notas.pedirAlumnos();
		notas.pedirNotas();
		notas.ordenarNotas();

		notas.calcularMedia();
		notas.showArrayNotas();
		notas.showArrayNotasOrdenadas();
		notas.showNotaMedia();

		System.out.println(Notas.mensajeFinPrograma);

	}

}

class Notas {

	Scanner in;
	private int numAlumnos = -1;
	private double notaMedia;
	private int[] arrayNotas;
	private int[] arrayNotasOrdenadas;
	public static final String mensajeFinPrograma = "Fin de programa!";

	public int getNumAlumnos() {
		return numAlumnos;
	}

	public void setNumAlumnos(int numAlumnos) {
		this.numAlumnos = numAlumnos;
	}

	public double getNotaMedia() {
		return notaMedia;
	}

	public void setNotaMedia(double notaMedia) {
		this.notaMedia = notaMedia;
	}

	public void showNotaMedia() {
		System.out.println(
				String.format("\nLa nota media de la clase es: %s\n", String.format("%.3f", this.getNotaMedia())));
	}

	public int[] getArrayNotas() {
		return arrayNotas;
	}

	public void setArrayNotas(int[] arrayNotas) {
		this.arrayNotas = arrayNotas;
	}

	public void showArrayNotas() {
		System.out.println("\nArray introducido");
		for (int nota : this.getArrayNotas()) {
			System.out.print(String.format("%s ", nota));
		}
		System.out.println();
	}

	public int[] getArrayNotasOrdenadas() {
		return arrayNotasOrdenadas;
	}

	public void setArrayNotasOrdenadas(int[] arrayNotasOrdenadas) {
		this.arrayNotasOrdenadas = arrayNotasOrdenadas;
	}

	public void showArrayNotasOrdenadas() {
		System.out.println("\nArray ordenado de menor a mayor");
		for (int nota : this.getArrayNotasOrdenadas()) {
			System.out.print(String.format("%s ", nota));
		}
		System.out.println();
	}

	public void pedirAlumnos() {

		while (this.getNumAlumnos() <= 0) {
			try {

				System.out.print("Indica el número de alumnos: ");
				in = new Scanner(System.in);
				this.setNumAlumnos(in.nextInt());

				if (this.getNumAlumnos() <= 0) {
					this.setNumAlumnos(-1);
					System.out.println("\nError. Debe introducir un número positivo.\n");
				}

			} catch (InputMismatchException e) {
				System.out.println("\nError. Debe introducir un número correcto de alumnos.\n");
			}
		}

	}

	public void pedirNotas() {

		int nota;
		this.setArrayNotas(new int[this.getNumAlumnos()]);

		System.out.println("Introduce las notas como un número entero");

		int n = 0;
		while (n < this.getNumAlumnos()) {
			try {

				System.out.print(String.format("Introduce nota %d: ", n));

				in = new Scanner(System.in);
				nota = in.nextInt();

				if (nota >= 0 && nota <= 10) {
					this.getArrayNotas()[n] = nota;
					n++;
				} else {
					System.out.println("\nError. Debe introducir un número entre 0 y 10.\n");
				}

			} catch (InputMismatchException e) {
				System.out.println("\nError. Debe introducir un número correcto para asignar la nota.\n");
			}
		}

	}

	public void ordenarNotas() {

		int[] arrayNotasTemp = this.getArrayNotas().clone();
		this.setArrayNotasOrdenadas(new int[this.getArrayNotas().length]);

		int temp = 0;

		for (int a = 0; a < this.getArrayNotas().length; a++) {
			for (int i = 0; i < arrayNotasTemp.length; i++) {
				if (i == 0) {
					temp = arrayNotasTemp[0];
				}
				if (arrayNotasTemp[i] < temp) {
					temp = arrayNotasTemp[i];
				}
			}

			arrayNotasTemp = this.quitarNota(arrayNotasTemp, temp);
			this.getArrayNotasOrdenadas()[a] = temp;
		}

	}

	public int[] quitarNota(int[] arrayNotas, int nota) {

		int[] notasFinal = new int[arrayNotas.length - 1];
		boolean coincidence = false;

		int c = 0;
		for (int n : arrayNotas) {
			if (!coincidence && n == nota) {
				coincidence = true;
				continue;
			}
			notasFinal[c] = n;
			c++;
		}

		return notasFinal;

	}

	public void calcularMedia() {

		this.setNotaMedia(0);

		if (this.getArrayNotas().length > 0) {

			for (int nota : this.getArrayNotas()) {
				this.setNotaMedia(this.getNotaMedia() + nota);
			}

			this.setNotaMedia(this.getNotaMedia() / this.getArrayNotas().length);

		}

	}

}