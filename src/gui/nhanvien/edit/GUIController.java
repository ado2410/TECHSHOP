package gui.nhanvien.edit;

import java.net.URL;
import java.util.ResourceBundle;

import gui.field.FieldController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import util.GUILoader;

public class GUIController implements Initializable {
	@FXML
	private GridPane grid;
	
	private String[] names = {"Tên", "Ngày sinh", "Giới tính", "Số điện thoại", "Email", "Ngày làm việc", "Hệ số", "Phụ cấp", "Địa chỉ", "Tinh", "Công việc"};
	private String[] columns = {"TEN", "NGAYSINH", "GIOITINH", "SDT", "EMAIL", "NGAYLAMVIEC", "HESO", "PHUCAP", "DIACHI", "TINH", "CONGVIEC"};
	private String[] fields = {"gui/field/String", "gui/field/Date", "gui/field/ChoiceBox", "gui/field/String", "gui/field/String", "gui/field/Date", "gui/field/Float", "gui/field/Number", "gui/field/String", "gui/field/String", "gui/field/String", };
	
	private String[] gioiTinhValues = {"0", "1"};
	private String[] gioiTinhHints = {"Nam", "Nữ"};
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initField();
		
	}
	
	private void initField() {
		
		int row = 0;
		int col = 0;
		for (int i = 0; i < columns.length; i++) {
			GUILoader loader = GUILoader.load(fields[i]);
			
			FieldController controller = (FieldController) loader.getController();
			controller.setName(names[i]);
			
			grid.add(loader.getNode(), col, row);
			
			col++;
			if (col > 1) {
				col = 0;
				row++;
			}
		}
	}
}
