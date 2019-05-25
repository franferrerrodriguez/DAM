package main;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {

		Window w = new Window();

		w.addWindow(primaryStage, "furniture", false, "/data/maincontroller/Furniture.fxml",
				"/data/maincontroller/application.css", "Furniture - Francisco José Ferrer Rodríguez");

		w.addWindow(new Stage(), "listwarehouse", true, "/data/listwarehousecontroller/ListWarehouse.fxml",
				"/data/maincontroller/application.css", "List Warehouse A");

		w.showWindow("furniture");
	}

	public static void main(String[] args) {
		launch(args);
	}

}