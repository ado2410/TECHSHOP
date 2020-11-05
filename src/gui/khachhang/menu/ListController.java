package gui.khachhang.menu;

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
	private Text gmail;
	
	public void setImage(String fileName) {
		image.setImage(Util.loadImage("general/Avatar.png", 100, 100));
	}
	
	public void setName(String name) {
		this.name.setText(name);
	}
	
	public void setGmail(String gmail) {
		this.gmail.setText(gmail);
	}
	
	@FXML
	private void onDetailAction() {
		
	}
}
