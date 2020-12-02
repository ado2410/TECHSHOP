package gui.sanpham.menu;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import util.GUILoader;

public class SanPhamAddController {
	@FXML
	private void onAction() {
		GUILoader loader = GUILoader.load("gui/sanpham/edit/GUI");
		gui.sanpham.edit.GUIController controller = (gui.sanpham.edit.GUIController) loader.getController();
		Stage stage = GUILoader.loadToNewWindow(loader, "Thêm sản phẩm");
		controller.setStage(stage);
	}
}
