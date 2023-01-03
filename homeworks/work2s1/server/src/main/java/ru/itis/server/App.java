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
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket(InetAddress.getLocalHost().getHostAddress(), ServerConfiguration.PORT);
        Thread.sleep(2000);
        Message<LocalDateTime> message = Message.newMessage(Type.ALL, LocalDateTime.now());
        System.out.println(message);

        byte[] convertedMessage = MessageManager.convertMessage(message);
        OutputStream os = socket.getOutputStream();
        os.write(convertedMessage);
        os.flush();

        System.out.println("sent message to server");
        System.out.println(MessageManager.readMessage(socket.getInputStream()));

        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);

            message = Message.newMessage(Type.ALL, LocalDateTime.now());
            System.out.println(message);

            convertedMessage = MessageManager.convertMessage(message);
            os = socket.getOutputStream();
            os.write(convertedMessage);
            os.flush();

            System.out.println("sent message to server");
            System.out.println(MessageManager.readMessage(socket.getInputStream()));
        }
    }
}
