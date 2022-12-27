package ru.itis;

import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;

import ru.itis.protocol.Message;
import ru.itis.protocol.MessageFabric;
import ru.itis.server.Server;

/**
 * @author Vitaly Chekushkin
 */
public class ServerRun {
	public static void main(String[] args) throws IOException, InterruptedException {
		Server server = new Server();
		server.start();
		Thread.sleep(5000);

		Socket clientSocket = server.getClientServerById(1);
		System.out.println("got client");
		Message<LocalDateTime> message = MessageFabric.readMessage(clientSocket.getInputStream());
		System.out.println("got message " + message);
		MessageFabric.sendMessage(message, clientSocket.getOutputStream());
	}
}
