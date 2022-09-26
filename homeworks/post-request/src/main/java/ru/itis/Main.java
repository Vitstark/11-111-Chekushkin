package ru.itis;

import ru.itis.http.FileLoader;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Main {

    private static final String charset = java.nio.charset.StandardCharsets.UTF_8.name();
    private static final String url = "https://reqres.in/api/users";

    public static void main(String[] args) {
        FileLoader fileLoader = new FileLoader("post", "response.json");
        fileLoader.postToFile(url);
    }

    private static String encodeTextQuery(String query, String param) {
        try {
            return String.format(query, URLEncoder.encode(param, charset));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
