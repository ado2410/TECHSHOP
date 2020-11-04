package gui.khachhang.menu;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import util.GUILoader;

public class GUIController implements Initializable {
	@FXML
	private GridPane list;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initList();
	}
	
	private void initList() {
		
		int number = 14;
		int colPerRow = 4;
		int row = 0;
		int col = 0;
		
		while(col + row*colPerRow < number) {
			GUILoader gui = GUILoader.load("gui/khachhang/menu/List");
			
			AnchorPane element = (AnchorPane) gui.getNode();
			ListController elementController = (ListController) gui.getController();
			elementController.setName("Hello");
			elementController.setGmail("1234@gmail.com");
			
			list.add(element, col+1, row);
			
			col++;
			if (col >= colPerRow) {
				col = 0;
				row++;
			}
		}
	}

}
