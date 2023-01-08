package com.example.client.client;

import java.io.IOException;
import java.net.Socket;

/**
 * @author Vitaly Chekushkin
 */
public class SocketClient {
	private static SocketClient socketClient;

	public static synchronized void connect(String host, int port) {
		try {
			socketClient = new SocketClient(host, port);
		}
		catch (IOException e) {
			throw new RuntimeException("Cant connect to " + host + ":" + port);
		}
	}

	public static SocketClient getInstance() {
		return socketClient;
	}

	private final Socket socket;

	private SocketClient(String host, int port) throws IOException {
		this.socket = new Socket(host, port);
	}
}
