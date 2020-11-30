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
	private int maxNum;
	
	private GUIController guiController;
	private Node node;
	
	public void setGUIController(GUIController guiController) {
		this.guiController = guiController;
	}
	
	public void setNode(Node node) {
		this.node = node;
	}

	@FXML
	public void onDecreaseAction() {
		int num = Integer.parseInt(number.getText());
		if (num > 1)
			num--;
		number.setText(Integer.toString(num));
		price.setText(Integer.toString(pricePerUnit*num));
		
		guiController.updateNumber();
		guiController.updatePrice();
	}
	
	@FXML
	public void onIncreaseAction() {
		int num = Integer.parseInt(number.getText());
		if (num < maxNum)
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
	
	public String getId() {
		return id.getText();
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

	public int getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}
}
