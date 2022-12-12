package com.example.app.util;

import java.util.Random;

/**
 * @author Vitaly Chekushkin
 */
public class NameGeneratorFromImageName {
	private static int leftLimit = 97; // letter 'a'
	private static int rightLimit = 122; // letter 'z'
	private static int targetStringLength = 20;

	public String generateImageName(String imageName) {
		final String[] split = imageName.split("\\.");
		final String suffix = split[split.length - 1];
		final String name = generateString() + "." + suffix;
		return name;
	}

	private String generateString() {
		Random random = new Random();

		String generatedString = random
			.ints(leftLimit, rightLimit + 1)
			.limit(targetStringLength)
			.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
			.toString();

		return generatedString;
	}
}
