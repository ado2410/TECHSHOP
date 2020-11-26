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
		GUILoader.loadToScene(GUILoader.load(link));
	}
	
	public void setImage(String fileName) {
		this.image.setImage(Util.loadImage("main/" + fileName + ".png", 200, 200, "general/MainMenu.png"));
	}
	
	public void setName(String name) {
		this.name.setText(name);
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
}
