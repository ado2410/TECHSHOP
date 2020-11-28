package gui.hoadon.add;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import util.Util;

public class SanPhamController {
	@FXML
	private ImageView image;
	@FXML
	private Text id;
	@FXML
	private Text price;
	@FXML
	private Text name;
	
	private GUIController guiController;
	
	@FXML
	private void onAction() {
		guiController.addProduct(id.getText());
	}
	
	public void setGUIController(GUIController guiController) {
		this.guiController = guiController;
	}
	
	public void setImage(String fileName) {
		this.image.setImage(Util.loadImage("sanpham/" + fileName + ".png", 100, 100, "general/Product.png"));
	}
	
	public void setId(String id) {
		this.id.setText(id);
	}
	
	public void setPrice(String price) {
		this.price.setText(price);
	}
	
	public void setName(String name) {
		this.name.setText(name);
	}
}
