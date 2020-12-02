package gui.khachhang.menu;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import util.GUILoader;

public class ListAddController {
	@FXML
	private void onAction() {
		GUILoader loader = GUILoader.load("gui/khachhang/edit/GUI");
		gui.khachhang.edit.GUIController controller = (gui.khachhang.edit.GUIController) loader.getController();
		Stage stage = GUILoader.loadToNewWindow(loader, "Thêm khách hàng");
		controller.setStage(stage);
	}
}
