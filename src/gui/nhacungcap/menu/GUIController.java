package gui.nhacungcap.menu;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import util.GUILoader;
import util.SQL;

public class GUIController implements Initializable {
	@FXML
	private TextField filter;
	@FXML
	private VBox list;
	@FXML
	private void onFilterAction() {
		initList(filter.getText());
		filter.setText("");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initList("");
	}
	
	private void initList(String filter) {
		try {
			list.getChildren().clear();
			ResultSet result = SQL.query("SELECT ID, TEN, DIACHI, SDT, EMAIL FROM NHACUNGCAP WHERE TEN LIKE N'%" + filter + "%'");
			
			while(result.next()) {
				try {
					GUILoader gui = GUILoader.load("gui/nhacungcap/menu/NhaCungCap");
					
					AnchorPane element = (AnchorPane) gui.getNode();
					NhaCungCapController elementController = (NhaCungCapController) gui.getController();
					
					elementController.setImage(result.getString("ID"));
					elementController.setName(result.getString("TEN"));
					elementController.setId(result.getString("ID"));
					elementController.setAddress(result.getString("DIACHI"));
					elementController.setPhone(result.getString("DIACHI"));
					elementController.setEmail(result.getString("EMAIL"));
					
					list.getChildren().add(element);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
