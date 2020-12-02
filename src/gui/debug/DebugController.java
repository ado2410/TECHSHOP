package gui.debug;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DebugController {
	@FXML
	private Text message;
	
	private Stage stage;
	
	@FXML
	private void onOkAction() {
		stage.close();
	}
	
	public void setMessage(String message) {
		this.message.setText(message);
	}
	
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
