package ru.itis.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

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
}
