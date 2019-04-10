package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

import data.Estado;

public class Main {

	public static void main(String[] args) {

		GestionEstados gestionEstados = new GestionEstados();

		gestionEstados.start();
	}

}

class GestionEstados {

	private List<Estado> estados;
	private BufferedReader br;
	private BufferedWriter bw;
	private File archivo;
	private Scanner scan = new Scanner(System.in);
	private final String route_csv = "./states.csv";
	private final String delim_csv = ";";
	private final String fin = "\nFin de programa";
	private int num_lineas = 0;
	private int num_coleccion = 0;

	public GestionEstados() {
		cargarCsv();
	}

	public void start() {
		String opcion;
		do {
			System.out.println("--------- Menú ---------");
			System.out.println("1. Muestra la colección");
			System.out.println("2. Buscar por código");
			System.out.println("3. Eliminar estado");
			System.out.println("------------------------");
			System.out.println("0. Salir\n");
			System.out.print("Elige una opción del menú: ");

			opcion = scan.next();

			switch (opcion) {
			case "1":
				mostrarColeccion();
				break;
			case "2":
				buscarPorCodigo();
				break;
			case "3":
				borrarPorCodigo();
				break;
			}

			if (!opcion.equals("1") && !opcion.equals("2") && !opcion.equals("3") && !opcion.equals("0"))
				System.out.println("\nOpción incorrecta!\n");

		} while (!opcion.equals("0"));

		System.out.println(fin);

	}

	public void cargarCsv() {

		num_coleccion = 0;
		num_lineas = 0;
		estados = new ArrayList<Estado>();

		try {
			br = new BufferedReader(new FileReader(route_csv));

			Estado estado;
			String linea;

			while ((linea = br.readLine()) != null) {

				String[] split = linea.split(delim_csv);
				estado = csvToEstado(split);

				estados.add(estado);

				num_coleccion++;

				num_lineas++;
			}
		} catch (IOException e) {
			System.out.println(
					String.format("Ha ocurrido el siguiente error cargando el archivo .csv de estados: %s", e));
		}

	}

	public void eliminarEstadosRepetidos() {

		HashMap<String, Estado> mapEstados = new HashMap<String, Estado>(estados.size());

		for (Estado e : estados) {
			mapEstados.put(e.getCodigo(), e);
		}

		estados.clear();
		for (Entry<String, Estado> p : mapEstados.entrySet()) {
			estados.add(p.getValue());
		}

		num_coleccion = estados.size();
	}

	public Estado csvToEstado(String[] args) {

		Estado estado = null;

		if (args.length == 2)
			estado = new Estado(args[0], args[1]);

		return estado;
	}

	public Boolean comprobarRepetido(Estado estado) {

		Boolean repetido = false;

		return repetido;
	}

	// OPCION 1
	public void mostrarColeccion() {

		eliminarEstadosRepetidos();

		for (Estado estado : estados) {
			System.out.println(estado.toString());
		}

		System.out.println(String.format("\nLíneas leídas de fichero: %s", num_lineas));
		System.out.println(String.format("Líneas almacenadas en la colección: %s\n", num_coleccion));

	}

	// OPCION 2
	public void buscarPorCodigo() {

		System.out.print("Introduce el código del estado (XX): ");

		String codigo = scan.next();

		Estado estado = getEstadoByCodigo(codigo);
		if (estado != null)
			System.out.println(String.format("El estado es: %s\n", estado.getNombre()));
		else
			System.out.println("No existe el código buscado.\n");
	}

	// OPCION 3
	public void borrarPorCodigo() {

		System.out.print("Introduce el código del estado a borrar (XX): ");

		String codigo = scan.next();

		Estado estado = getEstadoByCodigo(codigo);
		if (estado != null) {
			estados.remove(estado);
			System.out.println(String.format("Borrado: %s\n", estado.getNombre()));
		} else
			System.out.println("No existe el código buscado.\n");
	}

	public Estado getEstadoByCodigo(String codigo) {

		Estado estado = null;

		if (Estado.comprobarCodigo(codigo)) {
			for (Estado e : estados) {
				if (e.getCodigo().equalsIgnoreCase(codigo)) {
					estado = e;
					break;
				}
			}
		}

		return estado;
	}

}
