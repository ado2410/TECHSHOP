package gui.nhanvien.menu;

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
		
		filter.setOnKeyPressed(keyEvent -> {
			   if (keyEvent.getCode().equals(KeyCode.ENTER)) {
			      onFilterAction();
			   }
			});
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
			list.getChildren().clear();
			ResultSet result = SQL.query("SELECT NHANVIEN.ID, NHANVIEN.TEN AS NVTEN, CONGVIEC.TEN AS CVTEN FROM NHANVIEN INNER JOIN CONGVIEC ON NHANVIEN.CONGVIEC = CONGVIEC.ID WHERE NHANVIEN.TEN LIKE N'%" + filter + "%'");
			int colPerRow = 5;
			int row = 0;
			int col = 1;
			
			GUILoader guiAdd = GUILoader.load("gui/nhanvien/menu/NhanVienAdd");
			list.add(guiAdd.getNode(), 0, 0);
			
			while(result.next()) {
				try {
					GUILoader gui = GUILoader.load("gui/nhanvien/menu/NhanVien");
					
					AnchorPane element = (AnchorPane) gui.getNode();
					NhanVienController elementController = (NhanVienController) gui.getController();
					
					elementController.setImage(result.getString("ID"));
					elementController.setName(result.getString("NVTEN"));
					elementController.setId(result.getString("ID"));
					elementController.setPosition(result.getString("CVTEN"));
					
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
