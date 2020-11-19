package gui.loaisanpham;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import util.GUILoader;
import util.SQL;

public class GUIController implements Initializable {
	@FXML
	private GridPane grid;
	@FXML
	private TextField filter;
	
	@FXML
	private void onFilterAction() {
		initList(filter.getText());
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initList();
	}
	
	private void initList() {
		initList("");
	}
	private void initList(String filter) {
		try {
			grid.getChildren().clear();
			ResultSet result = SQL.query("SELECT ID, TEN FROM LOAISANPHAM WHERE TEN LIKE N'%" + filter + "%'");
			int colPerRow = 4;
			int row = 0;
			int col = 0;
			
			while(result.next()) {
				try {
					GUILoader gui = GUILoader.load("gui/loaisanpham/LoaiSanPham");
					
					AnchorPane element = (AnchorPane) gui.getNode();
					LoaiSanPhamController elementController = (LoaiSanPhamController) gui.getController();
					
					elementController.setImage(result.getString("ID") + ".png");
					elementController.setName(result.getString("TEN"));
					
					grid.add(element, col+1, row);
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
