package gui.field;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;

public class DateController extends FieldController implements Initializable {
	@FXML
	private DatePicker value;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		value.setValue(LocalDate.now());
	}
	
	public void setValue(LocalDate localDate) {
		this.value.setValue(localDate);
	}
	
	public LocalDate getValue() {
		return this.value.getValue();
	}
	
	public String getValueByString() {
		return value.getValue().getYear() + "/" + value.getValue().getMonthValue() + "/" + value.getValue().getDayOfMonth();
	}
}
