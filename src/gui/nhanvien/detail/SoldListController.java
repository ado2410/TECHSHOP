package gui.nhanvien.detail;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.Util;

public class SoldListController {
	@FXML
	private ImageView image;
	@FXML
	private Label id;
	@FXML
	private Label date;
	@FXML
	private Label price;
	
	public void setImage(String fileName) {
		Image image = Util.loadImage("khachhang/" + fileName, 100, 100);
		if (image != null)
			this.image.setImage(image);
		else
			this.image.setImage(Util.loadImage("general/Avatar.png", 50, 50));
	}
	
	public void setId(String id) {
		this.id.setText(id);
	}
	
	public void setDate(String date) {
		this.date.setText(date);
	}
	
	public void setPrice(String price) {
		this.price.setText(price);
	}
}
