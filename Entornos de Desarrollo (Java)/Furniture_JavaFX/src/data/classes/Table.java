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
public class Table extends Furniture {

	private int foots;

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
	 */
	public Table(String model, String manufacturer, String mainMaterial, String mainColor, double width, double high,
			double depth, int foots) {
		super(model, manufacturer, mainMaterial, mainColor, width, high, depth);
		setFoots(foots);
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
	 * @return String con la descripción del objeto Table
	 */
	@Override
	public String toString() {
		return String.format(
				"Mesa\nModelo: %s\nFabricante: %s\nMaterial: %s\nColor: %s\nAnchura: %scms - Altura: %scms\nProfundidad: %scms - Volumen:%scms^3\nNº Patas: %s",
				super.getModel(), super.getManufacturer(), super.getMainMaterial(), super.getMainColor(),
				super.getWidth(), super.getHigh(), super.getDepth(), volumen(), foots);
	}

	/**
	 *
	 * @return String del objeto Table a formato CSV
	 * 
	 */
	@Override
	public String toCsv() {
		return String.format("table;%s;%s;%s;%s;%s;%s;%s;%s;;", super.getModel(), super.getManufacturer(),
				super.getMainMaterial(), super.getMainColor(), super.getWidth(), super.getHigh(), super.getDepth(),
				foots);
	}

}