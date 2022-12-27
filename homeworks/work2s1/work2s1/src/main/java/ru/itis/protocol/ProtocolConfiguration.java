package ru.itis.protocol;

/**
 * @author Vitaly Chekushkin
 */
class ProtocolConfiguration {
	public static final int MAX_LENGTH = 2 + 1 + 4 + 4 * 1024;
	// 2 bytes for version, 1 byte for type, 4 for bytes for length, 4096 for message
	public static final byte[] VERSION = new byte[]{0x0, 0x1};
}