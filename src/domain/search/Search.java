package domain.search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Search {

    private List<Integer> count = new ArrayList<>();
    private List<String> loadData = new ArrayList<>();
    private List<String> files = new ArrayList<>();
    private final String path;
    private final String searchQuery;

    public Search(String path, String searchQuery) {
        this.path = path;
        this.searchQuery = searchQuery;
    }

    public static Boolean isExistsIndexTxt(String path) {
        File file = new File(path);
        return file.exists();
    }

    public void searchQuery() throws IOException {
        BufferedReader data = new BufferedReader(new FileReader(getIndexTxt(path)));
        String content;
        while ((content = data.readLine()) != null) {
            if (content.contains(searchQuery)) {
                String[] result = content.split(" ");
                for (int i = 2; i < result.length; i += 2) {
                    files.add(result[i]);
                    count.add(Integer.parseInt(result[i + 1]));
                }
            }
        }
    }

    public void loadFileData() throws IOException {
        for (String file : files) {
            BufferedReader data = new BufferedReader(new FileReader(path + '/' + file));
            loadData.add(data.readLine());
        }
    }

    public void print() {
        System.out.println('\n' + "------------------------");
        System.out.println(searchQuery + " 검색 결과" + files.size() + "건이 검색되었습니다.");
        for (int i = 0; i < files.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + files.get(i) + ' ' + count.get(i) + "회");
            System.out.println("\"" + loadData.get(i) + "..........." + "\"" + '\n');
        }
        System.out.println("검색이 완료되었습니다.");
        System.out.println("------------------------" + '\n');
    }

    public static String getIndexTxt(String path) {
        int index = path.lastIndexOf("/");
        path = path.substring(0, index);
        return path + '/' + "index.txt";
    }


}
