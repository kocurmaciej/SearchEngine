package wordsearch.wordindex;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * Representation of all words found in all files.
 * Index of every word and relevant file name and number of occurrences in that file.
 */
public class WordsIndex {

    private final Map<String, Map<String, Integer>> index;

    public WordsIndex(Map<String, Map<String, Integer>> index) {
        this.index = index;
    }

    /**
     * Returns a set of file names that have searched word inside.
     *
     * @param word single word from to find in index.
     * @return a set of file names containing given word.
     */
    public Set<String> getFileNamesContainingWord(String word) {
        if (index.containsKey(word)) {
            return index.get(word).keySet();
        }
        return Collections.emptySet();
    }

    /**
     * Returns a map of file names an occurrences counter for a given word.
     *
     * @param word a searched word
     * @return a map of key:fileName value:wordCounter
     */
    public Map<String, Integer> getWordCounterMappedByFile(String word) {
        if (index.containsKey(word)) {
            return index.get(word);
        }
        return Collections.emptyMap();
    }
}
