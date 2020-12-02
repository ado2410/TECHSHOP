package gui.field;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class FloatController extends FieldController implements Initializable {
	@FXML
	private TextField value;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		value.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*([\\.]\\d*)?")) {
                	value.setText(oldValue);
                }
            }
        });
		value.setText("0.0");
	}
	
	public void setValue(float value) {
		this.value.setText(Float.toString(value));
	}
	
	public float getValue() {
		return Float.parseFloat(this.value.getText());
	}
}
