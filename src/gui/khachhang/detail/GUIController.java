package gui.khachhang.detail;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import util.GUILoader;

public class GUIController implements Initializable {
	@FXML
	private ImageView imageLeft;
	@FXML
	private Text nameLeft;
	@FXML
	private Text name;
	@FXML
	private Text gender;
	@FXML
	private Text phoneNumber;
	@FXML
	private Text gmail;
	@FXML
	private Text country;
	@FXML
	private Text inviteCode;
	@FXML
	private Text address;
	@FXML
	private VBox purchasedGrid;
	
	@FXML
	private void onInfoAction() {
		
	}
	
	@FXML void onPurchasedAction() {
		
	}
	
	@FXML void onOtherInfoAction() {
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void initInfo(String id) {
		//Khoi tao cac thong tin co ban
		
		
		
		//Khoi tao cac mat hang da mua
		for (int i = 0; i < 10; i++) {
			GUILoader gui = GUILoader.load("gui/khachhang/detail/PurchasedBox");
			AnchorPane node = (AnchorPane) gui.getNode();
			PurchasedBoxController controller = (PurchasedBoxController) gui.getController();
			
			controller.setId("MX124F");
			controller.setDate("19/7/1999");
			controller.setPrice("348000");
			
			purchasedGrid.getChildren().add(node);
		}
	}
}
