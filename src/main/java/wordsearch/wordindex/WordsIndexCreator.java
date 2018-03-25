package wordsearch.wordindex;

import wordsearch.datareader.TextData;
import wordsearch.WordsNormalizer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Service that creates index of all words for all files data.
 * Words are standardized by passed word normalizer.
 */
public class WordsIndexCreator {

    private final WordsNormalizer wordsNormalizer;
    private final Pattern splitPattern = Pattern.compile("[\\s+,.|(){}\\[\\]<>']");

    public WordsIndexCreator(WordsNormalizer wordsNormalizer) {
        this.wordsNormalizer = wordsNormalizer;
    }

    /**
     * Creates index of all words based on given text files.
     *
     * @param filesData a text data of all files and file name
     * @return a words index that is used for searching word input
     */
    public WordsIndex createIndex(List<TextData> filesData) {

        final Map<String, Map<String, Integer>> index = new HashMap<>();

        for (TextData textData : filesData) {

            for (String line : textData.getLines()) {
                String[] words = line.split(splitPattern.pattern());

                for (String word : words) {
                    if (!word.isEmpty()) {
                        index.merge(wordsNormalizer.normalize(word), createWordCountersMap(textData.getFileName()), this::mergeCounterMaps);
                    }
                }
            }
        }
        return new WordsIndex(index);
    }

    private Map<String, Integer> createWordCountersMap(String file) {
        HashMap<String, Integer> singleWordIndex = new HashMap<>();
        singleWordIndex.put(file, 1);
        return singleWordIndex;
    }

    private <T> Map<T, Integer> mergeCounterMaps(Map<T, Integer> map1, Map<T, Integer> map2) {
        HashMap<T, Integer> mergedMap = new HashMap<>(map1);
        map2.forEach((t, counter) -> mergedMap.merge(t, counter, Integer::sum));
        return mergedMap;
    }
}
