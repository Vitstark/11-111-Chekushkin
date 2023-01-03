package ru.itis.protocol;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Arrays;

import org.apache.commons.lang3.SerializationUtils;
import ru.itis.protocol.config.Type;
import ru.itis.protocol.exceptions.ProtocolException;
import ru.itis.protocol.utils.MessageUtils;

import static ru.itis.protocol.config.ProtocolConfiguration.*;

/**
 * @author Vitaly Chekushkin
 */
public class MessageManager {
	public static <T extends Serializable> Message<T> readMessage(InputStream is)
		throws IOException {
		ByteBuffer byteBuffer = ByteBuffer.wrap(is.readNBytes(VERSION_LENGTH));
		byte[] messageVersion = byteBuffer.array();
		if (!Arrays.equals(VERSION, messageVersion)) {
			throw new ProtocolException("Message version should be " +
				MessageUtils.versionToString(VERSION) + ", your version: " +
				MessageUtils.versionToString(messageVersion));
		}

		Type type;
		try {
			byteBuffer = ByteBuffer.wrap(is.readNBytes(TYPE_LENGTH));
			type = Type.values()[byteBuffer.get()];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new ProtocolException("Unsupported version type", e);
		}

		byteBuffer = ByteBuffer.wrap(is.readNBytes(MESSAGE_INFO_LENGTH));
		int length = byteBuffer.getInt();

		if (length > MAX_MESSAGE_LENGTH) {
			throw new ProtocolException("Too long message: message length should be less than "
				+ MAX_MESSAGE_LENGTH);
		}

		byteBuffer = ByteBuffer.wrap(is.readNBytes(length));
		T value;

		try {
			value = SerializationUtils.deserialize(new ByteArrayInputStream(byteBuffer.array()));
		} catch (Exception e) {
			throw new ProtocolException("Cant deserialize value", e);
		}

		return Message.newMessage(type, value);
	}

	public static <T extends Serializable> byte[] convertMessage(Message<T> message)
		throws IOException {
		byte type = (byte) message.getType().ordinal();
		byte[] value = SerializationUtils.serialize(message.getValue());
		int valueLength = value.length;

		ByteBuffer byteBuffer = ByteBuffer.allocate(VERSION_LENGTH + TYPE_LENGTH
			+ MESSAGE_INFO_LENGTH + valueLength);

		byteBuffer.put(VERSION);
		byteBuffer.put(type);
		byteBuffer.put(ByteBuffer.allocate(MESSAGE_INFO_LENGTH).putInt(value.length).array());
		byteBuffer.put(value);

		return byteBuffer.array();
	}
}
