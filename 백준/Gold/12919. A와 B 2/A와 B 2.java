import java.io.*;
import java.util.*;

public class Main {
    static String S, T;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();

        dfs(T);

        System.out.println(answer);
    }

    static void dfs(String current) {
        // 이미 성공했으면 종료 (가지치기)
        if (answer == 1) return;

        // 길이가 S보다 작으면 더 못 줄임
        if (current.length() < S.length()) return;

        // 같으면 성공
        if (current.equals(S)) {
            answer = 1;
            return;
        }

        // 1. 끝이 A면 제거
        if (current.charAt(current.length() - 1) == 'A') {
            dfs(current.substring(0, current.length() - 1));
        }

        // 2. 시작이 B면 제거 후 뒤집기
        if (current.charAt(0) == 'B') {
            StringBuilder sb = new StringBuilder(current.substring(1));
            dfs(sb.reverse().toString());
        }
    }
}