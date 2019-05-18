package main;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import data.Alojamiento;
import data.CasaRural;
import data.Hotel;
import data.Utils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class AlojamientosController implements Initializable {

	private List<Alojamiento> listaAlojamientos;

	@FXML
	private Label label;

	@FXML
	private ListView<String> lista;

	@FXML
	private RadioButton radio_hotel;

	@FXML
	private ToggleGroup alojamientos;

	@FXML
	private RadioButton radio_casarural;

	@FXML
	private TextField txt_nombrealojamiento;

	@FXML
	private TextField txt_precionoche;

	@FXML
	private TextField txt_numestrellas;

	@FXML
	private TextField txt_max_personas;

	@FXML
	private TextField txt_numhabitaciones;

	@FXML
	private CheckBox check_piscina;

	@FXML
	private Button btn_anadir;

	@FXML
	private Button btn_borrar;

	@FXML
	private Button btn_limpiar;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		rellenarListView();

		limpiarCampos(Hotel.class);

		Platform.runLater(() -> txt_nombrealojamiento.requestFocus());
	}

	public void rellenarListView() {

		listaAlojamientos = Utils.cargarCsv();

		for (Alojamiento alojamiento : listaAlojamientos) {
			lista.getItems().add(alojamiento.toString());
		}

	}

	public void cambioAlojamiento(ActionEvent event) {

		if (radio_hotel.isSelected())
			limpiarCampos(Hotel.class);
		else if (radio_casarural.isSelected())
			limpiarCampos(CasaRural.class);

		txt_nombrealojamiento.requestFocus();

	}

	public void checkNumEstrellas(KeyEvent event) {
		Utils.checkOnlyInt(txt_numestrellas, 2);
	}

	public void checkMaxPersonas(KeyEvent event) {
		Utils.checkOnlyInt(txt_max_personas, 2);
	}

	public void checkNumHabitaciones(KeyEvent event) {
		Utils.checkOnlyInt(txt_numhabitaciones, 3);
	}

	public void checkPrecioNoche(KeyEvent event) {
		Utils.checkOnlyDouble(txt_precionoche, 3);
	}

	public void changeAlojamientoListView(MouseEvent event) {

		try {
			Alojamiento alojamiento = listaAlojamientos.get(lista.getSelectionModel().getSelectedIndex());

			if (alojamiento.getClass().equals(Hotel.class)) {
				limpiarCampos(Hotel.class);
				Hotel hotel = (Hotel) alojamiento;
				txt_numestrellas.setText("" + hotel.getNumEstrellas());
				if (hotel.getTienePiscina())
					check_piscina.setSelected(true);
			} else if (alojamiento.getClass().equals(CasaRural.class)) {
				limpiarCampos(CasaRural.class);
				CasaRural casarural = (CasaRural) alojamiento;
				txt_max_personas.setText("" + casarural.getMaxPersonas());
				txt_numhabitaciones.setText("" + casarural.getNumHabitaciones());
			}

			txt_nombrealojamiento.setText(alojamiento.getNombre());
			txt_precionoche.setText("" + alojamiento.getPrecio());
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e);
		}

	}

	public void limpiarCampos(Class<?> tipoAlojamiento) {

		txt_nombrealojamiento.clear();
		txt_precionoche.clear();
		txt_numestrellas.clear();
		txt_max_personas.clear();
		txt_numhabitaciones.clear();
		check_piscina.setSelected(false);

		if (tipoAlojamiento.equals(Hotel.class)) {
			radio_hotel.setSelected(true);
			txt_numestrellas.setDisable(false);
			check_piscina.setDisable(false);
			txt_max_personas.setDisable(true);
			txt_numhabitaciones.setDisable(true);
		} else if (tipoAlojamiento.equals(CasaRural.class)) {
			radio_casarural.setSelected(true);
			txt_max_personas.setDisable(false);
			txt_numhabitaciones.setDisable(false);
			txt_numestrellas.setDisable(true);
			check_piscina.setDisable(true);
		}

	}

	public void btnAnadir(ActionEvent event) {

		String mensaje = "";
		Alert.AlertType tipo = AlertType.ERROR;
		String nombrealojamiento = txt_nombrealojamiento.getText();
		String precionoche = txt_precionoche.getText();
		Boolean creado = false;

		if (nombrealojamiento.equals("")) {
			mensaje = "Debe introducir el nombre del alojamiento.";
		} else if (listaAlojamientos.stream()
				.anyMatch(alojamiento -> alojamiento.getNombre().equals(nombrealojamiento))) {
			mensaje = "El alojamiento " + nombrealojamiento + " ya existe en el sistema.";
		} else if (precionoche.equals("")) {
			mensaje = "Debe introducir el precio del alojamiento.";
		} else {
			double precionoche_d = Double.parseDouble(precionoche);

			if (radio_hotel.isSelected()) {
				String numestrellas = txt_numestrellas.getText();
				Boolean tiene_piscina = check_piscina.isSelected();

				if (numestrellas.equals("")) {
					mensaje = "Debe introducir el número de estrellas.";
				} else {
					int numestrellas_i = Integer.parseInt(numestrellas);

					if (numestrellas_i < 1 || numestrellas_i > 5)
						mensaje = "Debe introducir un número de estrellas entre 1 y 5.";
					else {
						listaAlojamientos
								.add(new Hotel(nombrealojamiento, precionoche_d, numestrellas_i, tiene_piscina));
						creado = true;
					}
				}
			} else if (radio_casarural.isSelected()) {
				String max_personas = txt_max_personas.getText();
				String numhabitaciones = txt_numhabitaciones.getText();

				if (max_personas.equals("")) {
					mensaje = "Debe introducir el máximo de personas.";
				} else if (numhabitaciones.equals("")) {
					mensaje = "Debe introducir el número de habitaciones.";
				} else {
					int max_personas_i = Integer.parseInt(max_personas);
					int numhabitaciones_i = Integer.parseInt(numhabitaciones);

					listaAlojamientos
							.add(new CasaRural(nombrealojamiento, precionoche_d, max_personas_i, numhabitaciones_i));
					creado = true;
				}
			}

			if (creado) {
				mensaje = "El alojamiento ha sido creado correctamente.";
				tipo = AlertType.INFORMATION;
				lista.getItems().add(nombrealojamiento);
				Utils.guardarCsv(listaAlojamientos);
			}
		}

		Utils.dialogo(mensaje, tipo);

	}

	public void btnBorrar(ActionEvent event) {

		int index_alojamiento = lista.getSelectionModel().getSelectedIndex();

		if (index_alojamiento == -1) {
			Utils.dialogo("Para borrar debe seleccionar un alojamiento de la lista.", AlertType.ERROR);
		} else if (Utils.dialogo(
				"¿Está seguro que desea borrar el alojamiento: " + lista.getSelectionModel().getSelectedItem() + "?",
				AlertType.CONFIRMATION).get() == ButtonType.OK) {
			lista.getItems().remove(index_alojamiento);
			listaAlojamientos.remove(index_alojamiento);
			Utils.guardarCsv(listaAlojamientos);
			Utils.dialogo("El alojamiento ha sido eliminado correctamente.", AlertType.INFORMATION);
		}
	}

	public void btnLimpiarCampos(ActionEvent event) {
		limpiarCampos(Hotel.class);
		txt_nombrealojamiento.requestFocus();
	}

}
