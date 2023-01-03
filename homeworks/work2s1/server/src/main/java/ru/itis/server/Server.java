package ru.itis.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import ru.itis.protocol.Message;
import ru.itis.protocol.MessageManager;
import ru.itis.server.configuration.ServerConfiguration;

/**
 * @author Vitaly Chekushkin
 */
public class Server {
	private static AtomicInteger atomicInteger = new AtomicInteger(0);

	private ServerSocket serverSocket;
	private Map<Object, Socket> clients;

	public Server() {
		try {
			this.serverSocket = new ServerSocket(ServerConfiguration.PORT);
			this.clients = new ConcurrentHashMap<>();
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void start() {
		Thread thread = new Thread(() -> {
			while(true) {
				try {
					Socket socket = serverSocket.accept();
					int id = atomicInteger.incrementAndGet();
					System.out.println("Client with id = " + id + " connected to the server");

					addSocketListener(socket);
					clients.put(id, socket);
				}
				catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		});
		thread.start();
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public Socket getClientServerById(Integer id) {
		return clients.get(id);
	}

	private void addSocketListener(Socket client) {
		Thread thread = new Thread(() -> {
			int numberOfMessage = 0;
			while (client.isConnected()) {
				try {
					System.out.println("w8 for message " + ++numberOfMessage);
					Message<?> message = MessageManager.readMessage(client.getInputStream());
					System.out.println("Message " + numberOfMessage + " comes to server by client:\n" + message);

					byte[] serializedMessage = MessageManager.convertMessage(message);
					OutputStream os = client.getOutputStream();
					os.write(serializedMessage);
					os.flush();
				}
				catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		});
		thread.start();
	}
}
