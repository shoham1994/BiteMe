
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 
package server;

import java.io.IOException;
import java.util.ArrayList;
import server.ServerMessageTypes;
import MySql.MySqlConnection;
import MySql.SqlMessage;
import MySql.SqlMessageTypes;
import client.ClientMessage;
import logic.User;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

/**
 * This class overrides some of the methods in the abstract superclass in order
 * to give more functionality to the server.
 *
 
 
 
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */
/**
 * GoNatureServer is responsible to handling client messages and sending
 * messages to client
 */
public class ServerBiteMe extends AbstractServer {
	// Class variables *****************

	private ArrayList<ConnectionToClient> conToClientMng;
	// private ArrayList<currentExam> currentExams;
	public ArrayList<Object> userList;
	// Constructors ******************

	/**
	 * Constructs an instance of the echo server.
	 *
	 * @param port The port number to connect on.
	 * 
	 */
	public ServerBiteMe(int port) {
		super(port);
		userList = new ArrayList<>();
		conToClientMng = new ArrayList<>();
		// currentExams = new ArrayList<>();
	}

	/**
	 * @param inputData Data that contains Exam id and Date.
	 * @return Report for the exam.
	 * @throws SQLException
	 * @throws ParseException
	 */
	/*
	 * public Object getTeacherSolvedExamReport(String[] inputData) throws
	 * SQLException, ParseException { Object returnVal; returnVal =
	 * MySQLConnection.getTeacherSolvedExamReport(inputData); return returnVal; }
	 */

	/*
	 * public Object validatePerson(String[] IdAndPw) throws SQLException { Object
	 * returnVal; returnVal = MySQLConnection.validatePerson(IdAndPw); if
	 * (userList.contains(returnVal)) returnVal = "logged in"; else if (returnVal !=
	 * null) userList.add(returnVal); return returnVal; }
	 */

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 */
	protected void serverStarted() {
		System.out.println("Server listening for connections on port " + getPort());
	}

	/**
	 * This method overrides the one in the superclass. Called when the server stops
	 * listening for connections.
	 */
	protected void serverStopped() {
		System.out.println("Server has stopped listening for connections.");
	}

	@Override
	protected void clientConnected(ConnectionToClient client) {

		ServerMain.guiController.connectClient(client);
	}

	@Override
	synchronized protected void clientDisconnected(ConnectionToClient client) {
		ServerMain.guiController.disconnectClient(client);
	}

	public void handleMessageFromClient(Object msg, ConnectionToClient client) {
		// this method is the first stop after client action from GUI, here we ask for
		// getting data from SQL/server
		Object returnVal = null;
		ServerMessageTypes type = null;
		try {
			if (msg instanceof ClientMessage) {
				ClientMessage clientMsg = (ClientMessage) msg;
				switch (clientMsg.getType()) {
				case Disconnected:
					if (clientMsg.getMessage() != null)
						userList.remove(clientMsg.getMessage());
					ServerMain.guiController.disconnectClient(client);
					break;
				// case Logout:
				// if (clientMsg.getMessage() != null)
				// userList.remove(clientMsg.getMessage());
				// returnVal = null;
				// type = ServerMessageTypes.LogoutSucceess;
				// break;
				case Login:
					SqlMessage sqlMessage = new SqlMessage(SqlMessageTypes.getUser, clientMsg.getMessage());
					returnVal = MySqlConnection.parseData(sqlMessage);
					type = ServerMessageTypes.Login;
					break;
				default:
					break;
				}

			}
		} catch (Exception e) {
			try {
				if (client != null)
					client.sendToClient(new ServerMessage(ServerMessageTypes.SERVER_ERROR, null));
				e.printStackTrace();
			} catch (IOException e1) {
			}
		}

		try {
			if (client != null)
				client.sendToClient(new ServerMessage(type, returnVal));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

// End of EchoServer class
