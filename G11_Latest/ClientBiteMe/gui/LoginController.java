package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import client.ChatClient;
import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.User;
import client.ClientMessage;
import client.ClientMessageType;


public class LoginController {

	@FXML
	private Button btnLogin;

	@FXML
	private Button btnExit;

	@FXML
	private TextField txtUserName;

	@FXML
	private TextField txtPassword;

	@FXML
	private Label lblError;

	@FXML
	public void initialize() {
	}

	@FXML
	void login(ActionEvent event) {
		Parent root = null;
		User user;
		user = getUser(txtUserName.getText(), txtPassword.getText());
		if (user == null) {
			lblError.setText("User name or password incorrect");
		}

		else {
			FXMLLoader loader = new FXMLLoader();
			((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
			Stage primaryStage = new Stage();
			String controllerByUserType = "/gui/" + user.getType() + "Page.fxml";
			try {
				root = loader.load(getClass().getResource(controllerByUserType).openStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle(user.getType() + " Page");
			primaryStage.show();

		}
	}

	public User getUser(String userName, String password) {
		User u = null;
		ClientMessage msg;
		List<String> userDetails =new ArrayList<>();
		userDetails.add(userName);
		userDetails.add(password);
		msg = new ClientMessage(ClientMessageType.Login, userDetails);
		ClientUI.chat.accept((Object) msg);
		u = (User) ChatClient.returnedValueFromServer;
		return u;
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
