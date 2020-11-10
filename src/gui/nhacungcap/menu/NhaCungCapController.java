package gui.nhacungcap.menu;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import util.GUILoader;
import util.Util;

public class NhaCungCapController {
	@FXML
	private ImageView image;
	@FXML
	private Text id;
	@FXML
	private Text name;
	@FXML
	private Text address;
	@FXML
	private Text phone;
	@FXML
	private Text email;
	
	@FXML
	private void onAction() {
		GUILoader.loadToScene(GUILoader.load("gui/nhacungcap/detail/GUI"));
	}
	
	public void setImage(String fileName) {
		image.setImage(Util.loadImage("general/" + fileName, 200, 200));
	}
	
	public void setId(String id) {
		this.id.setText(id);
	}
	
	public void setName(String name) {
		this.name.setText(name);
	}
	
	public void setAddress(String address) {
		this.address.setText(address);
	}
	
	public void setPhone(String phone) {
		this.phone.setText(phone);
	}
	
	public void setEmail(String email) {
		this.email.setText(email);
	}
}
