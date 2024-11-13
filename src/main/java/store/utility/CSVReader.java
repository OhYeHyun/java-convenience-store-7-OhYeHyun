package store.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    private static final String CSV_DELIMITER = ",";
    private final List<String[]> lists = new ArrayList<>();

    public CSVReader(String filePath) {
        Path path = Paths.get(filePath);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            reader.readLine();
            makeList(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeList(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] lineContents = line.split(CSV_DELIMITER);
            lists.add(lineContents);
        }
    }

    public List<String[]> getLists() {
        return new ArrayList<>(lists);
    }
}
