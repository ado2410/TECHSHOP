package util;

import java.io.IOException;

import javafx.fxml.FXMLLoader;

public class GUILoader {
	private Object node;
	private Object controller;
	
	public Object getNode() {
		return node;
	}

	public void setNode(Object node) {
		this.node = node;
	}

	public Object getController() {
		return controller;
	}

	public void setController(Object controller) {
		this.controller = controller;
	}
	
	/**
	 * Load tep tin fxml
	 * @param url khong can chua duoi .fxml
	 * @return node(UI) va controller(dieu khien)
	 */
	static public GUILoader load(String url) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GUILoader.class.getResource("../" + url + ".fxml"));
			GUILoader gui = new GUILoader();
			gui.setNode(loader.load());
			gui.setController(loader.getController());
			return gui;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
