package application;

import javafx.application.Application;
import javafx.stage.Stage;
import util.GUILoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			GUILoader gui = GUILoader.load("gui/general/Main");
			
			Scene scene = new Scene((BorderPane)gui.getNode());
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("TECHNOLOGY SHOP");
			primaryStage.show();
			
			GUILoader.mainNode = (BorderPane) gui.getNode();
			GUILoader.loadMainScene();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
