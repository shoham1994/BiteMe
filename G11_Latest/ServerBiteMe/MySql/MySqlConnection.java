package MySql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logic.User;

public class MySqlConnection {
	protected static Connection conn;

	public static void connectToDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver definition succeed");

		} catch (Exception ex) {
			/* handle the error */
			System.out.println("Driver definition failed");
		}

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/biteme?serverTimezone=IST", "root", "naft1995");
			System.out.println("SQL connection succeed");

		} catch (SQLException ex) {/* handle any errors */
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

	}

	/**
	 * disconnect java from DB.
	 */
	public static void disconnectFromDB() {
		try {
			conn.close();
			display("SQL close succeed");
		} catch (SQLException ex) {
			display("SQLException: " + ex.getMessage());
			display("SQLState: " + ex.getSQLState());
			display("VendorError: " + ex.getErrorCode());
		}
	}

	/*
	 * // need to do type of order public static String editOrderAddress(Object msg)
	 * { Message sqlMsg = (Message) msg; String str = (String) sqlMsg.getMsg();
	 * String[] dataFromClient = str.split(" "); if
	 * (dataFromClient[1].trim().isEmpty()) { return null; }
	 * System.out.println("OrderNumber = " + dataFromClient[0] + "OrderAddress = " +
	 * dataFromClient[1]); try { PreparedStatement updateOrderAddress = conn
	 * .prepareStatement("UPDATE order SET OrderAddress =? WHERE OrderNumber=?");
	 * updateOrderAddress.setString(2, (String) dataFromClient[0]);
	 * updateOrderAddress.setString(1, (String) dataFromClient[1]);
	 * updateOrderAddress.executeUpdate();
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } return (String)
	 * dataFromClient[1]; }
	 * 
	 * 
	 * public static String editTypeOfOrder(Object msg) { Message sqlMsg = (Message)
	 * msg; String str = (String) sqlMsg.getMsg(); String[] dataFromClient =
	 * str.split(" "); if (dataFromClient[1].trim().isEmpty()) { return null; }
	 * System.out.println("OrderNumber = " + dataFromClient[0] + "TypeOfOrder = " +
	 * dataFromClient[1]); try { PreparedStatement updateOrderAddress = conn
	 * .prepareStatement("UPDATE order SET TypeOfOrder=? WHERE OrderNumber=?");
	 * updateOrderAddress.setString(2, (String) dataFromClient[0]);
	 * updateOrderAddress.setString(1, (String) dataFromClient[1]);
	 * updateOrderAddress.executeUpdate();
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } return (String)
	 * dataFromClient[1]; }
	 */

	@SuppressWarnings("unchecked")
	public static Object parseData(Object msg) {
		SqlMessage sqlMsg = (SqlMessage) msg;
		switch (sqlMsg.getType()) {
		// from this moment clientMsg.getType()==clientMsgType
		// all request to get/update data from SQL is different case
		// clientMsg.getMessage contain arrayList of data to sent to Sql
		// e.g: List user details contains user_name and password and the needed sql
		// operation type call Login
		case getUser:
			List<String> userDetails = (ArrayList<String>) sqlMsg.getMessage();
			PreparedStatement stmt;
			try {
				stmt = conn.prepareStatement("SELECT * FROM users where user_name=? and password =?");
				stmt.setString(1, userDetails.get(0));
				stmt.setString(2, userDetails.get(1));
				ResultSet rs = stmt.executeQuery();
				while (rs.next())
					return new User(rs.getString(1), rs.getString(2), rs.getString(3));
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			break;
		default:
			break;
		}
		return null;

	}

	public static User getUser(Object msg) {
		String sqlMsg = (String) msg;
		String[] dataFromClient = sqlMsg.split(" ");
		System.out.println(dataFromClient[0] + " !!!!");
		User user = null;
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement("SELECT * FROM users where user_name=? and password =?");
			stmt.setString(1, dataFromClient[0]);
			stmt.setString(2, dataFromClient[1]);
			ResultSet rs = stmt.executeQuery();
			while (rs.next())
				user = new User(rs.getString(1), rs.getString(2), rs.getString(3));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}

	/**
	 * This method displays a message into the console.
	 *
	 * @param message The string to be displayed.
	 */
	public static void display(String message) {
		System.out.println("> " + message);
	}
}
