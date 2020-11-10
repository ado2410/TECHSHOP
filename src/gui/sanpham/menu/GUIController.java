package gui.sanpham.menu;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import util.GUILoader;

public class GUIController implements Initializable {
	@FXML
	private TextField filter;
	@FXML
	private VBox list;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initList();
	}
	
	private void initList() {
		list.getChildren().clear();
		
		int number = 20;
		
		for (int i = 0; i < number; i++) {
			GUILoader gui = GUILoader.load("gui/sanpham/menu/SanPham");
			
			AnchorPane element = (AnchorPane) gui.getNode();
			SanPhamController elementController = (SanPhamController) gui.getController();
			elementController.setImage("Product.png");
			elementController.setName("Hello");
			elementController.setPrice("18000000");
			
			list.getChildren().add(element);
		}
	}
}
