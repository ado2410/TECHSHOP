package gui.sanpham.edit;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

import gui.field.ChoiceBoxController;
import gui.field.DateController;
import gui.field.FieldController;
import gui.field.FloatController;
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
	
	private String[] names = {"Tên", "Giá", "Dài", "Rộng", "Cao", "Màu", "Khối lượng", "Số lượng", "Ngày nhập", "Thông tin", "Loại sản phẩm", "Nhà cung cấp"};
	private String[] columns = {"TEN", "GIA", "DAI", "RONG", "CAO", "MAU", "KHOILUONG", "SOLUONG", "NGAYNHAP", "THONGTIN", "LOAISANPHAM", "NHACUNGCAP"};
	private String[] fields = {"gui/field/String", "gui/field/Number", "gui/field/Float", "gui/field/Float", "gui/field/Float", "gui/field/ChoiceBox", "gui/field/Float", "gui/field/Number", "gui/field/Date", "gui/field/String", "gui/field/ChoiceBox", "gui/field/ChoiceBox"};
	private FieldController[] fieldsController = new FieldController[12];
	
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
				id = "SP" + Integer.toString(generator.nextInt((99999999 - 00000000) + 1));
			} while (!isIdValid(id));
		}
		
		if (mode == 0) {
			String execute = "INSERT INTO SANPHAM (ID, TEN, GIA, DAI, RONG, CAO, MAU, KHOILUONG, SOLUONG, NGAYNHAP, THONGTIN, LOAISANPHAM, NHACUNGCAP) VALUES (";
			execute += "'" + id + "', N'";
			execute += ((StringController)fieldsController[0]).getValue() + "', ";
			execute += ((NumberController)fieldsController[1]).getValue() + ", ";
			execute += ((FloatController)fieldsController[2]).getValue() + ", ";
			execute += ((FloatController)fieldsController[3]).getValue() + ", ";
			execute += ((FloatController)fieldsController[4]).getValue() + ", '";
			execute += ((ChoiceBoxController)fieldsController[5]).getValue() + "', ";
			execute += ((FloatController)fieldsController[6]).getValue() + ", ";
			execute += ((NumberController)fieldsController[7]).getValue() + ", '";
			execute += ((DateController)fieldsController[8]).getValue() + "', N'";
			execute += ((StringController)fieldsController[9]).getValue() + "', '";
			execute += ((ChoiceBoxController)fieldsController[10]).getValue() + "', '";
			execute += ((ChoiceBoxController)fieldsController[11]).getValue() + "')";
			SQL.update(execute);
			//System.out.println(execute);
			Util.showMessage("Tạo thành sản phẩm với id là " + id);
			onCancelAction();
			GUILoader.loadToScene(GUILoader.load("gui/sanpham/menu/GUI"), false);
		}
		else {
			String execute = "UPDATE SANPHAM SET ";
			execute += "TEN = N'" + ((StringController)fieldsController[0]).getValue() + "', ";
			execute += "GIA = " + ((NumberController)fieldsController[1]).getValue() + ", ";
			execute += "DAI = " + ((FloatController)fieldsController[2]).getValue() + ", ";
			execute += "RONG = " + ((FloatController)fieldsController[3]).getValue() + ", ";
			execute += "CAO = " + ((FloatController)fieldsController[4]).getValue() + ", ";
			execute += "MAU = '" + ((ChoiceBoxController)fieldsController[5]).getValue() + "', ";
			execute += "KHOILUONG = " + ((FloatController)fieldsController[6]).getValue() + ", ";
			execute += "SOLUONG = " + ((NumberController)fieldsController[7]).getValue() + ", ";
			execute += "NGAYNHAP = '" + ((DateController)fieldsController[8]).getValueByString() + "', ";
			execute += "THONGTIN = N'" + ((StringController)fieldsController[9]).getValue() + "', ";
			execute += "LOAISANPHAM = '" + ((ChoiceBoxController)fieldsController[10]).getValue() + "', ";
			execute += "NHACUNGCAP = '" + ((ChoiceBoxController)fieldsController[11]).getValue() + "' WHERE ID = '" + id + "'";
			SQL.update(execute);
			//System.out.println(execute);
			Util.showMessage("Cập nhật thành công sản phẩm id là " + id);
			onCancelAction();
			GUILoader loader = GUILoader.load("gui/sanpham/detail/GUI");
			gui.sanpham.detail.GUIController controller = (gui.sanpham.detail.GUIController) loader.getController();
			controller.initInfo(id);
			GUILoader.loadToScene(loader, false);
		}
	}
	
	private boolean isIdValid(String id) {
		ResultSet result = SQL.query("SELECT COUNT(ID) AS NUM FROM SANPHAM WHERE ID = '" + id + "'");
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
		ResultSet result = SQL.query("SELECT ID, TEN, GIA, DAI, RONG, CAO, MAU, KHOILUONG, SOLUONG, NGAYNHAP, THONGTIN, LOAISANPHAM, NHACUNGCAP FROM SANPHAM WHERE ID = '" + id + "'");
		try {
			while (result.next()) {
				this.id = result.getString("ID");
				this.mode = 1;
				((StringController)fieldsController[0]).setValue(result.getString("TEN"));
				((NumberController)fieldsController[1]).setValue(result.getInt("GIA"));
				((FloatController)fieldsController[2]).setValue(result.getFloat("DAI"));
				((FloatController)fieldsController[3]).setValue(result.getFloat("RONG"));
				((FloatController)fieldsController[4]).setValue(result.getFloat("CAO"));
				((ChoiceBoxController)fieldsController[5]).setValue(result.getString("MAU"));
				((FloatController)fieldsController[6]).setValue(result.getFloat("KHOILUONG"));
				((NumberController)fieldsController[7]).setValue(result.getInt("SOLUONG"));
				((DateController)fieldsController[8]).setValue(result.getDate("NGAYNHAP").toLocalDate());
				((StringController)fieldsController[9]).setValue(result.getString("THONGTIN"));
				((ChoiceBoxController)fieldsController[10]).setValue(result.getString("LOAISANPHAM"));
				((ChoiceBoxController)fieldsController[11]).setValue(result.getString("NHACUNGCAP"));
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
			
			if (i == 5 || i == 10 || i == 11) {
				ChoiceBoxController controller = (ChoiceBoxController) loader.getController();
				controller.setName(names[i]);
				
				String table = "";
				if (i == 5) table = "MAU";
				else if (i == 10) table = "LOAISANPHAM";
				else if (i == 11) table = "NHACUNGCAP";
				
				ResultSet result1 = SQL.query("SELECT COUNT(ID) AS NUM FROM " + table);
				ResultSet result2 = SQL.query("SELECT ID, TEN FROM " + table);
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
