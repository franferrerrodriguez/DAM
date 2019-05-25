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
public class Chair extends Furniture {

	private int foots;
	private boolean backrest;

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
	 * @param backrest     Boolean correspondiente a si tiene o no respaldo
	 */
	public Chair(String model, String manufacturer, String mainMaterial, String mainColor, double width, double high,
			double depth, int foots, Boolean backrest) {
		super(model, manufacturer, mainMaterial, mainColor, width, high, depth);
		setFoots(foots);
		this.backrest = backrest;
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
	 * @return backrest
	 */
	public boolean isBackrest() {
		return backrest;
	}

	/**
	 *
	 * @param backrest
	 */
	public void setBackrest(boolean backrest) {
		this.backrest = backrest;
	}

	/**
	 *
	 * @return String con la descripción del objeto Chair
	 */
	@Override
	public String toString() {
		return String.format(
				"Silla\nModelo: %s\nFabricante: %s\nMaterial: %s\nColor: %s\nAnchura: %scms - Altura: %scms\nProfundidad: %scms - Volumen:%scms^3\nNº Patas: %s\nRespaldo: %s",
				super.getModel(), super.getManufacturer(), super.getMainMaterial(), super.getMainColor(),
				super.getWidth(), super.getHigh(), super.getDepth(), volumen(), foots, backrest ? "SI" : "NO");
	}

	/**
	 *
	 * @return String del objeto Chair a formato CSV
	 * 
	 */
	@Override
	public String toCsv() {
		return String.format("chair;%s;%s;%s;%s;%s;%s;%s;%s;%s;", super.getModel(), super.getManufacturer(),
				super.getMainMaterial(), super.getMainColor(), super.getWidth(), super.getHigh(), super.getDepth(),
				foots, backrest ? "true" : "false");
	}

}