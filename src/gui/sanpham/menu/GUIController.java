package gui.sanpham.menu;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import util.GUILoader;
import util.SQL;

public class GUIController implements Initializable {
	@FXML
	private TextField filter;
	@FXML
	private VBox list;
	@FXML
	private TextField minPriceFilter;
	@FXML
	private TextField maxPriceFilter;
	@FXML
	private GridPane loaiSanPhamFilter;
	@FXML
	private GridPane nhaCungCapFilter;
	
	private ArrayList<String> loaiSanPhamIds = new ArrayList<>();
	private ArrayList<String> nhaCungCapIds = new ArrayList<>();
	
	@FXML
	private void onFilterAction() {
		initList();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initList();
		try {
			loaiSanPhamFilter.getChildren().clear();
			ResultSet loaiSanPhamIds = SQL.query("SELECT ID, TEN FROM LOAISANPHAM");
			int row = 0;
			int col = 0;
			while(loaiSanPhamIds.next()) {
				this.loaiSanPhamIds.add(loaiSanPhamIds.getString("ID"));
				CheckBox checkBox = new CheckBox();
				checkBox.setText(loaiSanPhamIds.getString("TEN"));
				loaiSanPhamFilter.add(checkBox, col, row);
				
				col++;
				if (col > 1) {
					col = 0;
					row ++;
				}		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			nhaCungCapFilter.getChildren().clear();
			ResultSet nhaSanXuatIds = SQL.query("SELECT ID, TEN FROM NHACUNGCAP");
			int row = 0;
			int col = 0;
			while(nhaSanXuatIds.next()) {
				this.nhaCungCapIds.add(nhaSanXuatIds.getString("ID"));
				CheckBox checkBox = new CheckBox();
				checkBox.setText(nhaSanXuatIds.getString("TEN"));
				nhaCungCapFilter.add(checkBox, col, row);
				
				col++;
				if (col > 1) {
					col = 0;
					row ++;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private String getloaiSanPhamFilter() {
		ObservableList<Node> list = loaiSanPhamFilter.getChildren();
		String value = "";
		for (int i = 0; i < list.size(); i++) {
			if (((CheckBox) list.get(i)).isSelected()) {
				if (value != "")
					value += ",";
				value += "'" + loaiSanPhamIds.get(i) + "'";
			}
		}
		return value;
	}
	
	private String getNhaSanXuatFilter() {
		ObservableList<Node> list = nhaCungCapFilter.getChildren();
		String value = "";
		for (int i = 0; i < list.size(); i++) {
			if (((CheckBox) list.get(i)).isSelected()) {
				if (value != "")
					value += ",";
				value += "'" + nhaCungCapIds.get(i) + "'";
			}
		}
		return value;
	}
	
	private void initList() {
		try {
			list.getChildren().clear();
			String query = "SELECT ID, TEN, GIA FROM SANPHAM WHERE ";
			if (!minPriceFilter.getText().isEmpty())
				query += "GIA > " + minPriceFilter.getText() + " AND ";
			if (!maxPriceFilter.getText().isEmpty())
				query += "GIA < " + maxPriceFilter.getText() + " AND ";
			if (!getloaiSanPhamFilter().isEmpty())
				query += "LOAISANPHAM IN (" + getloaiSanPhamFilter() + ") AND ";
			if (!getNhaSanXuatFilter().isEmpty())
				query += "NHACUNGCAP IN (" + getNhaSanXuatFilter() + ") AND ";
			query += "TEN LIKE N'%" + filter.getText() + "%'";
			ResultSet result = SQL.query(query);
			
			while(result.next()) {
				try {
					GUILoader gui = GUILoader.load("gui/sanpham/menu/SanPham");
					
					AnchorPane element = (AnchorPane) gui.getNode();
					SanPhamController elementController = (SanPhamController) gui.getController();
					
					elementController.setId(result.getString("ID"));
					elementController.setImage(result.getString("ID"));
					elementController.setName(result.getString("TEN"));
					elementController.setPrice(result.getString("GIA"));
					
					list.getChildren().add(element);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
