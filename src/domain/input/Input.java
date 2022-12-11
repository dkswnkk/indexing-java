package domain.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Input {

    public String getPath() throws IOException {
        System.out.print("폴더 입력: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

    public Integer setCommand() throws Exception {
        System.out.print("1:인덱싱단계 2:검색어입력 3:종료 중 명령어를 입력하세요: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer commend = Integer.parseInt(br.readLine());
        isValidCommand(commend);
        return Integer.parseInt(br.readLine());
    }

    private void isValidCommand(Integer command) throws Exception {
        if (command != 1 && command != 2 && command != 3) {
            throw new Exception("존재하지 않는 명령어입니다.");
        }
    }
}
