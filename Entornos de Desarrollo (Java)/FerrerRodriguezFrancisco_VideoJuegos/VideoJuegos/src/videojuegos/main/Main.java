package videojuegos.main;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import videojuegos.data.Compania;
import videojuegos.data.PCVideoJuego;
import videojuegos.data.VideoJuego;

public class Main {

	static Scanner reader;

	public static void main(String[] args) {

		PCVideoJuego[] juegos = new PCVideoJuego[5];

		Boolean retry;

		String titulo;

		String genero;

		Double precio = 0.00;

		Compania[] companias = { new Compania("EA", "2018-01-01"), new Compania("Riot", "2014-06-05"),
				new Compania("Blizzard", "2012-12-11") };

		int[] aleatorios = new int[juegos.length];

		for (int i = 0; i < 5; i++) {
			retry = false;

			System.out.println(String.format("\nIntroduce título para el juego %s:", i + 1));

			reader = new Scanner(System.in);
			titulo = reader.next();

			System.out.println(String.format("Introduce género para el juego %s:", i + 1));
			reader = new Scanner(System.in);
			genero = reader.next();

			while (!retry) {
				reader = new Scanner(System.in);
				try {
					System.out.println(String.format("Introduce precio para el juego %s:", i + 1));
					precio = reader.nextDouble();
					retry = true;
				} catch (InputMismatchException e) {
					System.out.println("Debe introducir una cifra válida para el precio del juego\n");
				}
			}

			Random random = new Random(System.currentTimeMillis());
			aleatorios[i] = (int) random.nextInt(companias.length);
			juegos[i] = new PCVideoJuego(titulo, genero, precio, companias[aleatorios[i]]);

		}

		System.out.println(
				"\n--------------------------------\nEl número total de juegos es: " + PCVideoJuego.getNumero_juegos());

		VideoJuego juego_mas_barato = VideoJuego.juegoMasBarato(juegos);
		System.out.println("\nEl juego más barato es: " + juego_mas_barato.toString());

		VideoJuego juego_mas_caro = VideoJuego.juegoMasCaro(juegos);
		System.out.println("\nEl juego más caro es: " + juego_mas_caro.toString());

		System.out.println("\nLa compañía de juegos que más aparece es: "
				+ companias[VideoJuego.numeroMasRepetido(aleatorios)].toString());
	}

}