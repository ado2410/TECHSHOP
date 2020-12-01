package gui.nhanvien.menu;

import javafx.fxml.FXML;
import util.GUILoader;

public class NhanVienAddController {
	@FXML
	private void onAction() {
		GUILoader.loadToNewWindow(GUILoader.load("gui/nhanvien/edit/GUI"));
	}
}
