package wordsearch.wordindex;

import org.junit.Assert;
import org.junit.Test;
import wordsearch.datareader.TextData;

import java.util.*;

public class WordsIndexCreatorTest {

    @Test
    public void createsIndex() throws Exception {

        // given
        List<TextData> givenTextDatas = Arrays.asList(
                new TextData("file1", Collections.singletonList("cat dog lion")),
                new TextData("file2", Arrays.asList("cat cat bird", "BIRD"))
        );

        WordsIndexCreator wordsIndexCreator = new WordsIndexCreator(word -> word.toLowerCase());

        // when
        WordsIndex actualIndex = wordsIndexCreator.createIndex(givenTextDatas);


        // then
        Assert.assertEquals(Integer.valueOf(1), actualIndex.getWordCounterMappedByFile("cat").get("file1"));
        Assert.assertEquals(Integer.valueOf(2), actualIndex.getWordCounterMappedByFile("cat").get("file2"));
        Assert.assertEquals(false, actualIndex.getWordCounterMappedByFile("bird").containsKey("file1"));
        Assert.assertEquals(Integer.valueOf(2), actualIndex.getWordCounterMappedByFile("bird").get("file2"));
        Assert.assertEquals(Integer.valueOf(1), actualIndex.getWordCounterMappedByFile("dog").get("file1"));
        Assert.assertEquals(false, actualIndex.getWordCounterMappedByFile("dog").containsKey("file2"));
        Assert.assertEquals(Integer.valueOf(1), actualIndex.getWordCounterMappedByFile("lion").get("file1"));
        Assert.assertEquals(false, actualIndex.getWordCounterMappedByFile("lion").containsKey("file2"));
    }

    @Test
    public void indexesNonEmptyWord() throws Exception {

        // given
        List<TextData> givenTextDatas = Arrays.asList(
                new TextData("file1", Collections.singletonList("")),
                new TextData("file2", Arrays.asList("cat cat bird", ""))
        );
        WordsIndexCreator wordsIndexCreator = new WordsIndexCreator(word -> word.toLowerCase());

        // when
        WordsIndex actualIndex = wordsIndexCreator.createIndex(givenTextDatas);

        // then
        Assert.assertEquals(Collections.emptySet(), actualIndex.getFileNamesContainingWord(""));
        Assert.assertEquals(Collections.emptySet(), actualIndex.getFileNamesContainingWord(""));
    }

    @Test
    public void indexesWordsSeparatedWithPunctuation() throws Exception {

        // given
        List<TextData> givenTextDatas = Arrays.asList(
                new TextData("file1", Collections.singletonList("<cat,dog.lion")),
                new TextData("file2", Arrays.asList("[cat{cat|bird", "(BIRD"))
        );

        WordsIndexCreator wordsIndexCreator = new WordsIndexCreator(word -> word.toLowerCase());

        // when
        WordsIndex actualIndex = wordsIndexCreator.createIndex(givenTextDatas);


        // then
        Assert.assertEquals(Integer.valueOf(1), actualIndex.getWordCounterMappedByFile("cat").get("file1"));
        Assert.assertEquals(Integer.valueOf(2), actualIndex.getWordCounterMappedByFile("cat").get("file2"));
        Assert.assertEquals(false, actualIndex.getWordCounterMappedByFile("bird").containsKey("file1"));
        Assert.assertEquals(Integer.valueOf(2), actualIndex.getWordCounterMappedByFile("bird").get("file2"));
        Assert.assertEquals(Integer.valueOf(1), actualIndex.getWordCounterMappedByFile("dog").get("file1"));
        Assert.assertEquals(false, actualIndex.getWordCounterMappedByFile("dog").containsKey("file2"));
        Assert.assertEquals(Integer.valueOf(1), actualIndex.getWordCounterMappedByFile("lion").get("file1"));
        Assert.assertEquals(false, actualIndex.getWordCounterMappedByFile("lion").containsKey("file2"));
    }

}
