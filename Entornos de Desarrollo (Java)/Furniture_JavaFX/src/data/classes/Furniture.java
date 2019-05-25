/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.classes;

import java.util.Scanner;

/**
 *
 * @author Francisco J. Ferrer
 */
public abstract class Furniture implements Comparable<Furniture> {

	private String model = null;
	private String manufacturer = null;
	private String mainMaterial = null;
	private String mainColor = null;
	private double width = 0;
	private double high = 0;
	private double depth = 0;

	/**
	 * Constructor con parámetros.
	 * 
	 * @param model        String correspondiente al modelo
	 * @param manufacturer String correspondiente al fabricante
	 * @param mainMaterial String correspondiente al material
	 * @param mainColor    String correspondiente al color
	 * @param width        double correspondiente a la anchura
	 * @param high         double correspondiente a la altura
	 * @param depth        double correspondiente a la profundidad
	 */
	public Furniture(String model, String manufacturer, String mainMaterial, String mainColor, double width,
			double high, double depth) {
		this.model = model;
		this.manufacturer = manufacturer;
		this.mainMaterial = mainMaterial;
		this.mainColor = mainColor;
		this.width = width;
		this.high = high;
		this.depth = depth;
	}

	/**
	 *
	 * @return model
	 */
	public String getModel() {
		return model;
	}

	/**
	 *
	 * @param model
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 *
	 * @return manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 *
	 * @param manufacturer
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 *
	 * @return mainMaterial
	 */
	public String getMainMaterial() {
		return mainMaterial;
	}

	/**
	 *
	 * @param mainMaterial
	 */
	public void setMainMaterial(String mainMaterial) {
		this.mainMaterial = mainMaterial;
	}

	/**
	 *
	 * @return mainColor
	 */
	public String getMainColor() {
		return mainColor;
	}

	/**
	 *
	 * @param mainColor
	 */
	public void setMainColor(String mainColor) {
		this.mainColor = mainColor;
	}

	/**
	 *
	 * @return width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 *
	 * @param width
	 */
	public void setWidth(double width) {
		if (width > 0)
			this.width = width;
	}

	/**
	 *
	 * @return high
	 */
	public double getHigh() {
		return high;
	}

	/**
	 *
	 * @param high
	 */
	public void setHigh(double high) {
		if (high > 0)
			this.high = high;
	}

	/**
	 *
	 * @return depth
	 */
	public double getDepth() {
		return depth;
	}

	/**
	 *
	 * @param depth
	 */
	public void setDepth(double depth) {
		if (depth > 0)
			this.depth = depth;
	}

	/**
	 *
	 * @return hashCode del modelo
	 */
	@Override
	public int hashCode() {
		return model.hashCode();
	}

	/**
	 * @param furniture
	 *
	 * @return equals entre 2 objetos Furniture
	 */
	@Override
	public boolean equals(Object furniture) {
		return (this.model.equals(((Furniture) furniture).getModel())
				&& this.manufacturer.equals(((Furniture) furniture).getManufacturer()));
	}

	/**
	 * @param furniture
	 *
	 * @return compareTo entre 2 objetos Furniture
	 */
	public int compareTo(Furniture furniture) {

		if (volumen() > (furniture.volumen()))
			return 1;
		else if (volumen() < (furniture.volumen()))
			return -1;
		else
			return 0;

	}

	/**
	 *
	 * @return volumen
	 */
	public double volumen() {
		double volumen = -1;

		if (width > 0 && high > 0 && depth > 0)
			volumen = this.width * this.high * this.depth;

		return volumen;
	}

	public String toCsv() {
		return "";
	}

	/**
	 * Pide al usuario que indique el tipo de Furniture
	 *
	 * @return Class<?>
	 */
	@SuppressWarnings("resource")
	public static Class<?> requestTypeFurniture() {

		Scanner scanner;
		String option;
		Class<?> type = null;
		boolean valid = false;
		do {
			System.out.println("Selecciona un mueble:");
			System.out.println("1. Silla");
			System.out.println("2. Mesa");
			System.out.println("3. Armario");
			System.out.println("4. Escritorio");
			scanner = new Scanner(System.in);
			option = scanner.next();
			switch (option) {
			case "1":
				valid = true;
				type = Chair.class;
				break;
			case "2":
				valid = true;
				type = Table.class;
				break;
			case "3":
				valid = true;
				type = Wardrobe.class;
				break;
			case "4":
				valid = true;
				type = Desk.class;
				break;
			}
		} while (!valid);

		return type;

	}

