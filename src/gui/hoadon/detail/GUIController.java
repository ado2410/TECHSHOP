package gui.hoadon.detail;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import util.SQL;

public class GUIController implements Initializable{
	@FXML
	private Text employee;
	@FXML
	private Text customer;
	@FXML
	private Text id;
	@FXML
	private Text date;
	@FXML
	private Text totalPrice;
	@FXML
	private TableView<BillData> billDetail;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		billDetail.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("order"));
		billDetail.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
		billDetail.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("number"));
		billDetail.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("pricePerUnit"));
		billDetail.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("price"));
	}
	
	public void initInfo(String id) {
		ResultSet result1 = SQL.query("SELECT HOADON.ID AS ID, NGAYTAO, NHANVIEN.TEN AS NVTEN, KHACHHANG.TEN AS KHTEN FROM HOADON INNER JOIN NHANVIEN ON HOADON.NHANVIEN = NHANVIEN.ID INNER JOIN KHACHHANG ON HOADON.KHACHHANG = KHACHHANG.ID WHERE HOADON.ID = '" + id + "'");
		try {
			while(result1.next()) {
				employee.setText(result1.getString("NVTEN"));
				customer.setText(result1.getString("KHTEN"));
				this.id.setText(result1.getString("ID"));
				date.setText(result1.getString("NGAYTAO"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet result2 = SQL.query("SELECT SANPHAM.TEN AS TEN, CHITIETHOADON.SOLUONG AS SOLUONG, GIA FROM CHITIETHOADON INNER JOIN SANPHAM ON CHITIETHOADON.SANPHAM = SANPHAM.ID WHERE CHITIETHOADON.ID = '" + id + "'");
		
		int i = 1;
		int price = 0;
		try {
			while(result2.next()) {
				billDetail.getItems().add(new BillData(i, result2.getString("TEN"), result2.getInt("SOLUONG"), result2.getInt("GIA"), result2.getInt("GIA")*result2.getInt("SOLUONG")));
				i++;
				price += result2.getInt("GIA")*result2.getInt("SOLUONG");
			}
			totalPrice.setText(Integer.toString(price));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
