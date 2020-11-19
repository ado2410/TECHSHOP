package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL {
	static private Connection connection;
	
	static {
		start();
	}
	
	//Ket noi java voi SQL Server
	static private void start() {
		try {
			String url = "jdbc:sqlserver://localhost;databaseName=TECHSHOP;integratedSecurity=true;";
			connection = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Truy xuat du lieu du co so du lieu, dung voi cac lenh nhu select, view...
	 * @param command
	 * @return Table ket qua tu co so du lieu
	 */
	public static ResultSet query(String command) {
		try {
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			return statement.executeQuery(command);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Cap nhat co so du lieu, dung voi cac lenh nhu .update, insert, delete..
	 * @param command
	 */
	public static void update(String command) {
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(command);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
