package wordsearch.ranking;

import org.junit.Assert;
import org.junit.Test;
import wordsearch.wordindex.WordsIndex;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RankingCreatorTest {

    @Test
    public void createRanking() throws Exception {

        // given
        Map<String, Map<String, Integer>> index = new HashMap<>();
        Map<String, Integer> bird = new HashMap<>();
        bird.put("file1", 1);
        bird.put("file2", 2);
        bird.put("file4", 1);

        Map<String, Integer> cat = new HashMap<>();
        cat.put("file1", 3);
        cat.put("file3", 1);

        Map<String, Integer> dog = new HashMap<>();
        dog.put("file1", 2);
        dog.put("file2", 5);
        dog.put("file3", 1);

        index.put("bird", bird);
        index.put("cat", cat);
        index.put("dog", dog);

        RankingCreator rankingCreator = new RankingCreator(new WordsIndex(index));

        // when
        List<Ranking> actualRanking = rankingCreator.createRanking(Arrays.asList("bird", "dog", "cat", "lion"));

        // then
        Assert.assertEquals(4, actualRanking.size());
        Assert.assertEquals("file1", actualRanking.get(0).getFileName());
        Assert.assertEquals(0.75, actualRanking.get(0).getRanking(), 0.0);
        Assert.assertEquals("file2", actualRanking.get(1).getFileName());
        Assert.assertEquals(0.5, actualRanking.get(1).getRanking(), 0.0);
        Assert.assertEquals("file3", actualRanking.get(2).getFileName());
        Assert.assertEquals(0.5, actualRanking.get(2).getRanking(), 0.0);
        Assert.assertEquals("file4", actualRanking.get(3).getFileName());
        Assert.assertEquals(0.25, actualRanking.get(3).getRanking(), 0.0);
    }

}
