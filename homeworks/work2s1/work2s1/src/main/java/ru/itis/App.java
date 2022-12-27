package ru.itis;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.time.LocalDateTime;
import ru.itis.protocol.Message;
import ru.itis.protocol.MessageFabric;
import ru.itis.protocol.Type;
import ru.itis.server.configuration.ServerConfiguration;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost().getHostAddress(), ServerConfiguration.PORT);
        Message<LocalDateTime> message = MessageFabric.createMessage(Type.CLIENT, LocalDateTime.now());
        System.out.println(message);

        MessageFabric.sendMessage(message, socket.getOutputStream());
        System.out.println();
        System.out.println(MessageFabric.readMessage(socket.getInputStream()));
    }
}
