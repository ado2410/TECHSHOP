package gui.nhacungcap.detail;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class GUIController implements Initializable {
	@FXML
	private ImageView image;
	@FXML
	private Text nameLeft;
	@FXML
	private TextField id;
	@FXML
	private TextField nameRight;
	@FXML
	private TextField address;
	@FXML
	private TextField phone;
	@FXML
	private TextField email;
	@FXML
	private TextArea description;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
