package gui.congviec.menu;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import util.Util;

public class CongViecController {
	@FXML
	private ImageView image;
	@FXML
	private Text name;
	@FXML
	private Text info;
	@FXML
	private Text salary;
	@FXML
	private Text employeeCount;
	
	public void setImage(String fileName) {
		this.image.setImage(Util.loadImage("nhanvien/" + fileName + ".png", 100, 100, "general/Avatar.png"));
	}
	
	public void setName(String name) {
		this.name.setText(name);
	}
	
	public void setInfo(String info) {
		this.info.setText(info);
	}
	
	public void setSalary(String salary) {
		this.salary.setText(salary);
	}
	
	public void setEmployeeCount(String employeeCount) {
		this.employeeCount.setText(employeeCount);
	}
}
