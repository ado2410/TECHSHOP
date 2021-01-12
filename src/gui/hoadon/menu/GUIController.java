package gui.hoadon.menu;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import util.GUILoader;
import util.SQL;

public class GUIController implements Initializable{
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
		
		filter.setOnKeyPressed(keyEvent -> {
			   if (keyEvent.getCode().equals(KeyCode.ENTER)) {
			      onFilterAction();
			   }
			});
	}
	
	private void initList(String filter) {
		try {
			list.getChildren().clear();
			ResultSet result = SQL.query("SELECT HOADON.ID, NGAYTAO, NHANVIEN.TEN AS NVTEN, KHACHHANG.TEN AS KHTEN FROM HOADON INNER JOIN NHANVIEN ON HOADON.NHANVIEN = NHANVIEN.ID INNER JOIN KHACHHANG ON HOADON.KHACHHANG = KHACHHANG.ID WHERE HOADON.ID LIKE N'%" + filter + "%' OR NHANVIEN.TEN LIKE N'%" + filter + "%' OR KHACHHANG.TEN LIKE N'%" + filter + "%'");
			
			while(result.next()) {
				try {
					GUILoader gui = GUILoader.load("gui/hoadon/menu/HoaDon");
					
					AnchorPane element = (AnchorPane) gui.getNode();
					HoaDonController elementController = (HoaDonController) gui.getController();
					
					elementController.setId(result.getString("ID"));
					elementController.setCreatedDate(result.getString("NGAYTAO"));
					elementController.setEmployee(result.getString("NVTEN"));
					elementController.setCustomer(result.getString("KHTEN"));
					
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
