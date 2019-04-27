package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import data.Alojamiento.TipoAlojamiento;

public class GestionAlojamientos {

	BufferedReader br;
	BufferedWriter bw;
	File archivo;
	static Scanner scan = new Scanner(System.in);
	private List<Usuario> usuarios;
	private List<Alojamiento> alojamientos;
	private List<Reserva> reservas;
	final String route_csv = "./alojamientos.csv";
	final String delim_csv = ";";

	public enum FiltroAlojamiento {
		PRECIO_MAX, CAPACIDAD, CATEGORIA
	}

	public GestionAlojamientos() {
		usuarios = new ArrayList<Usuario>();
		alojamientos = new ArrayList<Alojamiento>();
		reservas = new ArrayList<Reserva>();
		usuarios.add(new Usuario("admin", "1234", true));
		cargarCsv();
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
			System.out.println("\nListado de Alojamientos:");
			int num = 1;
			for (Alojamiento alojamiento : alojamientos) {
				System.out.println(num + " - " + alojamiento.toString());
				num++;
			}
		} else
			System.out.println("No existe ningún alojamiento en la lista.");

	}

	public void listarAlojamientos(List<Alojamiento> alojamientos_param) {

		if (alojamientos_param.size() > 0) {
			System.out.println("\nListado de Alojamientos:");
			int num = 1;
			for (Alojamiento alojamiento : alojamientos) {
				for (Alojamiento alojamiento_param : alojamientos_param)
					if (alojamiento.equals(alojamiento_param))
						System.out.println(num + " - " + alojamiento_param.toString());
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

		alojamientos.clear();

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

	public TipoAlojamiento pedirTipoAlojamiento() {
		String text;
		TipoAlojamiento tipoAlojamiento = null;

		do {
			System.out.println("\nSelecciona tipo de alojamiento:");
			System.out.println("1. Hotel");
			System.out.println("2. Casa Rural");

			text = scan.next();
			if (text.equals("1"))
				tipoAlojamiento = Alojamiento.TipoAlojamiento.HOTEL;
			else if (text.equals("2"))
				tipoAlojamiento = Alojamiento.TipoAlojamiento.CASARURAL;
		} while (tipoAlojamiento == null);

		return tipoAlojamiento;
	}

	// Usuarios Administradores
	public void AñadirAlojamiento() {

		TipoAlojamiento tipoAlojamiento = pedirTipoAlojamiento();

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
		if (tipoAlojamiento.equals(TipoAlojamiento.HOTEL)) {
			System.out.print("Introduzca el número de estrellas (1-5): ");
			try {
				num_estrellas = scan.nextInt();
			} catch (InputMismatchException e) {
				num_estrellas = 0;
			}

			System.out.print("¿Tiene piscina? (SI / NO): ");
			tienePiscina = scan.next().equalsIgnoreCase("si") ? true : false;
		} else if (tipoAlojamiento.equals(TipoAlojamiento.CASARURAL)) {
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
			if (tipoAlojamiento.equals(TipoAlojamiento.HOTEL))
				alojamientos.add(new Hotel(nombre, precio, num_estrellas, tienePiscina));
			else if (tipoAlojamiento.equals(TipoAlojamiento.CASARURAL))
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
	public void buscarAlojamientos(TipoAlojamiento buscarTipo, FiltroAlojamiento filtro) {

		List<Alojamiento> alojamientos_tmp = alojamientos;

		if (buscarTipo != null)
			alojamientos_tmp = alojamientos.stream().filter(x -> buscarTipo.equals(x.getTipo()))
					.collect(Collectors.toList());

		Boolean valido = false;
		if (filtro != null) {
			switch (filtro) {
			case PRECIO_MAX:
				double precio_max = 0;
				do {
					System.out.println("\nIntroduce el importe máximo de alojamientos a mostrar:");
					try {
						precio_max = Double.parseDouble(scan.next());
						valido = true;
					} catch (NumberFormatException e) {
						System.out.println("Error, debe introducir un número para el importe máximo.");
					}
				} while (!valido);
				double pre = precio_max;
				alojamientos_tmp = alojamientos_tmp.stream().filter(x -> (x.getPrecio() <= pre))
						.collect(Collectors.toList());
				break;
			case CAPACIDAD:
				int capacidad = 0;
				do {
					System.out.println("\nIntroduce el máximo de personas de alojamientos mostrar:");
					try {
						capacidad = Integer.parseInt(scan.next());
						valido = true;
					} catch (NumberFormatException e) {
						System.out.println("Error, debe introducir un número para el máximo de personas.");
					}
				} while (!valido);
				int cap = capacidad;
				alojamientos_tmp = alojamientos_tmp.stream().filter(x -> (cap >= x.getMaxPersonas()))
						.collect(Collectors.toList());
				break;
			case CATEGORIA:
				int categoria = 0;
				do {
					System.out.println("\nIntroduce el número de estrellas máximo de alojamientos a mostrar:");
					try {
						categoria = Integer.parseInt(scan.next());
						valido = true;
					} catch (NumberFormatException e) {
						System.out.println("Error, debe introducir un número para el número de estrellas.");
					}
				} while (!valido);
				int cat = categoria;
				alojamientos_tmp = alojamientos_tmp.stream().filter(x -> (cat >= x.getNumEstrellas()))
						.collect(Collectors.toList());
				break;
			}
		}

		listarAlojamientos(alojamientos_tmp);
	}

	public void verReservas() {
		if (reservas.size() > 0) {
			System.out.println("\nListado de reservas:");
			for (Reserva reserva : reservas)
				System.out.println(reserva.toString());
		} else
			System.out.println("No existe ninguna reserva en la lista.");
	}

	public void realizarReserva() {

		Alojamiento alojamiento = null;
		Boolean valido = false;
		int posicion_reserva = 0;
		do {
			System.out.println("\nIntroduce el número de la posición del alojamiento a reservar:");
			try {
				posicion_reserva = Integer.parseInt(scan.next()) - 1;
				alojamiento = alojamientos.get(posicion_reserva);
				if (!alojamiento.getReservado()) {
					alojamiento.setReservado(true);
					valido = true;
				} else
					System.out.println("Error, el alojamiento seleccionado ya se encuentra reservado.");
			} catch (NumberFormatException e) {
				System.out.println("Error, debe introducir un número de posición del alojamiento a reservar.");
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Error, la posición introducida no existe en la lista de alojamientos.");
			}
		} while (!valido);

		valido = false;
		String fecha_entrada = "";
		do {
			System.out.println("\nIntroduce fecha de entrada al alojamiento (dd-mm-aaaa):");
			fecha_entrada = scan.next();
			if (validaFecha(fecha_entrada))
				valido = true;
		} while (!valido);

		valido = false;
		int num_dias = 0;
		do {
			System.out.println("\nIntroduce el número de días a reservar:");
			try {
				num_dias = Integer.parseInt(scan.next());
				valido = true;
			} catch (NumberFormatException e) {
				System.out.println("Error, debe introducir un número de días a reservar.");
			}
		} while (!valido);

		reservas.add(new Reserva(fecha_entrada, num_dias, alojamiento));
	}

	public static boolean validaFecha(String date) {
		Boolean valid = true;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(date.trim());
		} catch (ParseException pe) {
			valid = false;
			System.out.println("Formato de fecha no válido. El formato debe ser (dd-mm-aaaa).");
		}
		return valid;
	}

}
