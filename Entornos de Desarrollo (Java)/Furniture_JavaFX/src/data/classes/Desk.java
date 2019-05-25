package data.classes;

public class Desk extends Furniture {

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
	 * @param drawers      int correspondiente al número de cajones
	 */
	public Desk(String model, String manufacturer, String mainMaterial, String mainColor, double width, double high,
			double depth, int drawers) {
		super(model, manufacturer, mainMaterial, mainColor, width, high, depth);
		this.drawers = drawers;
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
	 * @return String con la descripción del objeto Desk
	 */
	@Override
	public String toString() {
		return String.format(
				"Desk\nModelo: %s\nFabricante: %s\nMaterial: %s\nColor: %s\nAnchura: %scms - Altura: %scms\nProfundidad: %scms - Volumen:%scms^3\nNº Cajones: %s",
				super.getModel(), super.getManufacturer(), super.getMainMaterial(), super.getMainColor(),
				super.getWidth(), super.getHigh(), super.getDepth(), volumen(), drawers);
	}

	/**
	 *
	 * @return String del objeto Desk a formato CSV
	 * 
	 */
	@Override
	public String toCsv() {
		return String.format("desk;%s;%s;%s;%s;%s;%s;%s;%s;;", super.getModel(), super.getManufacturer(),
				super.getMainMaterial(), super.getMainColor(), super.getWidth(), super.getHigh(), super.getDepth(),
				drawers);
	}

}