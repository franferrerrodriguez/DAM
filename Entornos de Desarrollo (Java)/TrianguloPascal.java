package triangulo.pascal;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TrianguloPascal {
	public static void main(String[] args) {
		System.out.println("Triángulo de Pascal");
		pedirNumero();
		System.out.println("\nFin de programa!");
	}

	private static void pedirNumero() {
		Scanner reader = new Scanner(System.in);
		int filas = 0;
		do {
			System.out.print("Indica el número de filas: ");
			try {
				filas = reader.nextInt();
				if (filas <= 0) {
					System.out.println("Error. Debe introducir un número mayor a 0.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Error. Debe introducir un número válido.\n");
				reader.next();
			}
		} while (filas <= 0);
		reader.close();
		System.out.println();
		construirTriangulo(filas);
	}

	private static void construirTriangulo(int filas) {
		int[][] array = new int[filas][filas];
		for (int x = 0; x < filas; x++) {
			int y = 0;
			while (y < filas && y <= x) {
				if (y == 0 || y == x) {
					array[x][y] = 1;
				} else if (x > 1) {
					array[x][y] = array[x - 1][y - 1] + array[x - 1][y];
				}
				System.out.print(array[x][y] + " ");
				y++;
			}
			System.out.println();
		}
	}

}