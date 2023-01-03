package ru.itis.protocol.utils;

/**
 * @author Vitaly Chekushkin
 */
public class MessageUtils {
	public static final String versionToString(byte[] version) {
		StringBuilder versionBuilder = new StringBuilder();
		versionBuilder.append('"');

		for (byte byt : version) {
			versionBuilder.append(byt)
				.append('.');
		}

		if (version.length != 0) {
			versionBuilder.deleteCharAt(version.length * 2);
		}

		return versionBuilder.append('"')
			.toString();
	}
}