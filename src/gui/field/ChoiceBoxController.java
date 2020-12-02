package gui.field;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class ChoiceBoxController extends FieldController {
	@FXML
	private ChoiceBox<String> box;
	
	private String[] values;
	private String[] hints;
	
	public void setChoiceList(String[] values, String[] hints) {
		box.getItems().clear();
		this.values = values;
		this.hints = hints;
		for (int i = 0; i < values.length; i++)
			box.getItems().add(hints[i]);
	}
	
	public void setValue(int index) {
		box.setValue(hints[index]);
	}
	
	public void setValue(String value) {
		for ( int i = 0; i < values.length; i++)
			if (values[i].compareTo(value) == 0) {
				box.setValue(hints[i]);
				break;
			}
	}
	
	public String getValue() {
		int index = box.getSelectionModel().getSelectedIndex();
		return values[index];
	}
}
