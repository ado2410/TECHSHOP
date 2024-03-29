package gui.khachhang.menu;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import util.GUILoader;
import util.SQL;
import util.Util;
import gui.khachhang.detail.GUIController;

public class ListController {
	@FXML
	private ImageView image;
	@FXML
	private Text name;
	@FXML
	private Text id;
	
	public void setImage(String fileName) {
		this.image.setImage(Util.loadImage("khachhang/" + fileName + ".png", 100, 100, "general/Avatar.png"));
	}
	
	public void setName(String name) {
		this.name.setText(name);
	}
	
	public void setId(String id) {
		this.id.setText(id);
	}
	
	@FXML
	private void onDetailAction() {
		GUILoader gui = GUILoader.load("gui/khachhang/detail/GUI");
		GUIController controller = (GUIController) gui.getController();
		controller.initInfo(id.getText());
		GUILoader.loadToScene(gui);
	}
	
	@FXML
	private void onDeleteAction() {
		Util.continueWarning("Bạn có muốn xóa khách hàng có id là " + id.getText(), this, "delete");
	}
	
	public void delete() {
		SQL.update("DELETE FROM KHACHHANG WHERE ID = '" + id.getText() + "'");
		GUILoader.loadToScene(GUILoader.load("gui/khachhang/menu/GUI"), false);
	}
}
