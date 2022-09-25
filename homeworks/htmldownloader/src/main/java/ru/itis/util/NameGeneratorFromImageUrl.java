package ru.itis.util;

import java.util.Random;

public class NameGeneratorFromImageUrl {
    private static int leftLimit = 97; // letter 'a'
    private static int rightLimit = 122; // letter 'z'
    private static int targetStringLength = 10;

    public String generateImageName(String imageUrl) {
        final String urlWithoutAttributes = imageUrl.split("\\?")[0];
        final String[] split = urlWithoutAttributes.split("\\.");
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
