package gui;

import java.io.IOException;

import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import client.ClientMessage;
import client.ClientMessageType;


public class BiteMeHomeController {

	@FXML
	private Button btnExit;

	@FXML
	private Button btnLogin;

	@FXML
	public void initialize() {
	}

	@FXML
	void login(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		Stage primaryStage = new Stage();
		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Parent root = loader.load(getClass().getResource("/gui/Login.fxml").openStream());
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Login");
		primaryStage.show();
	}


	public void exit(ActionEvent event) throws Exception {
		ClientMessage msg;
		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		msg = new ClientMessage(ClientMessageType.Disconnected, "Client disconnected");
		ClientUI.chat.accept(msg);
		ClientUI.chat.quit();
		System.out.println("Exit BiteMe System");
		System.exit(0);
	}
}
