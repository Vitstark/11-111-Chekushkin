package ru.itis.http;

import ru.itis.util.NameGeneratorFromImageUrl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlSourcesReplacer {
    private static final String urlPattern = "(https|http)://[A-Za-z\\.]+\\.(ru|net|ua|com)\\S*\\.%s(\\?\\S*^\")?";

    private final Path htmlPath;

    public HtmlSourcesReplacer(String htmlPath) {
        this.htmlPath = Path.of(htmlPath);
    }

    public void sourcesReplace(final String suffix) {
        String html = getHtmlAsString();
        String targetDir = htmlPath.getParent().toString() + '/' + suffix;

        FileLoader fileLoader = new FileLoader(targetDir);
        NameGeneratorFromImageUrl nameGenerator = new NameGeneratorFromImageUrl();

        Pattern pattern = Pattern.compile(String.format(urlPattern, suffix));
        Matcher matcher = pattern.matcher(html);

        String replacedHtml = matcher.replaceAll((matchResult) -> {
            String imageName = nameGenerator.generateImageName(matcher.group());
            System.out.println(matcher.group());
            fileLoader.loadToFile(matcher.group(), imageName);
            return Path.of(suffix, imageName).toString();
        });

        try {
            PrintStream printStream = new PrintStream(htmlPath.toString());
            printStream.print(replacedHtml);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
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
}
