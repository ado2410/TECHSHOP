package gui.congviec.menu;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import util.GUILoader;
import util.SQL;

public class GUIController implements Initializable {
	@FXML
	private GridPane list;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initList();
	}
	
	private void initList() {
		try {
			list.getChildren().clear();
			ResultSet result = SQL.query("SELECT * FROM CONGVIEC");
			int colPerRow = 4;
			int row = 0;
			int col = 0;
			
			while(result.next()) {
				try {
					GUILoader gui = GUILoader.load("gui/congviec/menu/CongViec");
					
					AnchorPane element = (AnchorPane) gui.getNode();
					CongViecController elementController = (CongViecController) gui.getController();
					
					elementController.setImage(result.getString("ID"));
					elementController.setInfo(result.getString("THONGTIN"));
					elementController.setSalary(result.getString("LUONG"));
					elementController.setName(result.getString("TEN"));
					
					ResultSet result2 = SQL.query("SELECT COUNT(CONGVIEC) AS CVCOUNT FROM NHANVIEN WHERE CONGVIEC = '" + result.getString("ID") + "'");
					result2.next();
					elementController.setEmployeeCount(result2.getString("CVCOUNT"));
					
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
