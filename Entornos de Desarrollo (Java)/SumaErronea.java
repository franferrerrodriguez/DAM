package suma.erronea;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SumaErronea {

	public static void main(String[] args) {

		Scanner reader = new Scanner(System.in);

		try {
			System.out.println("Introduce la lista de números:");
			String lista = reader.nextLine();
			int[] listaArray = Arrays.stream(lista.split(" ")).mapToInt(Integer::parseInt).toArray();

			reader = new Scanner(System.in);
			System.out.println("Introduce el valor de la suma:");
			int suma = reader.nextInt();

			int resultado = 0;
			int iteracion = 0;

			// Coincide será true cuando la suma de los elementos de la lista (menos 2 de
			// ellos) sea igual al número de suma introducido
			Boolean coincide = false;

			for (int x = 0; x < listaArray.length && !coincide; x++) {

				for (int y = 0; y < listaArray.length && !coincide; y++) {

					resultado = 0;

					if (y != x) {

						iteracion = 0;
						for (int a = 0; a < listaArray.length && !coincide; a++) {

							if (a != x && a != y) {

								resultado += listaArray[a];

								if (resultado == suma && iteracion == listaArray.length - 3) {
									coincide = true;
									System.out.println(String.format("Para la suma %s sobran el %s y el %s.", suma,
											listaArray[x], listaArray[y]));
								}

								iteracion++;

							}

						}

					}

				}

			}

			if (!coincide) {
				System.out.println(String
						.format("No se han encontrado coincidencias en la lista de números para la suma: %s.", suma));
			}
		} catch (NumberFormatException e) {
			System.out.println("\nError. Ha introducido un parámetro no válido en la lista de números.");
		} catch (InputMismatchException e) {
			System.out.println("\nError. Ha introducido un parámetro no válido en el valor de la suma.");
		} catch (Exception e) {
			System.out.println(String.format("Ha ocurrido la siguiente excepción no controlada: %s.", e));
		}

	}

}
