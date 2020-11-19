package gui.khachhang.detail;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import util.GUILoader;
import util.SQL;

public class GUIController implements Initializable {
	@FXML
	private ImageView imageLeft;
	@FXML
	private Text nameLeft;
	@FXML
	private Text id;
	@FXML
	private Text name;
	@FXML
	private Text bod;
	@FXML
	private Text gender;
	@FXML
	private Text phoneNumber;
	@FXML
	private Text email;
	@FXML
	private Text country;
	@FXML
	private Text inviteCode;
	@FXML
	private Text joinDate;
	@FXML
	private Text address;
	@FXML
	private VBox purchasedGrid;
	@FXML
	private GridPane infoGrid;
	@FXML
	private VBox container;
	
	@FXML
	private void onInfoAction() {
		container.getChildren().clear();
		container.getChildren().add(infoGrid);
	}
	
	@FXML void onPurchasedAction() {
		container.getChildren().clear();
		container.getChildren().add(purchasedGrid);
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
			ResultSet result = SQL.query("SELECT * FROM KHACHHANG WHERE ID = '" + id + "'");
			result.next();
			this.id.setText(result.getString("ID"));
			this.nameLeft.setText(result.getString("TEN"));
			this.name.setText(result.getString("TEN"));
			this.bod.setText(result.getString("NGAYSINH"));
			this.gender.setText(result.getString("GIOITINH"));
			this.phoneNumber.setText(result.getString("SDT"));
			this.email.setText(result.getString("EMAIL"));
			this.inviteCode.setText(result.getString("MAGIOITHIEU"));
			this.joinDate.setText(result.getString("NGAYTHAMGIA"));
			this.address.setText(result.getString("DIACHI"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Khoi tao cac mat hang da mua
		try {
			ResultSet result = SQL.query("SELECT HOADON.ID, NGAYTAO FROM KHACHHANG INNER JOIN HOADON ON KHACHHANG.ID = HOADON.KHACHHANG WHERE KHACHHANG.ID = '" + id + "'");
			while (result.next()) {
				System.out.print(id);
				GUILoader gui = GUILoader.load("gui/khachhang/detail/PurchasedBox");
				AnchorPane node = (AnchorPane) gui.getNode();
				PurchasedBoxController controller = (PurchasedBoxController) gui.getController();
				
				ResultSet result2 = SQL.query("SELECT SUM(GIA) AS TOTALGIA FROM CHITIETHOADON INNER JOIN SANPHAM ON SANPHAM.ID = CHITIETHOADON.SANPHAM WHERE CHITIETHOADON.ID = '" + result.getString("ID") + "'");
				
				result2.next();
				controller.setId(result.getString("ID"));
				controller.setDate(result.getString("NGAYTAO"));
				controller.setPrice(result2.getString("TOTALGIA"));
				
				purchasedGrid.getChildren().add(node);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
