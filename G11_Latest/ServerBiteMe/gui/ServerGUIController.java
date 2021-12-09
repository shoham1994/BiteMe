package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.ProgressIndicator;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import ocsf.server.ConnectionToClient;
import server.ServerMain;

/**
 * controller class for the server UI
 */
public class ServerGUIController implements Initializable {
	@FXML
	private AnchorPane fillPane;

	@FXML
	private TitledPane _clientTitledPane;

	@FXML
	private ListView<ConnectionToClient> clientsConnectedList;

	@FXML
	private Button btnStart;

	@FXML
	private Circle _serverLedIndicator;

	@FXML
	private Circle _dbLedIndicator;

	@FXML
	private ImageView BiteMeSymbol1;

	@FXML
	private ImageView BiteMeSymbol2;

	@FXML
	private ProgressIndicator progressIndicator;

	/**
	 * client list in order to show connected clients in GUI
	 */
	ObservableList<ConnectionToClient> clientsConnectedObservableList;

	/**
	 * method that's called upon pressing the start button,sets up the client list
	 * in the GUI and calls ServerMain.runServer() note that upon being pressed,the
	 * button changes it's text to Exit, and upon being pressed again it shuts down
	 * the server
	 */
	@FXML
	void changeServerStatus(ActionEvent event) {
		progressIndicator.setVisible(true);// show progress Indicator
		serverDisconnected();
		DBDisconnected();
		Runnable task = () -> {
			if (btnStart.getText().equals("Start")) {
				if (ServerMain.runServer() == true) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							btnStart.setText("Exit");
						}
					});
				}

			} else {
				ServerMain.stopServer();
			}
			progressIndicator.setVisible(false);// hide progressIndicator
		};

		Thread thread = new Thread(task);
		thread.start();
	}

	public void serverConnected() {
		_serverLedIndicator.setFill(Paint.valueOf("LIGHTGREEN"));
	}

	private void serverDisconnected() {
		_serverLedIndicator.setFill(Paint.valueOf("RED"));
	}

	public void DBConnected() {
		_dbLedIndicator.setFill(Paint.valueOf("LIGHTGREEN"));
	}

	private void DBDisconnected() {
		_dbLedIndicator.setFill(Paint.valueOf("RED"));
	}

	public void connectClient(ConnectionToClient client) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				clientsConnectedObservableList.add(client);
			}
		});

	}

	public void disconnectClient(ConnectionToClient client) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				clientsConnectedObservableList.remove(client);
			}
		});

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// progressIndicator.setVisible(false);// hide progressIndicator
		clientsConnectedObservableList = FXCollections.observableArrayList();
		clientsConnectedList.setItems(clientsConnectedObservableList);
		btnStart.setOnMouseEntered(e -> {
			btnStart.setEffect(new DropShadow());
		});
		btnStart.setOnMouseExited(e -> {
			btnStart.setEffect(null);
		});
	}

}
