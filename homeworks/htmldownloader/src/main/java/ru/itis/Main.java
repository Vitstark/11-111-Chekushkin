package ru.itis;

import ru.itis.util.HttpClientMain;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Main {
    static final String charset = java.nio.charset.StandardCharsets.UTF_8.name();

    public static void main(String[] args) {
        //example get request for
        HttpClientMain httpClient = new HttpClientMain();
        String baseUrl = "https://yandex.ru/search/";
        String query = encodeTextQuery("text=%s", "итис кфу");
        httpClient.loadPageToFile(baseUrl, query, "a.html");

        //example download image
        httpClient.loadImageToRandomFile("https://otvet.imgsmail.ru/download/256116852_cfd74eaf0366a5b037913006001d11f6.png");
    }

    private static String encodeTextQuery(String query, String param) {
        try {
            return "text=" + URLEncoder.encode(param, charset);
//            return String.format(query, URLEncoder.encode(param, charset));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
