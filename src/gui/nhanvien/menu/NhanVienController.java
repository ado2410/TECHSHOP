package gui.nhanvien.menu;

import gui.nhanvien.detail.GUIController;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import util.GUILoader;
import util.SQL;
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
		this.image.setImage(Util.loadImage("nhanvien/" + fileName + ".png", 100, 100, "general/Avatar.png"));
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
	
	@FXML
	private void onDeleteAction() {
		Util.continueWarning("Bạn có muốn xóa nhân viên có id là " + id.getText(), this, "delete");
	}
	
	public void delete() {
		SQL.update("DELETE FROM NHANVIEN WHERE ID = '" + id.getText() + "'");
		GUILoader.loadToScene(GUILoader.load("gui/nhanvien/menu/GUI"), false);
	}
}
