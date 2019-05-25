package data.listwarehousecontroller;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import data.classes.Furniture;
import data.classes.Warehouse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import main.Window;

public class ListWarehouseController implements Initializable {

	private Window window;
	private Warehouse almacen;

	@FXML
	private ListView<String> list_products;

	@FXML
	private Button btn_back;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		window = new Window();

		almacen = new Warehouse("Almacén A");

		almacen.loadFile("./warehouseA.csv");
		for (Map.Entry<String, Furniture> entry : almacen.getProducts().entrySet()) {
			list_products.getItems().add(entry.getValue().toListView());
		}

	}

	public void btnBack(ActionEvent event) {
		window.hideWindow("listwarehouse");
	}

	public void changeListView(MouseEvent event) {

		String key = list_products.getSelectionModel().getSelectedItem();

		if (key != null) {
			Furniture mueble = almacen.getProducts().get(key.split(":")[0]);

			Warehouse.dialogo("Información", mueble.toString(), Alert.AlertType.INFORMATION);
		}

	}

}