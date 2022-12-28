package ru.itis.protocol;

import java.io.Serializable;

import ru.itis.protocol.config.Type;

/**
 * @author Vitaly Chekushkin
 */
public class Message<T extends Serializable> {
	public static <T extends Serializable> Message<T> newMessage(Type type, T value) {
		return new Message<>(value, type);
	}

	private Message(T value, Type type) {
		this.value = value;
		this.type = type;
	}
	private final T value;
	private final Type type;

	public T getValue() {
		return value;
	}

	public Type getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Message{" +
			   "value=" + value +
			   ", type=" + type +
			   '}';
	}
}