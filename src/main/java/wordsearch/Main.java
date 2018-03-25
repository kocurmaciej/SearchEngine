package wordsearch;

import wordsearch.datareader.TextData;
import wordsearch.datareader.FileDataReader;
import wordsearch.ranking.Ranking;
import wordsearch.ranking.RankingCreator;
import wordsearch.wordindex.WordsIndex;
import wordsearch.wordindex.WordsIndexCreator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            throw new IllegalArgumentException("No directory given to index");
        }

        File indexableDirectory = new File(args[0]);

        File[] files = indexableDirectory.listFiles();
        if (files == null) {
            System.out.println("no a valid directory " + indexableDirectory.getPath());
            return;
        }
        files = Stream.of(files).filter(File::isFile).toArray(File[]::new);
        System.out.println(files.length + " files read in directory " + indexableDirectory.getPath());

        WordsNormalizer normalizer = String::toLowerCase;

        FileDataReader fileDataReader = new FileDataReader();
        List<TextData> filesData = fileDataReader.readFiles(files);

        WordsIndexCreator fileIndexService = new WordsIndexCreator(normalizer);
        WordsIndex wordIndex = fileIndexService.createIndex(filesData);

        InputReader reader = new InputReader(new BufferedReader(new InputStreamReader(System.in)), normalizer);

        RankingCreator rankingCreator = new RankingCreator(wordIndex);

        while (true) {
            System.out.println("search> ");

            List<String> words = reader.readWords();

            if (!words.isEmpty() && words.get(0).equals(":quit")) {
                break;
            }

            List<Ranking> rankings = rankingCreator.createRanking(words);

            if (rankings.isEmpty()) {
                System.out.println("no matches found");
            }

            rankings
                    .stream()
                    .limit(10)
                    .forEach((x) -> System.out.println(x.getFileName() + " : " + x.getRanking() * 100 + "%"));
        }

    }
}
