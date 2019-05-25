package data.maincontroller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import main.Window;

public class FurnitureController implements Initializable {

	private Window window;

	@FXML
	private Button btn_listarAlmacenA;

	@FXML
	private Button btn_exit;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		window = new Window();
	}

	public void btnListarAlmacenA(ActionEvent event) {
		window.showWindow("listwarehouse");
	}

	public void btnExit(ActionEvent event) {
		System.exit(0);
	}

}