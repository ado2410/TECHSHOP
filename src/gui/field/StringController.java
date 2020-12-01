package gui.field;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class StringController extends FieldController {
	@FXML
	private TextField value;
	
	public void setValue(String value) {
		this.value.setText(value);
	}
	
	public String getValue() {
		return this.value.getText();
	}
}
