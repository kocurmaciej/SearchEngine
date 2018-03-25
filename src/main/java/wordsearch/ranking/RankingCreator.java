package wordsearch.ranking;

import wordsearch.wordindex.WordsIndex;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Creates ranking for particular files based on word occurrences in text.
 * Searches for given words through word index.
 * Returns a Ranking for all files.
 * If finds all words in file, it ranks file with 1.
 * If none, ranks it with 0.
 * All between are ranked linearly based on number of matches.
 * Rank: numberOfMatched/numberOfAllSearchedWords
 */
public class RankingCreator {

    private final WordsIndex index;

    public RankingCreator(WordsIndex index) {
        this.index = index;
    }

    /**
     * Creates ranking for searched words.
     * Returns a list of sorted files ranking.
     *
     * @param words a list searched words
     * @return a list of matched ranking score for a particular file.
     */
    public List<Ranking> createRanking(List<String> words) {

        if (words.isEmpty()) {
            return Collections.emptyList();
        }

        Map<String, Integer> ranking = new HashMap<>();

        for (String word : words) {
            index.getFileNamesContainingWord(word)
                    .forEach(filename -> ranking.merge(filename, 1, Integer::sum));
        }

        return ranking.entrySet()
                .stream()
                .map(e -> new Ranking(e.getKey(), 1.0 * e.getValue() / words.size()))
                .sorted()
                .collect(Collectors.toList());
    }
}
