package gui.hoadon.add;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.text.Text;

public class SanPhamSelectedController {
	@FXML
	private Text id;
	@FXML
	private Text name;
	@FXML
	private Text price;
	@FXML
	private Text number;
	
	private int pricePerUnit;
	
	private GUIController guiController;
	private Node node;
	
	public void setGUIController(GUIController guiController) {
		this.guiController = guiController;
	}
	
	public void setNode(Node node) {
		this.node = node;
	}

	@FXML
	private void onDecreaseAction() {
		int num = Integer.parseInt(number.getText());
		if (num > 1)
			num--;
		number.setText(Integer.toString(num));
		price.setText(Integer.toString(pricePerUnit*num));
		
		guiController.updateNumber();
		guiController.updatePrice();
	}
	
	@FXML
	private void onIncreaseAction() {
		int num = Integer.parseInt(number.getText());
		num++;
		number.setText(Integer.toString(num));
		price.setText(Integer.toString(pricePerUnit*num));
		
		guiController.updateNumber();
		guiController.updatePrice();
	}
	
	@FXML void onDeleteAction() {
		guiController.deleteProduct(node);
	}
	
	public void setId(String id) {
		this.id.setText(id);
	}
	
	public void setName(String name) {
		this.name.setText(name);
	}
	
	public void setPrice(String price) {
		this.price.setText(price);
		pricePerUnit = Integer.parseInt(price);
	}
	
	public String getPrice() {
		return this.price.getText();
	}
	
	public void setNumber(String number) {
		this.number.setText(number);
	}
	
	public String getNumber() {
		return this.number.getText();
	}
}
