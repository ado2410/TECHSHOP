package gui.field;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class NumberController extends FieldController implements Initializable {
	@FXML
	private TextField value;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		value.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	value.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
	}
	
	public void setValue(int value) {
		this.value.setText(Integer.toString(value));
	}
	
	public int getValue() {
		return Integer.parseInt(this.value.getText());
	}
}
