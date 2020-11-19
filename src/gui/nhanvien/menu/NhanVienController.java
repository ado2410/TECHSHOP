package gui.nhanvien.menu;

import gui.nhanvien.detail.GUIController;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import util.GUILoader;
import util.Util;

public class NhanVienController {
	@FXML
	private ImageView image;
	@FXML
	private Text id;
	@FXML
	private Text name;
	@FXML
	private Text position;
	
	public void setImage(String fileName) {
		Image image = Util.loadImage("khachhang/" + fileName, 100, 100);
		if (image != null)
			this.image.setImage(image);
		else
			this.image.setImage(Util.loadImage("general/Avatar.png", 100, 100));
	}
	
	public void setId(String id) {
		this.id.setText(id);
	}
	
	public void setName(String name) {
		this.name.setText(name);
	}
	
	public void setPosition(String position) {
		this.position.setText(position);
	}
	
	@FXML
	private void onDetailAction() {
		GUILoader gui = GUILoader.load("gui/nhanvien/detail/GUI");
		GUIController controller = (GUIController) gui.getController();
		controller.initInfo(this.id.getText());
		GUILoader.loadToScene(gui);
	}
}
