package gui.general;

import javafx.fxml.FXML;
import util.GUILoader;

public class MainController {
	@FXML
	private void onHomeAction() {
		GUILoader.loadMainScene();
	}
	
	@FXML
	private void onBackAction() {
		GUILoader.loadPreviousScene();
	}
}
