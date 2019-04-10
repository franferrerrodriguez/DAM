package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import data.Agricultor;
import data.Ganadero;
import data.Personaje;

public class Ejercicio {

	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		// Introducimos personajes en una lista
		List<Personaje> personajes = new ArrayList<Personaje>();
		personajes.add(new Agricultor("Pepe", 34, "Naranjos", "Azada", 1));
		personajes.add(new Agricultor("Miguel Ángel", 42, "Limoneros", "Legón", 8));
		personajes.add(new Ganadero("Antonio", 27, "Caballos", "Todoterreno", 4));
		personajes.add(new Agricultor("Luis", 38, "Almendros", "Horca", 6));
		personajes.add(new Ganadero("José", 45, "Vacas lecheras", "Camión", 2));
		personajes.add(new Agricultor("Susana", 41, "Tomates", "Pala", 5));
		personajes.add(new Ganadero("Raquel", 24, "Ponis", "Furgoneta", 3));

		// Menú
		String tecla;
		do {
			System.out.println("------------------- Menú -------------------");
			System.out.println("1. Listado sin ordenar.");
			System.out.println("2. Listado ordenado por edades.");
			System.out.println("3. Generar fichero ordenado de agricultores.");
			System.out.println("4. Generar fichero ordenado de ganaderos.");
			System.out.println("5. Buscar por nombre.\n");
			System.out.println("0. Salir.");
			System.out.println("--------------------------------------------");

			System.out.print("Elige una opción del menú: ");

			tecla = scan.next();

			switch (tecla) {
			case "1":
				imprimir(Personaje.tiposOrdenacion.NO, personajes);
				break;
			case "2":
				imprimir(Personaje.tiposOrdenacion.EDAD, personajes);
				break;
			case "3":
				generarFicheroAgricultorGanadero(Agricultor.class, personajes);
				break;
			case "4":
				generarFicheroAgricultorGanadero(Ganadero.class, personajes);
				break;
			case "5":
				buscarNombre(personajes);
				break;
			}

		} while (!tecla.equals("0"));

		scan.close();

		System.out.println("Fin");
	}

	static void imprimir(Personaje.tiposOrdenacion ordenacion, List<Personaje> personajes) {

		switch (ordenacion) {
		case EDAD:
			Collections.sort(personajes);
			break;
		default:
			break;
		}

		for (Personaje pj : personajes)
			System.out.println(pj.toString());

	}

	public static void generarFicheroAgricultorGanadero(Class<?> tipo_class, List<Personaje> personajes) {

		String nombre_clase = String.format("%s", tipo_class.getSimpleName().toLowerCase());
		String nombre_archivo = String.format("%s.txt", nombre_clase);
		String ruta = String.format("./%s", nombre_archivo);
		File archivo = new File(ruta);
		BufferedWriter bw;

		Collections.sort(personajes);

		try {
			bw = new BufferedWriter(new FileWriter(archivo));

			bw.write(tipo_class == Agricultor.class ? "[AGRICULTORES]"
					: (tipo_class == Ganadero.class) ? "[GANADEROS]" : "");
			for (Personaje pj : personajes)
				if (pj.getClass().equals(tipo_class)){
                                    bw.newLine();
                                    bw.write(pj.toString());
                                }
					

			System.out.println(String.format("Fichero %s generado correctamente.\n", nombre_archivo));

			bw.close();
		} catch (IOException e) {
			System.out.println(String.format("Ha ocurrido la siguiente excepción: %s\n", e));
		}

	}

	public static void buscarNombre(List<Personaje> personajes) {

		Boolean coincidence = false;

		System.out.print("Introduce el nombre o parte a buscar: ");

		String cadena = scan.next();

		for (Personaje pj : personajes)
			if (pj.getNombre().toLowerCase().contains(cadena.toLowerCase())) {
				coincidence = true;
				System.out.println(pj.toString());
			}

		if (!coincidence)
			System.out.println("No se ha encontrado ninguna coincidencia.\n");
		else
			System.out.println();

	}

}