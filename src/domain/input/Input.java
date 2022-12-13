package domain.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Input {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public String getPath() throws IOException {
        System.out.println("[주의] .txt 문서의 데이터가 담긴 폴더를 입력하세요. ex) C:\\java_special\\input");
        System.out.print("폴더 입력: ");
        return br.readLine();
    }

    public String setCommand() throws Exception {
        System.out.print("1:인덱싱단계 2:검색어입력 3:종료 중 명령어를 입력하세요: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String commend = br.readLine();
        isValidCommand(commend);
        return commend;
    }

    private void isValidCommand(String command) throws Exception {
        if (!command.equals("1") && !command.equals("2") && !command.equals("3")) {
            throw new Exception("존재하지 않는 명령어입니다.");
        }
    }
}
