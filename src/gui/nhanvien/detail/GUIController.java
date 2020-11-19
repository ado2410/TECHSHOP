package gui.nhanvien.detail;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import util.GUILoader;
import util.SQL;
import util.Util;

public class GUIController implements Initializable{
	@FXML
	private ImageView avatar;
	@FXML
	private Text id;
	@FXML
	private Text nameLeft;
	@FXML
	private Text name;
	@FXML
	private Text gender;
	@FXML
	private Text phoneNumber;
	@FXML
	private Text email;
	@FXML
	private Text country;
	@FXML
	private Text address;
	@FXML
	private Text dob;
	@FXML
	private Text workingDate;
	@FXML
	private Text coefficientsSalary;
	@FXML
	private Text allowance;
	@FXML
	private Text salary;
	@FXML
	private Label job;
	@FXML
	private VBox infoContainer;
	@FXML
	private VBox purchasedContainer;
	@FXML
	private VBox container;
	
	@FXML
	private void onInfoAction() {
		container.getChildren().clear();
		container.getChildren().add(infoContainer);
	}
	
	@FXML void onPurchasedAction() {
		container.getChildren().clear();
		container.getChildren().add(purchasedContainer);
	}
	
	@FXML void onOtherInfoAction() {
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		onInfoAction();
	}
	
	public void initInfo(String id) {
		//Khoi tao cac thong tin co ban
		try {
			ResultSet result = SQL.query("SELECT * FROM NHANVIEN INNER JOIN CONGVIEC ON NHANVIEN.CONGVIEC = CONGVIEC.ID WHERE NHANVIEN.ID = '" + id + "'");
			result.next();
			this.avatar.setImage(Util.loadImage("nhanvien/" + result.getString("ID") + ".png", 200, 200, "general/Avatar.png"));
			this.id.setText(result.getString("ID"));
			this.nameLeft.setText(result.getString("TEN"));
			this.name.setText(result.getString("TEN"));
			this.dob.setText(result.getString("NGAYSINH"));
			this.gender.setText(result.getString("GIOITINH"));
			this.phoneNumber.setText(result.getString("SDT"));
			this.email.setText(result.getString("EMAIL"));
			this.workingDate.setText(result.getString("NGAYLAMVIEC"));
			this.address.setText(result.getString("DIACHI"));
			this.country.setText(result.getString("TINH"));
			this.coefficientsSalary.setText(result.getString("HESO"));
			this.allowance.setText(result.getString("PHUCAP"));
			this.salary.setText(Integer.toString(result.getInt("HESO") * result.getInt("LUONG") + result.getInt("PHUCAP")));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Khoi tao cac mat hang da mua
		try {
			ResultSet result = SQL.query("SELECT HOADON.ID, NGAYTAO FROM NHANVIEN INNER JOIN HOADON ON NHANVIEN.ID = HOADON.NHANVIEN WHERE NHANVIEN.ID = '" + id + "'");
			while (result.next()) {
				GUILoader gui = GUILoader.load("gui/nhanvien/detail/SoldList");
				AnchorPane node = (AnchorPane) gui.getNode();
				SoldListController controller = (SoldListController) gui.getController();
				
				ResultSet result2 = SQL.query("SELECT SUM(GIA) AS TOTALGIA FROM CHITIETHOADON INNER JOIN SANPHAM ON SANPHAM.ID = CHITIETHOADON.SANPHAM WHERE CHITIETHOADON.ID = '" + result.getString("ID") + "'");
				
				result2.next();
				controller.setId(result.getString("ID"));
				controller.setDate(result.getString("NGAYTAO"));
				controller.setPrice(result2.getString("TOTALGIA"));
				
				purchasedContainer.getChildren().add(node);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
