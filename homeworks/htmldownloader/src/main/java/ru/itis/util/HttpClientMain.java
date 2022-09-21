package ru.itis.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

public class HttpClientMain {


    public void loadPageToFile(final String url,
                               final String query,
                               final String pageName) {
        URLConnection connection = null;
        try {
            //прокинули запрос
            connection = new URL(url + "?" + query).openConnection();
            //возвращается обратно страница (содержимое)
            InputStream response = connection.getInputStream();
            //считываем содержимое в файл
            writeToFile(response, pageName);
            response.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    //todo - показать пример с Closeable
    private void writeToFile(InputStream response, String fileName) throws IOException {
        final File file = new File(fileName);
        OutputStream outStream = new FileOutputStream(file);
        byte[] buffer = new byte[8 * 1024];
        int bytesRead;
        while ((bytesRead = response.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        //что будет если не вызвать close - домашка
        outStream.close();
    }

    public void loadImageToRandomFile(String imageUrl) {
        URLConnection connection = null;
        try {
            connection = new URL(imageUrl).openConnection();
            InputStream response = connection.getInputStream();
            writeToFile(response, generateImageName(imageUrl));
            response.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private String generateImageName(String imageUrl) {
        final String[] split = imageUrl.split("\\.");
        final String suffix = split[split.length - 1];
        final String name = generateString() + "." + suffix;
        return name;
    }


    public static String generateString()
    {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

}
