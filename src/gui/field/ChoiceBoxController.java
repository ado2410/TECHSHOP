package gui.field;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class ChoiceBoxController extends FieldController {
	@FXML
	private ChoiceBox<String> box;
	
	private String[] values;
	
	public void setChoiceList(String[] values, String[] hints) {
		box.getItems().clear();
		this.values = values;
		for (int i = 0; i < values.length; i++)
			box.getItems().add(hints[i]);
	}
	
	public void setValue(int index) {
		box.setValue(values[index]);
	}
	
	public String getValue() {
		int index = box.getSelectionModel().getSelectedIndex();
		return values[index];
	}
}
