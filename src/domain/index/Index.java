package domain.index;

import java.io.*;
import java.util.*;

public class Index {

    private final String path;
    private static final String FILE_NAME = "index.txt";

    public Index(String path) {
        this.path = path;
    }

    public void indexing(File file) throws IOException {
        String[] indexingFiles = getIndexingFiles();
        Map<String, Map<String, Integer>> record = new HashMap<>();
        Map<String, Integer> dataCount = new HashMap<>();

        for (String indexingFile : indexingFiles) {
            BufferedReader data = new BufferedReader(new FileReader(path + '/' + indexingFile));
            String content;
            List<String> contents = new ArrayList<>();
            while ((content = data.readLine()) != null) {
                record.computeIfAbsent(content, k -> new HashMap<>());
                contents.add(content);
            }
            for (String _content : contents) {
                record.get(_content).put(indexingFile, record.get(_content).getOrDefault(indexingFile, 0) + 1);
                dataCount.put(_content, dataCount.getOrDefault(_content, 0) + 1);
            }
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (String key : record.keySet()) {
            LinkedList<Map.Entry<String, Integer>> entryList = new LinkedList<>(record.get(key).entrySet());
            entryList.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

            writer.write(key + ' ' + dataCount.get(key) + ' ');
            for (Map.Entry<String, Integer> stringIntegerEntry : entryList) {
                writer.write(stringIntegerEntry.getKey() + ' ' + stringIntegerEntry.getValue() + ' ');
                writer.flush();
            }
            writer.newLine();
        }
    }

    public File createFile() {
        return new File(getParentDirectoryPath() + '/' + FILE_NAME);
    }

    private String[] getIndexingFiles() {
        FilenameFilter filter = (file, name) -> name.contains(".txt");
        return new File(path).list(filter);
    }

    private String getParentDirectoryPath() {
        int index = path.lastIndexOf("/");
        return path.substring(0, index);
    }


}
