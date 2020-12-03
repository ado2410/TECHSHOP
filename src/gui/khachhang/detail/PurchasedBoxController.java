package gui.khachhang.detail;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import util.GUILoader;
import util.Util;

public class PurchasedBoxController {
	@FXML
	private ImageView image;
	@FXML
	private Label id;
	@FXML
	private Label date;
	@FXML
	private Label price;
	
	public void setImage(String fileName) {
		this.image.setImage(Util.loadImage("khachhang/" + fileName + ".png", 50, 50, "general/Purchased.png"));
	}
	
	public void setId(String id) {
		this.id.setText(id);
	}
	
	public void setDate(String date) {
		this.date.setText(date);
	}
	
	public void setPrice(String price) {
		this.price.setText(price + "d");
	}
	
	@FXML
	private void onAction() {
		GUILoader loader = GUILoader.load("gui/hoadon/detail/GUI");
		gui.hoadon.detail.GUIController guiController = (gui.hoadon.detail.GUIController) loader.getController();
		guiController.initInfo(id.getText());
		GUILoader.loadToScene(loader);
	}
}
