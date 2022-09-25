package ru.itis.http;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileLoader {

    private String targetDir;
    private String fileName;

    public FileLoader(String targetDir, String fileName) {
        this.targetDir = targetDir;
        this.fileName = fileName;

        try {
            Files.createDirectories(Path.of(targetDir));
        }catch (FileAlreadyExistsException e) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadToFile(final String url) {
        URLConnection connection = null;
        try {
            //прокинули запрос
            connection = new URL(url).openConnection();
            //возвращается обратно страница (содержимое)
            InputStream response = connection.getInputStream();
            //считываем содержимое в файл
            writeToFile(response);
            response.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    //todo - показать пример с Closeable
    private void writeToFile(InputStream response) throws IOException {
        try (OutputStream outStream = new FileOutputStream(Path.of(targetDir, fileName).toFile())) {
            byte[] buffer = new byte[8 * 1024];
            int bytesRead;
            while ((bytesRead = response.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
        }
    }
}
