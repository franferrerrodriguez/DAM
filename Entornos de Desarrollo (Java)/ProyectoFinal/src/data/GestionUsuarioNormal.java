package data;

import java.util.List;
import java.util.stream.Collectors;

import data.Alojamiento.TipoAlojamiento;

public class GestionUsuarioNormal extends GestionUsuario {

	public void mostrarMenu() {

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
				buscarAlojamientos(null, null);
				break;
			case "2":
				TipoAlojamiento tipoAlojamiento = pedirTipoAlojamiento();
				buscarAlojamientos(tipoAlojamiento, FiltroAlojamiento.PRECIO_MAX);
				break;
			case "3":
				buscarAlojamientos(Alojamiento.TipoAlojamiento.CASARURAL, FiltroAlojamiento.CAPACIDAD);
				break;
			case "4":
				buscarAlojamientos(Alojamiento.TipoAlojamiento.HOTEL, FiltroAlojamiento.CATEGORIA);
				break;
			case "5":
				verReservas();
				break;
			case "6":
				realizarReserva();
				break;
			}
		} while (!opcion.equals("7"));

	}

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

}