package gui.sanpham.menu;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import util.GUILoader;
import util.Util;

public class SanPhamController {
	@FXML
	private Label name;
	@FXML
	private Label price;
	@FXML
	private ImageView image;
	@FXML
	private Label spec1;
	@FXML
	private Label spec2;
	@FXML
	private Label spec3;
	
	@FXML
	private void onAction() {
		GUILoader.loadToScene(GUILoader.load("gui/sanpham/detail/GUI"));
	}
	
	public void setName(String name) {
		this.name.setText(name);
	}
	
	public void setPrice(String price) {
		this.price.setText(price);
	}
	
	public void setImage(String fileName) {
		image.setImage(Util.loadImage("general/" + fileName, 200, 200));
	}
}
