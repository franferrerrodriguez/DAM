package main;

import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Window {

	private static Map<String, Stage> windows = new HashMap<String, Stage>();

	public void addWindow(Stage stage, String id, boolean modal, String root_resource, String css_resource,
			String title_app) {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource(root_resource));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource(css_resource).toExternalForm());
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle(title_app);
			if (windows.size() > 0 && modal)
				stage.initModality(Modality.APPLICATION_MODAL);
			windows.put(id, stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showWindow(String id) {
		windows.get(id).show();
	}

	public void hideWindow(String id) {
		windows.get(id).hide();
	}

}