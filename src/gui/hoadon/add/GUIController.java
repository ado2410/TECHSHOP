package gui.hoadon.add;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import util.GUILoader;
import util.SQL;
import util.Util;

public class GUIController implements Initializable {
	@FXML
	private TextField filter;
	@FXML
	private GridPane productList;
	@FXML
	private VBox selectedProductList;
	@FXML
	private Text totalNumber;
	@FXML
	private Text totalPrice;
	@FXML
	private ChoiceBox<String> employeeList;
	@FXML
	private ChoiceBox<String> customerList;
	
	private ArrayList<SanPhamSelectedController> selectedProductControllers = new ArrayList<>();
	
	@FXML
	private void onFilterAction() {
		initProductList(filter.getText());
	}
	
	@FXML
	private void onAddBillAction() {
		if (selectedProductControllers.isEmpty()) {
			Util.showMessage("Không có sản phẩm nào được chọn");
			return;
		}
		
		if (employeeList.getValue() == null) {
			Util.showMessage("Chưa chọn nhân viên");
			return;
		}
		if (customerList.getValue() == null) {
			Util.showMessage("Chưa chọn khách hàng");
			return;
		}
		
		Random generator = new Random();
		int id = 0;
		do {
			id = generator.nextInt((99999999 - 00000000) + 1);
		} while (!isIdValid("HD" + Integer.toString(id)));
		
		for (int i = 0; i < selectedProductControllers.size(); i++) {
			SanPhamSelectedController controller = selectedProductControllers.get(i);
			if (getItemNumber(controller.getId()) < Integer.parseInt(controller.getNumber())) {
				Util.showMessage("Sản phẩm vượt quá trong kho hàng");
				return;
			}
		}
		
		LocalDateTime now = LocalDateTime.now();
		String currentDate = "'" + now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth() + "'";
		System.out.println("INSERT INTO HOADON (ID, NGAYTAO, NHANVIEN, KHACHHANG) VALUES ('HD" + id + "', " + currentDate + ", '" + employeeList.getValue() + "', '" + customerList.getValue() + "')");
		
		for (int i = 0; i < selectedProductControllers.size(); i++) {
			SanPhamSelectedController controller = selectedProductControllers.get(i);
			System.out.println("INSERT INTO CHITIETHOADON (ID, SANPHAM, SOLUONG) VALUES ('HD" + id + "', '" + controller.getId() + "', '" + controller.getNumber() + "')");
			ResultSet result = SQL.query("SELECT SOLUONG FROM SANPHAM WHERE ID = '" + controller.getId() + "'");
			try {
				result.next();
				System.out.println("UPDATE SANPHAM SET SOLUONG = " + (result.getInt("SOLUONG") - Integer.parseInt(controller.getNumber())) + " WHERE ID = " + controller.getId());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		Util.showMessage("Tạo thành công đơn hàng với id là HD" + id);
		GUILoader.loadToScene(GUILoader.load("gui/hoadon/add/GUI"));
	}
	
	private boolean isIdValid(String id) {
		ResultSet result = SQL.query("SELECT COUNT(ID) AS NUM FROM HOADON WHERE ID = '" + id + "'");
		try {
			result.next();
			if (result.getInt("NUM") == 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private int getItemNumber(String id) {
		ResultSet result = SQL.query("SELECT SOLUONG FROM SANPHAM WHERE ID = '" + id + "'");
		try {
			result.next();
			if (result.wasNull())
				return -1;
			return result.getInt("SOLUONG");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initProductList("");
		
		filter.setOnKeyPressed(keyEvent -> {
			   if (keyEvent.getCode().equals(KeyCode.ENTER)) {
			      onFilterAction();
			   }
			});
		
		ResultSet result = SQL.query("SELECT ID FROM NHANVIEN");
		try {
			while(result.next()) {
				employeeList.getItems().add(result.getString("ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		result = SQL.query("SELECT ID FROM KHACHHANG");
		try {
			while(result.next()) {
				customerList.getItems().add(result.getString("ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void initProductList(String filter) {
		try {
			productList.getChildren().clear();
			ResultSet result = SQL.query("SELECT ID, TEN, GIA FROM SANPHAM WHERE ID LIKE '%" + filter + "%' OR TEN LIKE '%" + filter + "%'");
			int colPerRow = 4;
			int row = 0;
			int col = 0;
			
			while(result.next()) {
				try {
					GUILoader gui = GUILoader.load("gui/hoadon/add/SanPham");
					
					AnchorPane element = (AnchorPane) gui.getNode();
					SanPhamController elementController = (SanPhamController) gui.getController();
					
					elementController.setId(result.getString("ID"));
					elementController.setImage(result.getString("ID"));
					elementController.setName(result.getString("TEN"));
					elementController.setPrice(result.getString("GIA"));
					elementController.setGUIController(this);
					
					productList.add(element, col, row);
					
					col++;
					if (col >= colPerRow) {
						col = 0;
						row++;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void addProduct(String id) {
		for (int i = 0; i < selectedProductControllers.size(); i++) {
			SanPhamSelectedController controller = selectedProductControllers.get(i);
			if (controller.getId().compareTo(id) == 0) {
				controller.onIncreaseAction();
				return;
			}
		}
		
		ResultSet result = SQL.query("SELECT ID, TEN, GIA, SOLUONG FROM SANPHAM WHERE ID = '" + id + "'");
		try {
			while(result.next()) {
				GUILoader gui = GUILoader.load("gui/hoadon/add/SanPhamSelected");
				
				AnchorPane element = (AnchorPane) gui.getNode();
				SanPhamSelectedController elementController = (SanPhamSelectedController) gui.getController();
				
				elementController.setId(result.getString("ID"));
				elementController.setName(result.getString("TEN"));
				elementController.setPrice(result.getString("GIA"));
				elementController.setGUIController(this);
				elementController.setNode(element);
				elementController.setMaxNum(result.getInt("SOLUONG"));
				
				selectedProductControllers.add(elementController);
				
				selectedProductList.getChildren().add(element);
				
				updateNumber();
				updatePrice();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteProduct(Node node) {
		selectedProductControllers.remove(selectedProductList.getChildren().indexOf(node));
		selectedProductList.getChildren().remove(node);
		updateNumber();
		updatePrice();
	}
	
	public void updateNumber() {
		int num = 0;
		for (int i = 0; i < selectedProductControllers.size(); i++) {
			num += Integer.parseInt(selectedProductControllers.get(i).getNumber());
		}
		totalNumber.setText(Integer.toString(num));
	}
	
	public void updatePrice() {
		int price = 0;
		for (int i = 0; i < selectedProductControllers.size(); i++) {
			price += Integer.parseInt(selectedProductControllers.get(i).getPrice());
		}
		totalPrice.setText(Integer.toString(price));
	}
}
