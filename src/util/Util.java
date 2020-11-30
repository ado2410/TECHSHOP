package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import gui.debug.DebugController;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Util {
	public static Image loadImage(String path, int width, int height) {
		try {
			String fullPath = System.getProperty("user.dir").replace('\\', '/') + "/images/" + path;
			InputStream input;
			input = new FileInputStream(fullPath);
			return new Image(input, width, height, false, false);
		} catch (FileNotFoundException e) {
			
		}
		return null;
	}
	
	public static Image loadImage(String path, int width, int height, String defaultPath) {
		Image image = loadImage(path, width, height);
		if (image != null)
			return image;
		else
			return loadImage(defaultPath, width, height);
	}
	
	public static void showMessage(String message) {
		GUILoader loader = GUILoader.load("gui/debug/Message");
		
		DebugController controller = (DebugController) loader.getController();
		
		Scene scene = new Scene((AnchorPane)loader.getNode());
		Stage stage = new Stage();
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(GUILoader.getPrimaryStage().getScene().getWindow());
		stage.setScene(scene);
		stage.show();
		
		controller.setStage(stage);
		controller.setMessage(message);
	}
}
