package ru.itis.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Scanner;

import ru.itis.protocol.Message;
import ru.itis.protocol.MessageManager;
/**
 * @author Vitaly Chekushkin
 */
public class ServerRun {
	public static void main(String[] args) throws IOException, InterruptedException {
		Server server = new Server();
		server.start();

		Scanner scanner = new Scanner(System.in);
		scanner.next();
	}
}
