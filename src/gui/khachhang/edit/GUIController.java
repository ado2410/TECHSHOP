package gui.khachhang.edit;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

import gui.field.ChoiceBoxController;
import gui.field.DateController;
import gui.field.FieldController;
import gui.field.NumberController;
import gui.field.StringController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import util.GUILoader;
import util.SQL;
import util.Util;

public class GUIController implements Initializable {
	@FXML
	private GridPane grid;
	
	private String[] names = {"Tên", "Ngày sinh", "Giới tính", "Số điện thoại", "Email", "Mã giới thiệu", "Địa chỉ", "Tỉnh", "Ngày tham gia"};
	private String[] columns = {"TEN", "NGAYSINH", "GIOITINH", "SDT", "EMAIL", "MAGIOITHIEU", "DIACHI", "TINH", "NGAYTHAMGIA"};
	private String[] fields = {"gui/field/String", "gui/field/Date", "gui/field/ChoiceBox", "gui/field/String", "gui/field/String", "gui/field/Number", "gui/field/String", "gui/field/ChoiceBox", "gui/field/Date"};
	private FieldController[] fieldsController = new FieldController[9];
	
	private String[] gioiTinhValues = {"0", "1"};
	private String[] gioiTinhHints = {"Nam", "Nữ"};
	
	private Stage stage;
	
	private String id = "";
	
	private int mode = 0;
	
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initField();
	}
	
	@FXML
	private void onOKAction() {
		if (id.isEmpty()) {
			Random generator = new Random();
			do {
				id = "KH" + Integer.toString(generator.nextInt((99999999 - 00000000) + 1));
			} while (!isIdValid(id));
		}
		
		if (mode == 0) {
			String execute = "INSERT INTO KHACHHANG (ID, TEN, NGAYSINH, GIOITINH, SDT, EMAIL, MAGIOITHIEU, DIACHI, TINH, NGAYTHAMGIA) VALUES (";
			execute += "'" + id + "', N'";
			execute += ((StringController)fieldsController[0]).getValue() + "', '";
			execute += ((DateController)fieldsController[1]).getValueByString() + "', ";
			execute += ((ChoiceBoxController)fieldsController[2]).getValue() + ", '";
			execute += ((StringController)fieldsController[3]).getValue() + "', '";
			execute += ((StringController)fieldsController[4]).getValue() + "', ";
			execute += ((NumberController)fieldsController[5]).getValue() + ", N'";
			execute += ((StringController)fieldsController[6]).getValue() + "', '";
			execute += ((ChoiceBoxController)fieldsController[7]).getValue() + "', '";
			execute += ((DateController)fieldsController[8]).getValueByString() + "')";
			SQL.update(execute);
			Util.showMessage("Tạo thành công khách hàng với id là " + id);
			onCancelAction();
			GUILoader.loadToScene(GUILoader.load("gui/khachhang/menu/GUI"), false);
		}
		else {
			String execute = "UPDATE KHACHHANG SET ";
			execute += "TEN = N'" + ((StringController)fieldsController[0]).getValue() + "', ";
			execute += "NGAYSINH = '" + ((DateController)fieldsController[1]).getValueByString() + "', ";
			execute += "GIOITINH = " + ((ChoiceBoxController)fieldsController[2]).getValue() + ", ";
			execute += "SDT = '" + ((StringController)fieldsController[3]).getValue() + "', ";
			execute += "EMAIL = '" + ((StringController)fieldsController[4]).getValue() + "', ";
			execute += "MAGIOITHIEU = " + ((NumberController)fieldsController[5]).getValue() + ", ";
			execute += "DIACHI = N'" + ((StringController)fieldsController[6]).getValue() + "', ";
			execute += "TINH = '" + ((ChoiceBoxController)fieldsController[7]).getValue() + "', ";
			execute += "NGAYTHAMGIA = '" + ((DateController)fieldsController[8]).getValueByString() + "' WHERE ID = '" + id + "'";
			SQL.update(execute);
			Util.showMessage("Cập nhật thành công khách hàng với id là " + id);
			onCancelAction();
			GUILoader loader = GUILoader.load("gui/khachhang/detail/GUI");
			gui.khachhang.detail.GUIController controller = (gui.khachhang.detail.GUIController) loader.getController();
			controller.initInfo(id);
			GUILoader.loadToScene(loader, false);
		}
	}
	
	private boolean isIdValid(String id) {
		ResultSet result = SQL.query("SELECT COUNT(ID) AS NUM FROM KHACHHANG WHERE ID = '" + id + "'");
		try {
			result.next();
			if (result.getInt("NUM") == 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@FXML
	private void onCancelAction() {
		stage.close();
	}
	
	public void edit(String id) {
		ResultSet result = SQL.query("SELECT ID, TEN, NGAYSINH, GIOITINH, SDT, EMAIL, MAGIOITHIEU, DIACHI, TINH, NGAYTHAMGIA FROM KHACHHANG WHERE ID = '" + id + "'");
		try {
			while (result.next()) {
				this.id = result.getString("ID");
				this.mode = 1;
				((StringController)fieldsController[0]).setValue(result.getString("TEN"));
				((DateController)fieldsController[1]).setValue(result.getDate("NGAYSINH").toLocalDate());
				((ChoiceBoxController)fieldsController[2]).setValue(result.getString("GIOITINH"));
				((StringController)fieldsController[3]).setValue(result.getString("SDT"));
				((StringController)fieldsController[4]).setValue(result.getString("EMAIL"));
				((NumberController)fieldsController[5]).setValue(result.getInt("MAGIOITHIEU"));
				((StringController)fieldsController[6]).setValue(result.getString("DIACHI"));
				((ChoiceBoxController)fieldsController[7]).setValue(result.getString("TINH"));
				((DateController)fieldsController[8]).setValue(result.getDate("NGAYTHAMGIA").toLocalDate());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void initField() {
		
		int row = 0;
		int col = 0;
		for (int i = 0; i < columns.length; i++) {
			GUILoader loader = GUILoader.load(fields[i]);
			
			if (i == 2) {
				ChoiceBoxController controller = (ChoiceBoxController) loader.getController();
				controller.setName(names[i]);
				controller.setChoiceList(gioiTinhValues, gioiTinhHints);
				controller.setValue(0);
			}
			else if (i == 7) {
				ChoiceBoxController controller = (ChoiceBoxController) loader.getController();
				controller.setName(names[i]);
				
				ResultSet result1 = SQL.query("SELECT COUNT(ID) AS NUM FROM TINH");
				ResultSet result2 = SQL.query("SELECT ID, TEN FROM TINH");
				try {
					result1.next();
					int number = result1.getInt("NUM");
					int j = 0;
					String[] values = new String[number];
					String[] hints = new String[number];
					while(result2.next()) {
						values[j] = result2.getString("ID");
						hints[j] = result2.getString("TEN");
						j++;
					}
					controller.setChoiceList(values, hints);
					controller.setValue(0);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else {
				FieldController controller = (FieldController) loader.getController();
				controller.setName(names[i]);
			}
			
			grid.add(loader.getNode(), col, row);
			fieldsController[i] = (FieldController) loader.getController();
			
			col++;
			if (col > 1) {
				col = 0;
				row++;
			}
		}
	}
}
