package gui.field;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public abstract class FieldController {
	@FXML
	protected Label name;
	
	public void setName(String name) {
		this.name.setText(name);
	}
	
	public String getName() {
		return this.name.getText();
	}
}
