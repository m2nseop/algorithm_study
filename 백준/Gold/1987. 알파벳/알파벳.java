import java.io.*;
import java.util.*;

public class Main {
    static int R; // 세로
    static int C; // 가로
    static int answer = 1; // 일단 첫번째칸은 방문하니까
    
    static int[] dy = {-1, 1, 0, 0}; // 상하좌우
    static int[] dx = {0, 0, -1, 1}; // 상하좌우
    static char[][] board; // 보드
    static HashSet<Character> checker = new HashSet<>(); // 알파벳 중복확인용
    
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            String str = br.readLine().trim();
            for (int j = 0; j < C; j++) {
                board[i][j] = str.charAt(j);
            }
        }
        
        checker.add(board[0][0]); // 시작 알파벳 넣기
        dfs(0, 0, 1);
        System.out.println(answer);
    }

    static void dfs(int x, int y, int len) {
        answer = Math.max(answer, len); // 최댓값을 뽑아야하니까
        
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
   
            if (nx < 0 || nx >= C || ny < 0 || ny >= R) {
                continue;
            }
            char cur = board[ny][nx];
            if (checker.contains(cur)) {
                continue; // 이미 사용한 알파벳
            }
            checker.add(cur);
            dfs(nx, ny, len + 1);
            checker.remove(cur); // 백트래킹 (상태 복원)
        }
    }
}
