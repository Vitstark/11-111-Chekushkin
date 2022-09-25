package ru.itis.http;

import java.nio.file.Path;
import java.util.List;

public class HtmlDownloader {
    private final List<String> fileExtensions;

    public HtmlDownloader(String... fileExtensions) {
        this.fileExtensions = List.of(fileExtensions);
    }

    public void loadPage(final String url,
                         final String targetDir,
                         final String fileName) {

        FileLoader fileLoader = new FileLoader(targetDir, fileName);
        fileLoader.loadToFile(url);

        HtmlSourcesReplacer sourcesReplacer = new HtmlSourcesReplacer(
                Path.of(targetDir, fileName).toString());

        fileExtensions.forEach(x -> sourcesReplacer.replaceSources(x));
    }
}
