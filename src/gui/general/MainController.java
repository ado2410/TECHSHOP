package gui.general;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import util.GUILoader;

public class MainController implements Initializable {
	@FXML
	private GridPane grid;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initMenuList();
	}
	
	private void initMenuList() {
		
		for (int i = 0; i < 3; i++) {
			GUILoader gui = GUILoader.load("gui/general/List");
			AnchorPane node = (AnchorPane) gui.getNode();
			ListController controller = (ListController) gui.getController();
			
			controller.setName("ABC");
			
			grid.add(node, i, 0);
		}
	}
}
