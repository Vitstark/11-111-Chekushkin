package ru.itis.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.time.LocalDateTime;
import ru.itis.protocol.Message;
import ru.itis.protocol.MessageManager;
import ru.itis.protocol.config.Type;
import ru.itis.server.configuration.ServerConfiguration;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost().getHostAddress(), ServerConfiguration.PORT);
        Message<LocalDateTime> message = Message.newMessage(Type.ALL, LocalDateTime.now());
        System.out.println(message);

        byte[] convertedMessage = MessageManager.convertMessage(message);
        OutputStream os = socket.getOutputStream();
        os.write(convertedMessage);
        os.flush();

        System.out.println("sent message to server");
        System.out.println(MessageManager.readMessage(socket.getInputStream()));
    }
}
