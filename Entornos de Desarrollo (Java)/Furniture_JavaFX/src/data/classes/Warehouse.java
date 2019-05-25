/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Francisco J. Ferrer
 */
public class Warehouse {

	BufferedReader br;
	BufferedWriter bw;
	File archivo;
	String delimiter = ";";
	private String name = null;
	private Map<String, Furniture> products = new HashMap<>();

	/**
	 * Constructor con parámetros.
	 * 
	 * @param name String correspondiente al nombre del almacén
	 */
	public Warehouse(String name) {
		this.name = name;
	}

	/**
	 *
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 *
	 * @return products
	 */
	public Map<String, Furniture> getProducts() {
		return products;
	}

	/**
	 * Leerá los datos del fichero pasado como parámetro para crear el mapa
	 *
	 * @param file
	 */
	public void loadFile(String file) {

		try {
			br = new BufferedReader(new FileReader(file));
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] split = linea.split(delimiter);
				try {
					if (!addProduct(csvToFurniture(split)))
						System.out.println("Ya existe un Furniture con este modelo en la lista.");
				} catch (NumberFormatException | IndexOutOfBoundsException e) {
					System.out.println("Error al mapear el objeto Furniture." + e.getMessage());
				}
			}
		} catch (IOException e) {
			System.out.println(
					String.format("Ha ocurrido el siguiente error cargando el archivo .csv de Furnitures: %s", e));
		}

	}

	/**
	 * Convertirá una línea del formato CSV a un objeto de tipo Furniture
	 *
	 * @param String[] args
	 * 
	 * @return Furniture
	 */
	public static Furniture csvToFurniture(String[] args) throws NumberFormatException, IndexOutOfBoundsException {

		Furniture furniture = null;
		try {
			if (args.length == 10 && args[0].equals("chair")) {
				furniture = new Chair(args[1], args[2], args[3], args[4], Double.parseDouble(args[5]),
						Double.parseDouble(args[6]), Double.parseDouble(args[7]), Integer.parseInt(args[8]), true);
			} else if (args.length == 9 && args[0].equals("table")) {
				furniture = new Table(args[1], args[2], args[3], args[4], Double.parseDouble(args[5]),
						Double.parseDouble(args[6]), Double.parseDouble(args[7]), Integer.parseInt(args[8]));
			} else if (args.length == 11 && args[0].equals("wardrobe")) {
				furniture = new Wardrobe(args[1], args[2], args[3], args[4], Double.parseDouble(args[5]),
						Double.parseDouble(args[6]), Double.parseDouble(args[7]), Integer.parseInt(args[8]),
						Integer.parseInt(args[9]), Integer.parseInt(args[10]));
			} else if (args.length == 9 && args[0].equals("desk")) {
				furniture = new Desk(args[1], args[2], args[3], args[4], Double.parseDouble(args[5]),
						Double.parseDouble(args[6]), Double.parseDouble(args[7]), Integer.parseInt(args[8]));
			}
		} catch (NumberFormatException e) {
			throw new NumberFormatException();
		} catch (IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException();
		}

		return furniture;

	}

	/**
	 * Guardará el mapa en el fichero, sobrescribe el fichero
	 *
	 * @param file
	 */
	public void saveFile(String file) {

		try {
			archivo = new File(file);
			bw = new BufferedWriter(new FileWriter(archivo));
			for (Map.Entry<String, Furniture> entry : products.entrySet()) {
				bw.write(entry.getValue().toCsv());
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			System.out.println(
					String.format("Ha ocurrido el siguiente error guardando el archivo .csv de alojamientos: %s", e));
		}

	}

	/**
	 * Muestra los datos del producto indicado por el modelo. No pueden existir dos
	 * productos cuyo modelo sea el mismo en un almacén.
	 *
	 * @param model
	 */
	public void showProduct(String model) {

		boolean result = false;

		for (Map.Entry<String, Furniture> entry : products.entrySet())
			if (entry.getValue().getModel().equals(model)) {
				result = true;
				System.out.println(entry.getValue().toString());
			}

		if (!result)
			System.out.println("No existen productos en el almacén: " + name);

	}

	/**
	 * Pide al usuario un almacén y devolverá el index del mismo
	 *
	 * @return option
	 */
	public static int requestIndexWarehouse() {

		Scanner scanner;
		int option;
		do {
			System.out.println("Selecciona un almacén:");
			System.out.println("1. Almacén A");
			System.out.println("2. Almacén B");
			scanner = new Scanner(System.in);
			option = Integer.parseInt(scanner.next());
		} while (option != 1 && option != 2);

		return option - 1;

	}

	/**
	 * Pide al usuario un modelo y devolverá el String del introducido
	 *
	 * @return option
	 */
	public static String requestModel() {

		Scanner scanner;
		String option;

		do {
			System.out.print("Introduce un modelo: ");
			scanner = new Scanner(System.in);
			option = scanner.next();
		} while (option.equals(""));

		return option;

	}

	/**
	 * Añade un objeto Furniture a la lista de productos Furniture devoviendo un
	 * booleano de la operación
	 *
	 * @param furniture
	 *
	 * @return result
	 */
	public boolean addProduct(Furniture furniture) {

		boolean result = false;
		if (furniture != null && !existsModel(furniture.getModel())) {
			result = true;
			products.put(furniture.getModel().toUpperCase(), furniture);
		}

		return result;

	}

	/**
	 * Comprueba con la lista de productos Furniture si existe el modelo pasado como
	 * parámetro devoviendo un booleano de la operación
	 *
	 * @param model
	 *
	 * @return result
	 */
	public boolean existsModel(String model) {

		boolean result = false;
		for (Map.Entry<String, Furniture> entry : products.entrySet())
			if (entry.getValue().getModel().equals(model)) {
				result = true;
				break;
			}

		return result;

	}

	/**
	 * Elimina un objeto Furniture de la lista de productos Furniture devoviendo un
	 * booleano de la operación
	 *
	 * @param model
	 *
	 * @return result
	 */
	public boolean delProduct(String model) {

		boolean result = false;
		if (products.containsKey(model)) {
			result = true;
			products.remove(model);
		}

		return result;

	}

	/**
	 * Muestra un listado de los productos almacenados en la lista de Furniture
	 */
	public void listProducts() {
		for (Map.Entry<String, Furniture> entry : products.entrySet())
			System.out.println(entry.getValue().toString());
	}

	/**
	 * Muestra un mensaje de alerta
	 *
	 * @param contenido
	 * @param tipo
	 *
	 * @return Optional<ButtonType>
	 */
	public static Optional<ButtonType> dialogo(String header, String contenido, Alert.AlertType tipo) {

		Alert a1 = new Alert(tipo);
		String titulo = "";
		switch (tipo) {
		case CONFIRMATION:
			titulo = "Confirmación";
			break;
		case INFORMATION:
			titulo = "Información";
			break;
		case ERROR:
			titulo = "Error";
			break;
		case NONE:
			titulo = "";
			break;
		case WARNING:
			titulo = "Advertencia";
			break;
		}
		a1.setTitle(titulo);
		a1.setHeaderText(header);
		a1.setContentText(contenido);

		return a1.showAndWait();

	}

}