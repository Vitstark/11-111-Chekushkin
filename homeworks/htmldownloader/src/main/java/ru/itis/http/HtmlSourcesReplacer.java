package ru.itis.http;

import ru.itis.util.NameGeneratorFromImageUrl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlSourcesReplacer {
    private static final String urlPattern = "(https|http)://[A-Za-z\\.]+\\.(ru|net|ua|com)\\S*\\.%s(\\?(^\")*)?";

    private final Path htmlPath;

    public HtmlSourcesReplacer(String htmlPath) {
        this.htmlPath = Path.of(htmlPath);
    }

    public void replaceSources(final String suffix) {
        String html = getHtmlAsString();
        String changedHtml = downloadFilesAndReplaceSources(html, suffix);
        rewriteHtml(changedHtml);
    }

    private String downloadFilesAndReplaceSources(String html, String suffix) {
        String targetDir = htmlPath.getParent().resolve(suffix).toString();

        Pattern pattern = Pattern.compile(String.format(urlPattern, suffix));
        Matcher matcher = pattern.matcher(html);
        ExecutorService executorService = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors());

        String changedHtml = matcher.replaceAll((matchResult) -> {
            String url = matchResult.group();
            System.out.println(url);
            // url = url.substring(1, url.length() - 1);
            String imageName = new NameGeneratorFromImageUrl().generateImageName(url);
            FileLoader fileLoader = new FileLoader(targetDir, imageName);
            executorService.submit(() -> fileLoader.loadToFile(url));
            return Path.of(suffix, imageName).toString();
        });

        executorService.shutdown();

        return changedHtml;
    }

    private String getHtmlAsString() {
        String htmlAsString = null;
        try {
            htmlAsString = Files.readString(htmlPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return htmlAsString;
    }

    private void rewriteHtml(String newHtml) {
        try {
            PrintStream printStream = new PrintStream(htmlPath.toString());
            printStream.print(newHtml);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
