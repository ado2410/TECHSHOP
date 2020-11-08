package gui.loaisanpham;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import util.GUILoader;

public class GUIController implements Initializable {
	@FXML
	private GridPane grid;
	@FXML
	private TextField filter;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initList();
		
		filter.textProperty().addListener((observable, oldValue, newValue) -> {
		    initList(newValue);
		});
	}
	
	private void initList() {
		initList("");
	}
	private void initList(String filter) {
		grid.getChildren().clear();
		
		int number = 14;
		int colPerRow = 4;
		int row = 0;
		int col = 0;
		
		while(col + row*colPerRow < number) {
			GUILoader gui = GUILoader.load("gui/loaisanpham/LoaiSanPham");
			
			AnchorPane element = (AnchorPane) gui.getNode();
			LoaiSanPhamController elementController = (LoaiSanPhamController) gui.getController();
			elementController.setImage("Avatar.png");
			elementController.setName("Hello");
			
			grid.add(element, col+1, row);
			
			col++;
			if (col >= colPerRow) {
				col = 0;
				row++;
			}
		}
	}
}
