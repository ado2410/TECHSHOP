package gui.nhacungcap.menu;

import gui.nhacungcap.detail.GUIController;
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
	private void onDetailAction() {
		GUILoader gui = GUILoader.load("gui/nhacungcap/detail/GUI");
		GUIController controller = (GUIController) gui.getController();
		controller.initInfo(this.id.getText());
		GUILoader.loadToScene(gui);
	}
	
	@FXML
	private void onDeleteAction() {
		
	}
	
	public void setImage(String fileName) {
		this.image.setImage(Util.loadImage("nhacungcap/" + fileName + ".png", 100, 100, "general/Company.png"));
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
