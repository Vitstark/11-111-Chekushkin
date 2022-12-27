package ru.itis.protocol;

import java.io.Serializable;

/**
 * @author Vitaly Chekushkin
 */
public class Message<T extends Serializable> {
	Message(T value, Type type) {
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