package wordsearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Service responsible for reading particular words from user input.
 */
public class InputReader {

    private final BufferedReader reader;

    private final WordsNormalizer wordsNormalizer;

    public InputReader(BufferedReader reader, WordsNormalizer wordsNormalizer) {
        this.reader = reader;
        this.wordsNormalizer = wordsNormalizer;
    }

    /**
     * Returns list of particular words parsed from user input.
     *
     * @return list of single words
     * @throws IOException when issues with user input read occurs.
     */
    public List<String> readWords() throws IOException {
        return Stream.of(reader.readLine().split(" "))
                .map(wordsNormalizer::normalize)
                .collect(Collectors.toList());
    }
}
