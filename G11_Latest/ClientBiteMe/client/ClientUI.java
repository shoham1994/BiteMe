package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientUI extends Application {
	public static ClientController chat; // only one instance - singleton

	public static void main(String args[]) throws Exception {
		launch(args);
	} // end main

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// before submitting prototype - replace try-catch with this
		
		/*
		 * BufferedReader fromConsole = new BufferedReader(new
		 * InputStreamReader(System.in)); String message;
		 * System.out.println("Please insert IP"); message = fromConsole.readLine();
		 * chat = new ClientController(message, 5555);
		 */
		try {
		chat = new ClientController("localhost", 5555);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// load and show primary window
		Parent root = FXMLLoader.load(getClass().getResource("/gui/BiteMeHome.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("BiteMe System");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
}
