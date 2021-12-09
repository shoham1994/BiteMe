package server;

import MySql.MySqlConnection;
import gui.ServerGUIController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * main class for running the application,opens main server screen
 */
public class ServerMain extends Application {
	public static ServerGUIController guiController;
	public static ServerBiteMe server;
	final public static int DEFAULT_PORT = 5555;

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ServerGUI.fxml"));
		Parent root = loader.load();
		guiController = loader.getController();
		Scene serverScene = new Scene(root);
		primaryStage.setTitle("BiteMe Server");
		primaryStage.setScene(serverScene);
		primaryStage.setOnCloseRequest(e -> stopServer());// make sure safe shutdown
		primaryStage.show();
	}

	public static void main(String args[]) throws Exception {
		launch(args);
	} // end main

	/**
	 * static method that upon being called,sets up a server connection to default
	 * port and connects to database
	 */
	public static boolean runServer() {
		if (server == null)
			server = new ServerBiteMe(DEFAULT_PORT);
		try {
			server.listen(); // Start listening for connections
			guiController.serverConnected();
		} catch (Exception ex) {
			System.out.println("ERROR - Could not listen for clients!");
			showError("Server encountered an issue and cannot run, please try again later");
			return false;
		}
		try {
			MySqlConnection.connectToDB();
			guiController.DBConnected();
		} catch (Exception e) {
			showError("Could not connect to SQL, please try again later");
			server.stopListening();
			return false;
		}
		return true;

	}
	
	/**
	 * static method that upon being called sends a crash message to all clients and
	 * exits the application
	 */
	public static void stopServer() {
		MySqlConnection.disconnectFromDB();
		if (server != null)
			server.sendToAllClients(new ServerMessage(ServerMessageTypes.SERVER_ERROR,
					"Server crashed!\nSorry for the inconvenience\nClick 'OK' to exit..."));
		System.exit(0);

	}

	static void showError(String msg) {
		Platform.runLater(() -> {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("");
			alert.setContentText(msg);
			alert.showAndWait();
		});
	}
}
