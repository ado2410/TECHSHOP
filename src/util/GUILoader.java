package util;

import java.io.IOException;
import java.util.Stack;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class GUILoader {
	public static BorderPane mainNode;
	private Node node;
	private Object controller;
	
	private static Stack<GUILoader> previousGUIs = new Stack<>();
	private static GUILoader currentGUI;
	
	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
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
	
	/**
	 * Thay doi khung canh sang canh moi
	 * @param node
	 */
	static public void loadToScene(GUILoader guiLoader) {
		previousGUIs.push(currentGUI);
		mainNode.setCenter(guiLoader.getNode());
		currentGUI = guiLoader;
	}
	
	/**
	 * Quay lai canh cu
	 */
	static public void loadPreviousScene() {
		if (previousGUIs.empty())
			loadMainScene();
		else 
			mainNode.setCenter(previousGUIs.pop().getNode());
	}
	
	static public GUILoader getCurrentGUI() {
		return currentGUI;
	}
	
	/**
	 * Load trang chu
	 */
	static public void loadMainScene() {
		GUILoader.loadToScene(GUILoader.load("gui/general/MainMenu"));
		previousGUIs.clear();
	}
}
