package gui.hoadon.menu;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import util.GUILoader;
import util.SQL;
import util.Util;

public class HoaDonController {
	@FXML
	private Label id;
	@FXML
	private Label createdDate;
	@FXML
	private Label employee;
	@FXML
	private Label customer;
	
	public void setId(String id) {
		this.id.setText(id);
	}
	
	public void setCreatedDate(String createdDate) {
		this.createdDate.setText(createdDate);
	}
	
	public void setEmployee(String employee) {
		this.employee.setText(employee);
	}
	
	public void setCustomer(String customer) {
		this.customer.setText(customer);
	}
	
	@FXML
	private void onDetailAction() {
		GUILoader gui = GUILoader.load("gui/hoadon/detail/GUI");
		gui.hoadon.detail.GUIController controller = (gui.hoadon.detail.GUIController) gui.getController();
		controller.initInfo(id.getText());
		GUILoader.loadToScene(gui);
	}
	
	@FXML
	private void onDeleteAction() {
		Util.continueWarning("Xóa hóa đơn có id là " + id.getText() + "?", this, "delete");
	}
	
	public void delete() {
		SQL.update("DELETE FROM CHITIETHOADON WHERE ID = '" + id.getText() + "'");
		SQL.update("DELETE FROM HOADON WHERE ID = '" + id.getText() + "'");
		GUILoader gui = GUILoader.load("gui/hoadon/menu/GUI");
		GUILoader.loadToScene(gui, false);
	}
}
