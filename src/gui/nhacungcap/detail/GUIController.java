package gui.nhacungcap.detail;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import util.SQL;
import util.Util;

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
	
	public void initInfo(String id) {
		try {
			ResultSet result = SQL.query("SELECT * FROM NHACUNGCAP WHERE ID = '" + id + "'");
			result.next();
			this.image.setImage(Util.loadImage("nhacungcap/" + result.getString("ID") + ".png", 200, 200, "general/Company.png"));
			this.id.setText(result.getString("ID"));
			this.nameLeft.setText(result.getString("TEN"));
			this.nameRight.setText(result.getString("TEN"));
			this.email.setText(result.getString("EMAIL"));
			this.address.setText(result.getString("DIACHI"));
			this.phone.setText(result.getString("SDT"));
			this.description.setText(result.getString("THONGTIN"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
