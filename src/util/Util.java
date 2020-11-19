package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.scene.image.Image;

public class Util {
	public static Image loadImage(String path, int width, int height) {
		try {
			String fullPath = System.getProperty("user.dir").replace('\\', '/') + "/images/" + path;
			InputStream input;
			input = new FileInputStream(fullPath);
			return new Image(input, width, height, false, false);
		} catch (FileNotFoundException e) {
			System.out.print("Image path doesn't exist!");
		}
		return null;
	}
	
	public static Image loadImage(String path, int width, int height, String defaultPath) {
		Image image = loadImage(path, width, height);
		if (image != null)
			return image;
		else
			return loadImage(defaultPath, width, height);
	}
}
