package wordsearch;

/**
 * A common interface for all different words normalizers implementation.
 * It used to make standardized version of given word.
 */
public interface WordsNormalizer {

    /**
     * Return word after standardization.
     * i.e. changes all letters to lower case
     *
     * @param word input word
     * @return normalized word
     */
    String normalize(String word);

}
