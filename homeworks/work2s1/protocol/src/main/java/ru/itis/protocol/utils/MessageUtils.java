package ru.itis.protocol.utils;

/**
 * @author Vitaly Chekushkin
 */
public class MessageUtils {
	public static final String versionToString(byte[] version) {
		StringBuilder versionBuilder = new StringBuilder();
		versionBuilder.append('"')
				.append(version[0]);
		for (int i = 1; i < version.length; i++) {
			versionBuilder.append('.')
					.append(version[i]);
		}
		return versionBuilder.append('"')
			.toString();
	}
}