package data;

import java.util.InputMismatchException;

import data.Alojamiento.TipoAlojamiento;

public class GestionUsuarioAdmin extends GestionUsuario {

	public void mostrarMenu() {

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
				AñadirAlojamiento();
				break;
			case "2":
				listarAlojamientos();
				break;
			case "3":
				eliminarAlojamientos();
				break;
			}
		} while (!opcion.equals("4"));

	}

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

}