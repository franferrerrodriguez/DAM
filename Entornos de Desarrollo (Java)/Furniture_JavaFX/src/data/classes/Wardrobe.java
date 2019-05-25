/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.classes;

/**
 *
 * @author Francisco J. Ferrer
 */
public class Wardrobe extends Furniture {

	private int foots;
	private int doors;
	private int drawers;

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
	 * @param foots        int correspondiente al número de patas
	 * @param doors        int correspondiente al número de puertas
	 * @param drawers      int correspondiente al número de cajones
	 */
	public Wardrobe(String model, String manufacturer, String mainMaterial, String mainColor, double width, double high,
			double depth, int foots, int doors, int drawers) {
		super(model, manufacturer, mainMaterial, mainColor, width, high, depth);
		setFoots(foots);
		setDoors(doors);
		setDrawers(drawers);
	}

	/**
	 *
	 * @return foots
	 */
	public int getFoots() {
		return foots;
	}

	/**
	 *
	 * @param foots
	 */
	public void setFoots(int foots) {
		if (foots >= 0)
			this.foots = foots;
	}

	/**
	 *
	 * @return doors
	 */
	public int getDoors() {
		return doors;
	}

	/**
	 *
	 * @param doors
	 */
	public void setDoors(int doors) {
		if (doors >= 0)
			this.doors = doors;
	}

	/**
	 *
	 * @return drawers
	 */
	public int getDrawers() {
		return drawers;
	}

	/**
	 *
	 * @param drawers
	 */
	public void setDrawers(int drawers) {
		if (drawers >= 0)
			this.drawers = drawers;
	}

	/**
	 *
	 * @return String con la descripción del objeto Wardrobe
	 */
	@Override
	public String toString() {
		return String.format(
				"Armario\nModelo: %s\nFabricante: %s\nMaterial: %s\nColor: %s\nAnchura: %scms - Altura: %scms\nProfundidad: %scms - Volumen:%scms^3\nNº Patas: %s\nNº Puertas: %s\nNº Cajones: %s",
				super.getModel(), super.getManufacturer(), super.getMainMaterial(), super.getMainColor(),
				super.getWidth(), super.getHigh(), super.getDepth(), volumen(), foots, doors, drawers);
	}

	/**
	 *
	 * @return String del objeto Wardrobe a formato CSV
	 * 
	 */
	@Override
	public String toCsv() {
		return String.format("wardrobe;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s", super.getModel(), super.getManufacturer(),
				super.getMainMaterial(), super.getMainColor(), super.getWidth(), super.getHigh(), super.getDepth(),
				foots, doors, drawers);
	}

}