package gui.khachhang.detail;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
	
	@FXML
	private void onAction() {
		
	}
	
	public void setImage(String fileName) {
		image.setImage(Util.loadImage("general/" + fileName, 50, 50));
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
}
