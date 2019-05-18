package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class Utils {

	public static List<Alojamiento> cargarCsv() {

		BufferedReader br;

		List<Alojamiento> alojamientos = new ArrayList<Alojamiento>();

		try {
			br = new BufferedReader(new FileReader("./alojamientos.csv"));

			String linea;
			while ((linea = br.readLine()) != null) {
				String[] split = linea.split(";");
				alojamientos.add(csvToAlojamiento(split));
			}
		} catch (IOException e) {
			System.out.println(
					String.format("Ha ocurrido el siguiente error cargando el archivo .csv de alojamientos: %s", e));
		}

		return alojamientos;

	}

	public static Alojamiento csvToAlojamiento(String[] args) {

		Alojamiento alojamiento = null;
		if (args.length == 5) {
			if (args[0].equals("hotel")) {
				alojamiento = new Hotel(args[1], Double.parseDouble(args[2]), Integer.parseInt(args[3]),
						Boolean.parseBoolean(args[4]));
			} else if (args[0].equals("casarural")) {
				alojamiento = new CasaRural(args[1], Double.parseDouble(args[2]), Integer.parseInt(args[3]),
						Integer.parseInt(args[4]));
			}
		}

		return alojamiento;

	}

	public static void guardarCsv(List<Alojamiento> alojamientos) {

		BufferedWriter bw;
		File archivo;

		try {
			archivo = new File("./alojamientos.csv");

			bw = new BufferedWriter(new FileWriter(archivo));

			for (Alojamiento a : alojamientos) {
				bw.write(a.toCsv());
				bw.newLine();
			}

			bw.close();
		} catch (IOException e) {
			System.out.println(
					String.format("Ha ocurrido el siguiente error guardando el archivo .csv de alojamientos: %s", e));
		}

	}

	public static void checkOnlyInt(TextField textField, int length) {
		textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\.{0,7}([\\d+]\\d{0,4})?")) {
					textField.setText(oldValue);
				}
			}
		});
	}

	public static void checkOnlyDouble(TextField textField, int length) {
		textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
					textField.setText(oldValue);
				}
			}
		});
	}

	public static Optional<ButtonType> dialogo(String contenido, Alert.AlertType tipo) {

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
		a1.setContentText(contenido);
		a1.setHeaderText(null);

		return a1.showAndWait();

	}

}
