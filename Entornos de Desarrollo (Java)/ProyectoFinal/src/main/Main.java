package main;

import java.util.Scanner;

import data.Alojamiento;
import data.Alojamiento.TipoAlojamiento;
import data.GestionAlojamientos;
import data.GestionAlojamientos.FiltroAlojamiento;
import data.Usuario;

public class Main {

	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("¡Bienvenido al programa de reservas de alojamientos!\n");

		// SEPARAR USUARIOS - USUARIOS ADMIN - USUARIOS NORMAL
		GestionAlojamientos gestionAlojamientos = new GestionAlojamientos();

		Usuario usuario;
		do {
			String opcion;
			do {
				System.out.println("1. Login");
				System.out.println("2. Registrarse");

				opcion = scan.next();

			} while (!opcion.equals("1") && !opcion.equals("2"));

			if (opcion.equals("1"))
				usuario = gestionAlojamientos.loginUsuario();
			else
				usuario = gestionAlojamientos.registrarUsuario();

			if (usuario == null)
				System.out.println(
						"La combinación de username y password introducidos no existen, inténtelo de nuevo.\n");
		} while (null == usuario);

		System.out.println(String.format("\n¡Bienvenido %s!", usuario.getUsername()));

		if (usuario.isAdmin()) {
			mostrarMenuAdmin(gestionAlojamientos);
		} else {
			mostrarMenu(gestionAlojamientos);
		}

		gestionAlojamientos.guardarCsv();

		System.out.println("\nEl programa ha finalizado.");

	}

	public static void mostrarMenuAdmin(GestionAlojamientos gestionAlojamientos) {

		String opcion;
		do {
			System.out.println("\n**** MENÚ ****");
			System.out.println("1. Dar de alta nuevo alojamiento");
			System.out.println("2. Listar alojamientos");
			System.out.println("3. Eliminar alojamiento");
			System.out.println("4. Salir del programa");

			System.out.print("\nIntroduce una opción del menú: ");
			opcion = scan.next();

			switch (opcion) {
			case "1":
				gestionAlojamientos.AñadirAlojamiento();
				break;
			case "2":
				gestionAlojamientos.listarAlojamientos();
				break;
			case "3":
				gestionAlojamientos.eliminarAlojamientos();
				break;
			}
		} while (!opcion.equals("4"));

	}

	public static void mostrarMenu(GestionAlojamientos gestionAlojamientos) {

		String opcion;
		do {
			System.out.println("\n**** MENÚ ****");
			System.out.println("1. Buscar alojamientos por tipo");
			System.out.println("2. Buscar alojamientos por precio máximo");
			System.out.println("3. Buscar casas rurales por capacidad");
			System.out.println("4. Buscar hoteles por categoría");
			System.out.println("5. Ver reservas");
			System.out.println("6. Reservar");
			System.out.println("7. Salir del programa");

			System.out.print("\nIntroduce una opción del menú: ");
			opcion = scan.next();

			switch (opcion) {
			case "1":
				gestionAlojamientos.buscarAlojamientos(null, null);
				break;
			case "2":
				TipoAlojamiento tipoAlojamiento = gestionAlojamientos.pedirTipoAlojamiento();
				gestionAlojamientos.buscarAlojamientos(tipoAlojamiento, FiltroAlojamiento.PRECIO_MAX);
				break;
			case "3":
				gestionAlojamientos.buscarAlojamientos(Alojamiento.TipoAlojamiento.CASARURAL,
						FiltroAlojamiento.CAPACIDAD);
				break;
			case "4":
				gestionAlojamientos.buscarAlojamientos(Alojamiento.TipoAlojamiento.HOTEL, FiltroAlojamiento.CATEGORIA);
				break;
			case "5":
				gestionAlojamientos.verReservas();
				break;
			case "6":
				gestionAlojamientos.realizarReserva();
				break;
			}
		} while (!opcion.equals("7"));

	}

}