package gui.khachhang.menu;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ListController {
	@FXML
	private ImageView image;
	@FXML
	private Text name;
	@FXML
	private Text gmail;
	
	public void setImage(String url) {
		Image imagem = new Image(url, 100, 100, false, false);
		image.setImage(imagem);
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
