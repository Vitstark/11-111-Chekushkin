package ru.itis.protocol.config;

/**
 * @author Vitaly Chekushkin
 */
public class ProtocolConfiguration {
	public static final byte[] VERSION = new byte[]{0x0, 0x2};
	public static final int VERSION_LENGTH = VERSION.length;
	public static final int TYPE_LENGTH = 1;
	public static final int MESSAGE_INFO_LENGTH = 4;
	public static final int MAX_MESSAGE_LENGTH = 4 * 1024;
}