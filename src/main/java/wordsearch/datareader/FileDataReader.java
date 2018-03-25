package wordsearch.datareader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * The service responsible for reading all lines from file or files.
 */
public class FileDataReader {

    /**
     * Parses lines of text from given file/files.
     * Returns list containing file names and related text data.
     *
     * @param files the one File or more to read from.
     * @return a list containing abstractions of parsed file name and text lines.
     * @throws IOException if problem with files reading occurs
     */
    public List<TextData> readFiles(File... files) throws IOException {

        List<TextData> filesData = new ArrayList<>();
        for (File file : files) {
            List<String> lines = Files.readAllLines(file.toPath());
            filesData.add(new TextData(file.getName(), lines));
        }
        return filesData;
    }
}
