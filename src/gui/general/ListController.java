package gui.general;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import util.GUILoader;
import util.Util;

public class ListController {
	@FXML
	private ImageView image;
	@FXML
	private Text name;
	
	private String link;
	
	@FXML
	private void onAction() {
		GUILoader.loadToMainScene(GUILoader.load(link).getNode());
	}
	
	public void setImage(String fileName) {
		image.setImage(Util.loadImage("general/" + fileName, 200, 200));
	}
	
	public void setName(String name) {
		this.name.setText(name);
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
}
