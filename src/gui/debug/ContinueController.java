package gui.debug;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ContinueController {
	@FXML
	private Text message;
	
	private Stage stage;
	
	private Object object;
	private String function;
	private Object[] params;
	
	@FXML
	private void onOkAction() {
		try {
			Class[] parameterTypes = new Class[params.length];
			for (int i = 0; i < params.length; i++)
				parameterTypes[i] = params[i].getClass();
			
			Method method = object.getClass().getMethod(function, parameterTypes);
			method.invoke(object, params);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		onCancelAction();
	}
	@FXML void onCancelAction() {
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
	
	public void setFunction(Object object, String function, Object... params) {
		this.object = object;
		this.function = function;
		this.params = params;
	}
}
