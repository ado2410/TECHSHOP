	package gui.khachhang.menu;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import util.GUILoader;
import util.SQL;

public class GUIController implements Initializable {
	@FXML
	private GridPane list;
	@FXML
	private TextField filter;
	@FXML
	private void onFilterAction() {
		initList(filter.getText());
		filter.setText("");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initList();
		
		filter.setOnKeyPressed(keyEvent -> {
			   if (keyEvent.getCode().equals(KeyCode.ENTER)) {
			      onFilterAction();
			   }
			});
	}
	
	private void initList() {
		initList("");
	}
	
	private void initList(String filter) {
		try {
			list.getChildren().clear();
			ResultSet result = SQL.query("SELECT ID, TEN FROM KHACHHANG WHERE TEN LIKE N'%" + filter + "%'");
			int colPerRow = 5;
			int row = 0;
			int col = 1;
			
			GUILoader guiAdd = GUILoader.load("gui/khachhang/menu/ListAdd");
			list.add(guiAdd.getNode(), 0, 0);
			
			while(result.next()) {
				try {
					GUILoader gui = GUILoader.load("gui/khachhang/menu/List");
					
					AnchorPane element = (AnchorPane) gui.getNode();
					ListController elementController = (ListController) gui.getController();
					
					elementController.setImage(result.getString("ID"));
					elementController.setName(result.getString("TEN"));
					elementController.setId(result.getString("ID"));
					
					list.add(element, col, row);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				col++;
				if (col >= colPerRow) {
					col = 0;
					row++;
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
