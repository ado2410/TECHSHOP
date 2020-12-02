package gui.nhanvien.menu;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import util.GUILoader;

public class NhanVienAddController {
	@FXML
	private void onAction() {
		GUILoader loader = GUILoader.load("gui/nhanvien/edit/GUI");
		gui.nhanvien.edit.GUIController controller = (gui.nhanvien.edit.GUIController) loader.getController();
		Stage stage = GUILoader.loadToNewWindow(loader, "Thêm nhân viên");
		controller.setStage(stage);
	}
}
