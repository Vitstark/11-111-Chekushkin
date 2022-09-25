package ru.itis;

import ru.itis.http.FileLoader;
import ru.itis.http.HtmlSourcesReplacer;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Main {

    private static final String charset = java.nio.charset.StandardCharsets.UTF_8.name();
    private static final String baseUrl = "https://stackoverflow.com/questions/73845862/having-trouble-with-comparing-datetimes-when-there-are-db-golang-and-unix-vers";

    public static void main(String[] args) {
        FileLoader fileLoader = new FileLoader("html");
        fileLoader.loadToFile(baseUrl, "a.html");

        HtmlSourcesReplacer sourcesReplacer = new HtmlSourcesReplacer("html/a.html");
        sourcesReplacer.sourcesReplace("png");
        sourcesReplacer.sourcesReplace("jpg");
    }

    private static String encodeTextQuery(String query, String param) {
        try {
            return String.format(query, URLEncoder.encode(param, charset));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
