package wordsearch.ranking;

/**
 * A single ranking for particular file name.
 */
public class Ranking implements Comparable<Ranking> {

    private String fileName;
    private double ranking;

    public Ranking(String fileName, double ranking) {
        this.fileName = fileName;
        this.ranking = ranking;
    }

    @Override
    public int compareTo(Ranking fileRanking) {

        return fileRanking.getRanking() == this.getRanking()
                ? this.getFileName().compareTo(fileRanking.getFileName())
                : fileRanking.getRanking() > this.getRanking() ? 1 : -1;
    }

    public String getFileName() {
        return fileName;
    }

    public double getRanking() {
        return ranking;
    }
}