	/**
	 * Pide al usuario los parámetros necesario para añadir un objeto de tipo
	 * Furniture a la lista de productos
	 * 
	 * @param Class<?> type
	 *
	 * @return Furniture
	 */
	public static Furniture requestFurniture(Class<?> type) {

		Furniture furniture = null;
		if (validFurniture(type)) {

			String model = requestString("Introduce el modelo: ");
			String manufacturer = requestString("Introduce el fabricante: ");
			String mainMaterial = requestString("Introduce el material: ");
			String mainColor = requestString("Introduce el color: ");
			int width = requestInt("Introduce la anchura: ");
			int high = requestInt("Introduce la altura: ");
			int depth = requestInt("Introduce la profundidad: ");

			if (type.equals(Chair.class)) {

				int foots = requestInt("Introduce el número de patas: ");
				boolean backrest = requestBoolean("¿Tiene respaldo?");

				furniture = new Chair(model, manufacturer, mainMaterial, mainColor, width, high, depth, foots,
						backrest);

			} else if (type.equals(Table.class)) {

				int foots = requestInt("Introduce el número de patas: ");

				furniture = new Table(model, manufacturer, mainMaterial, mainColor, width, high, depth, foots);

			} else if (type.equals(Wardrobe.class)) {

				int foots = requestInt("Introduce el número de patas: ");
				int doors = requestInt("Introduce el número de puertas: ");
				int drawers = requestInt("Introduce el número de cajones: ");

				furniture = new Wardrobe(model, manufacturer, mainMaterial, mainColor, width, high, depth, foots, doors,
						drawers);

			} else if (type.equals(Desk.class)) {

				int drawers = requestInt("Introduce el número de cajones: ");

				furniture = new Desk(model, manufacturer, mainMaterial, mainColor, width, high, depth, drawers);

			}

		}

		return furniture;

	}

	public static String requestString(String title) {
		Scanner scanner;
		String string;
		do {
			System.out.print(title);
			scanner = new Scanner(System.in);
			string = scanner.next();
		} while (string.equals(""));
		return string;
	}

	public static int requestInt(String title) {
		Scanner scanner;
		int _int = -1;
		do {
			System.out.print(title);
			scanner = new Scanner(System.in);
			try {
				_int = Integer.parseInt(scanner.next());
			} catch (NumberFormatException e) {
				System.out.println("Debe introducir un valor numérico.\n");
			}
		} while (_int == -1);
		return _int;
	}

	public static double requestDouble(String title) {
		Scanner scanner;
		Double _double = -1.0;
		do {
			System.out.print(title);
			scanner = new Scanner(System.in);
			try {
				_double = Double.parseDouble(scanner.next());
			} catch (NumberFormatException e) {
				System.out.println("Debe introducir un valor numérico.\n");
			}
		} while (_double == -1.0);
		return _double;
	}

	public static boolean requestBoolean(String title) {
		Scanner scanner;
		String s_boolean;
		boolean _boolean = false;
		do {
			System.out.print(title);
			System.out.println("Introduce (SI/NO)");
			scanner = new Scanner(System.in);
			s_boolean = scanner.next();
			if (s_boolean.equalsIgnoreCase("SI"))
				_boolean = true;
			else if (s_boolean.equalsIgnoreCase("NO"))
				_boolean = false;
			else {
				s_boolean = "";
				System.out.println("Debe introducir el parámetro SI o NO.\n");
			}
		} while (s_boolean.equals(""));

		return _boolean;
	}

	/**
	 *
	 * @return String con la descripción en formato listView del objeto Furniture
	 */
	public String toListView() {
		return String.format("%s: %s(%s)", getModel().toUpperCase(), getManufacturer(), getClass().getSimpleName());
	}

	/**
	 * @param Class<?> type
	 *
	 * @return boolean
	 */
	public static boolean validFurniture(Class<?> type) {
		boolean result = false;
		if (type.getSuperclass() != null && type.getSuperclass().equals(Furniture.class))
			result = true;
		return result;
	}

}