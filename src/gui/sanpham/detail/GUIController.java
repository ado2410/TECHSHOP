package gui.sanpham.detail;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import util.Util;

public class GUIController {
	@FXML
	private Label name;
	@FXML
	private Text price;
	@FXML
	private Label length;
	@FXML
	private Label wide;
	@FXML
	private Label height;
	@FXML
	private Label color;
	@FXML
	private Label battery;
	@FXML
	private Label weight;
	@FXML
	private ImageView image;
	
	public void setImage(String fileName) {
		image.setImage(Util.loadImage("general/" + fileName, 200, 200));
	}
}
