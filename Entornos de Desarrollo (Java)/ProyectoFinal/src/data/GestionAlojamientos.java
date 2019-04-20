package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GestionAlojamientos {

	BufferedReader br;
	BufferedWriter bw;
	File archivo;
	static Scanner scan = new Scanner(System.in);
	private List<Usuario> usuarios;
	private List<Alojamiento> alojamientos;
	final String route_csv = "./alojamientos.csv";
	final String delim_csv = ";";

	public enum FiltroAlojamiento {
		PRECIO_MAX, CAPACIDAD, CATEGORIA
	}

	public GestionAlojamientos() {
		usuarios = new ArrayList<Usuario>();
		usuarios.add(new Usuario("admin", "1234", true));
		cargarCsv();
		System.out.println();
	}

	// Comunes
	public Usuario getUsuario(String username, String password) {

		Usuario usuario = null;

		if (null != username && null != password)
			for (Usuario u : usuarios)
				if (u.getUsername().equals(username) && u.getPassword().equals(password))
					usuario = u;

		return usuario;

	}

	public Usuario loginUsuario() {

		System.out.print("Introduzca login de usuario: ");
		String username = scan.next();

		System.out.print("Introduzca password de usuario: ");
		String password = scan.next();

		return getUsuario(username, password);

	}

	public Usuario registrarUsuario() {

		System.out.print("Introduzca username para el nuevo usuario: ");
		String username = scan.next();

		System.out.print("Introduzca password para el nuevo usuario: ");
		String password = scan.next();

		Usuario usuario = null;
		if (Usuario.comprobarUsernamePassword(username, password)) {
			usuario = new Usuario(username, password);
			usuarios.add(usuario);
			System.out.println(String.format("El usuario (%s) ha sido registrado correctamente.", username));
		} else
			System.out.println("El alta ha fallado. El username y la password deben contener algún valor.");

		return usuario;
	}

	public void listarAlojamientos() {

		if (alojamientos.size() > 0) {
			int num = 1;
			for (Alojamiento a : alojamientos) {
				System.out.println(num + " - " + a.toString());
				num++;
			}
		} else
			System.out.println("No existe ningún alojamiento en la lista.");

	}

	public void guardarCsv() {

		try {
			archivo = new File(route_csv);

			bw = new BufferedWriter(new FileWriter(archivo));

			for (Alojamiento a : alojamientos) {
				bw.write(a.toCsv());
				bw.newLine();
			}

			bw.close();
		} catch (IOException e) {
			System.out.println(
					String.format("Ha ocurrido el siguiente error guardando el archivo .csv de alojamientos: %s", e));
		}

		System.out.println("Alojamientos guardados correctamente.");

	}

	public void cargarCsv() {

		alojamientos = new ArrayList<Alojamiento>();

		try {
			br = new BufferedReader(new FileReader(route_csv));

			String linea;
			while ((linea = br.readLine()) != null) {
				String[] split = linea.split(delim_csv);
				alojamientos.add(csvToAlojamiento(split));
			}
		} catch (IOException e) {
			System.out.println(
					String.format("Ha ocurrido el siguiente error cargando el archivo .csv de alojamientos: %s", e));
		}

		System.out.println("Alojamientos cargados correctamente.");

	}

	public Alojamiento csvToAlojamiento(String[] args) {
		Alojamiento alojamiento = null;
		if (args.length == 5) {
			if (args[0].equals("hotel")) {
				alojamiento = new Hotel(args[1], Double.parseDouble(args[2]), Integer.parseInt(args[3]),
						Boolean.parseBoolean(args[4]));
			} else if (args[0].equals("casarural")) {
				alojamiento = new CasaRural(args[1], Double.parseDouble(args[2]), Integer.parseInt(args[3]),
						Integer.parseInt(args[4]));
			}
		}

		return alojamiento;
	}

	// Usuarios Administradores
	public void AñadirAlojamiento() {

		Boolean valido = false;
		String opcion;
		do {
			System.out.println("\nSelecciona una opción para el alta del alojamiento:");
			System.out.println("1. Hotel");
			System.out.println("2. Casa Rural");

			opcion = scan.next();
			if (opcion.equals("1") || opcion.equals("2"))
				valido = true;
		} while (!valido);

		System.out.print("Introduzca el nombre del alojamiento: ");
		String nombre = scan.next();

		System.out.print("Introduzca el precio: ");
		Double precio;
		try {
			precio = scan.nextDouble();
		} catch (InputMismatchException e) {
			precio = 0.00;
		}

		int num_estrellas = 0;
		Boolean tienePiscina = false;
		int max_personas = 0;
		int num_habitaciones = 0;
		if (opcion.equals("1")) {
			System.out.print("Introduzca el número de estrellas (1-5): ");
			try {
				num_estrellas = scan.nextInt();
			} catch (InputMismatchException e) {
				num_estrellas = 0;
			}

			System.out.print("¿Tiene piscina? (SI / NO): ");
			tienePiscina = scan.next().equalsIgnoreCase("si") ? true : false;
		} else {
			System.out.print("Introduzca el máximo de personas: ");
			try {
				max_personas = scan.nextInt();
			} catch (InputMismatchException e) {
				max_personas = 0;
			}

			System.out.print("Introduzca número de habitaciones: ");
			try {
				num_habitaciones = scan.nextInt();
			} catch (InputMismatchException e) {
				num_habitaciones = 0;
			}
		}

		if (Alojamiento.comprobarNombre(nombre)) {
			if (opcion.equals("1"))
				alojamientos.add(new Hotel(nombre, precio, num_estrellas, tienePiscina));
			else
				alojamientos.add(new CasaRural(nombre, precio, max_personas, num_habitaciones));
			System.out.println(String.format("El alojamiento (%s) ha sido registrado correctamente.", nombre));
		} else
			System.out.println("El alta ha fallado. El nombre debe contener algún valor.");

	}

	public void eliminarAlojamientos() {

		System.out.print("Introduzca la posición del alojamiento de la lista para borrar: ");
		int pos;
		try {
			pos = scan.nextInt();
			try {
				alojamientos.remove(pos - 1);
				System.out.println(String.format("Alojamiento de la poscición %i eliminado correctamente.", pos));
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Error, debe introducir un número válido de la lista para poder eliminar.");
			}
		} catch (InputMismatchException e) {
			System.out.println("Para introducir una posición de la lista debe introducir un número entero.");
		}

	}

	// Usuarios Normales
	public <T> void buscarAlojamientos(Class<T> type, FiltroAlojamiento filtro) {

		if (type.equals(Alojamiento.class)) {
			switch (filtro) {
			case PRECIO_MAX:
				//
				break;
			default:
				//
				break;
			}
		} else if (type.equals(Hotel.class)) {

		} else if (type.equals(CasaRural.class)) {

		}

		listarAlojamientos();
	}

	public void verReservas() {

	}

	public void realizarReserva() {

	}

}
