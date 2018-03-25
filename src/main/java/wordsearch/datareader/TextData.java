package wordsearch.datareader;

import java.util.ArrayList;
import java.util.List;


/**
 * Representation of parse files.
 * Stores file name and related text data.
 */
public class TextData {
    private final String fileName;
    private final List<String> lines;

    public TextData(String fileName, List<String> lines) {
        this.fileName = fileName;
        this.lines = lines;
    }

    public String getFileName() {
        return fileName;
    }

    public List<String> getLines() {
        return new ArrayList<>(lines);
    }
}
