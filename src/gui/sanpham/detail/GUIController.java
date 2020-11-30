package gui.sanpham.detail;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import util.SQL;
import util.Util;

public class GUIController {
	@FXML
	private Label name;
	@FXML
	private Label id;
	@FXML
	private Text price;
	@FXML
	private Label depth;
	@FXML
	private Label width;
	@FXML
	private Label height;
	@FXML
	private Label color;
	@FXML
	private Label battery;
	@FXML
	private Label weight;
	@FXML
	private ImageView image;
	@FXML
	private Label number;
	
	public void initInfo(String id) {
		try {
			ResultSet result = SQL.query("SELECT SANPHAM.ID AS ID, SANPHAM.TEN AS TEN, GIA, CAO, DAI, RONG, MAU.TEN AS MAU, KHOILUONG, SOLUONG FROM SANPHAM INNER JOIN MAU ON SANPHAM.MAU = MAU.ID WHERE SANPHAM.ID = '" + id + "'");
			result.next();
			this.image.setImage(Util.loadImage("sanpham/" + result.getString("ID") + ".png", 250, 250, "general/Product.png"));
			this.id.setText(result.getString("ID"));
			this.id.setText(result.getString("ID"));
			this.name.setText(result.getString("TEN"));
			this.price.setText(result.getString("GIA"));
			this.depth.setText(result.getString("CAO"));
			this.width.setText(result.getString("DAI"));
			this.height.setText(result.getString("RONG"));
			this.color.setText(result.getString("MAU"));
			this.weight.setText(result.getString("KHOILUONG"));
			this.number.setText(result.getString("SOLUONG"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
