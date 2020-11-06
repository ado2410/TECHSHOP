package gui.general;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import util.Util;

public class ListController {
	@FXML
	private ImageView image;
	@FXML
	private Text name;
	
	@FXML
	private void onAction() {
		
	}
	
	public void setImage(String fileName) {
		image.setImage(Util.loadImage("general/" + fileName, 200, 200));
	}
	
	public void setName(String name) {
		this.name.setText(name);
	}
	
}
