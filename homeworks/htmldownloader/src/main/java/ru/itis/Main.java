package ru.itis;

import ru.itis.http.HtmlDownloader;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Main {

    private static final String charset = java.nio.charset.StandardCharsets.UTF_8.name();
    private static final String url = "https://hh.ru";

    public static void main(String[] args) {
        HtmlDownloader downloader = new HtmlDownloader("jpg", "png", "svg");
        downloader.loadPage(url, "html", "a.html");
    }

    private static String encodeTextQuery(String query, String param) {
        try {
            return String.format(query, URLEncoder.encode(param, charset));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
