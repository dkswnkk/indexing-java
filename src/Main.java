import domain.index.Index;
import domain.input.Input;
import domain.search.Search;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static domain.search.Search.getIndexTxt;
import static domain.search.Search.isExistsIndexTxt;

public class Main {
    public static void main(String[] args) throws Exception {
        Input input = new Input();
        String directory = input.getPath();

        while (true) {
            switch (input.setCommand()) {
                case "1": {
                    Index index = new Index(directory);
                    index.indexing(index.createFile());
                    break;
                }
                case "2": {
                    if (!isExistsIndexTxt(getIndexTxt(directory))) {
                        System.out.println("인덱싱 먼저 수행주세요!!!");
                        break;
                    }
                    System.out.print("검색어를 입력하세요: ");
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    Search search = new Search(directory, br.readLine());
                    search.searchQuery();
                    search.loadFileData();
                    search.print();
                    break;
                }
                default: {
                    System.exit(1);
                    break;
                }
            }
        }
    }
}
