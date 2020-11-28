package gui.hoadon.menu;

import gui.hoadon.detail.GUIController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import util.GUILoader;

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
		GUIController controller = (GUIController) gui.getController();
		controller.initInfo(id.getText());
		GUILoader.loadToScene(gui);
	}
}
