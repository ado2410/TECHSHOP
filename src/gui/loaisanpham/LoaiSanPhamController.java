package gui.loaisanpham;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import util.GUILoader;
import util.Util;

public class LoaiSanPhamController {
	@FXML
	private ImageView image;
	@FXML
	private Text name;
	
	@FXML
	private void onAction() {
		GUILoader.loadToScene(GUILoader.load("gui/sanpham/detail/GUI"));
	}
	
	public void setName(String name) {
	this.name.setText(name);
	}
	
	public void setImage(String fileName) {
		image.setImage(Util.loadImage("general/" + fileName, 150, 150));
	}
}
