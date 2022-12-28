package ru.itis.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.time.LocalDateTime;

import ru.itis.protocol.Message;
import ru.itis.protocol.MessageManager;
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
		Message<LocalDateTime> message = MessageManager.readMessage(clientSocket.getInputStream());
		System.out.println("got message " + message);

		byte[] convertedMessage = MessageManager.convertMessage(message);
		OutputStream os = clientSocket.getOutputStream();
		os.write(convertedMessage);
		os.flush();
		System.out.println("sent message to client");
	}
}
