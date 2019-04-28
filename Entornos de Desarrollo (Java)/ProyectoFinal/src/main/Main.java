package main;

import java.util.Scanner;

import data.GestionUsuario;
import data.GestionUsuarioAdmin;
import data.GestionUsuarioNormal;
import data.Usuario;

public class Main {

	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("¡Bienvenido al programa de reservas de alojamientos!\n");

		GestionUsuario gestionUsuario = new GestionUsuario();
		GestionUsuarioAdmin gestionUsuarioAdmin = new GestionUsuarioAdmin();
		GestionUsuarioNormal gestionUsuarioNormal = new GestionUsuarioNormal();

		Usuario usuario;
		do {
			String opcion;
			do {
				System.out.println("1. Login");
				System.out.println("2. Registrarse");

				opcion = scan.next();

			} while (!opcion.equals("1") && !opcion.equals("2"));

			if (opcion.equals("1"))
				usuario = gestionUsuario.loginUsuario();
			else
				usuario = gestionUsuario.registrarUsuario();

			if (usuario == null)
				System.out.println(
						"La combinación de username y password introducidos no existen, inténtelo de nuevo.\n");
		} while (null == usuario);

		System.out.println(String.format("\n¡Bienvenido %s!", usuario.getUsername()));

		if (usuario.isAdmin()) {
			gestionUsuarioAdmin.mostrarMenu();
		} else {
			gestionUsuarioNormal.mostrarMenu();
		}

		gestionUsuario.guardarCsv();

		System.out.println("\nEl programa ha finalizado.");

	}

}