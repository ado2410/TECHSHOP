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
	
	@FXML
	private void onAddBillAction() {
		GUILoader.loadToScene(GUILoader.load("gui/hoadon/add/GUI"));
	}
	
	@FXML
	private void onCustomerAction() {
		GUILoader.loadToScene(GUILoader.load("gui/khachhang/menu/GUI"));
	}

	@FXML
	private void onEmployeeAction() {
		GUILoader.loadToScene(GUILoader.load("gui/nhanvien/menu/GUI"));
	}
	
	@FXML
	private void onProductCategoryAction() {
		GUILoader.loadToScene(GUILoader.load("gui/loaisanpham/GUI"));
	}
	
	@FXML
	private void onProductAction() {
		GUILoader.loadToScene(GUILoader.load("gui/sanpham/menu/GUI"));
	}
	
	@FXML
	private void onCompanyAction() {
		GUILoader.loadToScene(GUILoader.load("gui/nhacungcap/menu/GUI"));
	}
	
	@FXML
	private void onJobAction() {
		GUILoader.loadToScene(GUILoader.load("gui/congviec/menu/GUI"));
	}
	
	@FXML
	private void onBillAction() {
		GUILoader.loadToScene(GUILoader.load("gui/hoadon/menu/GUI"));
	}
}
