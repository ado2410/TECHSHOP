package gui.general;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import util.GUILoader;

public class MainMenuController implements Initializable {
	@FXML
	private GridPane grid;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initMenuList();
	}
	
	private void initMenuList() {
		String[] names = {"Thêm hóa đơn", "Khách hàng", "Nhân viên", "Loại sản phẩm", "Sản phẩm", "Nhà cung cấp", "Công việc", "Hóa đơn"};
		String[] paths = {"gui/hoadon/add/GUI", "gui/khachhang/menu/GUI", "gui/nhanvien/menu/GUI", "gui/loaisanpham/GUI", "gui/sanpham/menu/GUI", "gui/nhacungcap/menu/GUI", "gui/congviec/menu/GUI", "gui/hoadon/menu/GUI"};
		String[] images = {"AddBill", "Customer", "Employee", "ProductCategory", "Product", "Company", "Job", "Bill"};
		grid.getChildren().clear();
		
		int number = names.length;
		int colPerRow = 3;
		int row = 0;
		int col = 0;
		
		while(col + row*colPerRow < number) {
			GUILoader gui = GUILoader.load("gui/general/List");
			AnchorPane node = (AnchorPane) gui.getNode();
			ListController controller = (ListController) gui.getController();
			controller.setName(names[col + row*colPerRow]);
			controller.setLink(paths[col + row*colPerRow]);
			controller.setImage(images[col + row*colPerRow]);
			
			grid.add(node, col, row);
			
			col++;
			if (col >= colPerRow) {
				col = 0;
				row++;
			}
		}
	}
}
