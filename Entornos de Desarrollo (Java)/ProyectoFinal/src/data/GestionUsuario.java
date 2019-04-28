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
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import data.Alojamiento.TipoAlojamiento;

public class GestionUsuario {

	protected BufferedReader br;
	protected BufferedWriter bw;
	protected File archivo;
	protected Scanner scan = new Scanner(System.in);
	protected List<Usuario> usuarios;
	protected List<Alojamiento> alojamientos;
	protected List<Reserva> reservas;
	final String route_csv = "./alojamientos.csv";
	final String delim_csv = ";";

	public enum FiltroAlojamiento {
		PRECIO_MAX, CAPACIDAD, CATEGORIA
	}

	public GestionUsuario() {
		usuarios = new ArrayList<Usuario>();
		alojamientos = new ArrayList<Alojamiento>();
		reservas = new ArrayList<Reserva>();
		usuarios.add(new Usuario("admin", "1234", true));
		cargarCsv();
	}

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

	public static boolean validaFecha(String date_param) {
		Boolean valid = true;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		try {
			Date date = dateFormat.parse(date_param.trim());
			Date current_date = new Date();
			if (current_date.after(date) || current_date.equals(date)) {
				valid = false;
				System.out.println("Error, debe elegir una fecha igual o posterior a la fecha actual.");
			}
		} catch (ParseException pe) {
			valid = false;
			System.out.println("Formato de fecha no válido. El formato debe ser: dd-mm-aaaa.");
		}
		return valid;
	}

}
