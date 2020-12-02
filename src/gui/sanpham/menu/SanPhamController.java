package gui.sanpham.menu;

import gui.sanpham.detail.GUIController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import util.GUILoader;
import util.SQL;
import util.Util;

public class SanPhamController {
	@FXML
	private Label name;
	@FXML
	private Label price;
	@FXML
	private ImageView image;
	@FXML
	private Label spec1;
	@FXML
	private Label spec2;
	@FXML
	private Label spec3;
	
	private String id;
	
	@FXML
	private void onDetailAction() {
		GUILoader gui = GUILoader.load("gui/sanpham/detail/GUI");
		GUIController controller = (GUIController) gui.getController();
		controller.initInfo(id);
		GUILoader.loadToScene(gui);
	}
	
	@FXML
	private void onDeleteAction() {
		Util.continueWarning("Bạn có muốn xóa sản phẩm có id là " + id, this, "delete");
	}
	
	public void delete() {
		SQL.update("DELETE FROM SANPHAM WHERE ID = '" + id + "'");
		GUILoader.loadToScene(GUILoader.load("gui/sanpham/menu/GUI"), false);
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name.setText(name);
	}
	
	public void setPrice(String price) {
		this.price.setText(price);
	}
	
	public void setImage(String fileName) {
		this.image.setImage(Util.loadImage("sanpham/" + fileName + ".png", 150, 150, "general/Product.png"));
	}
}
