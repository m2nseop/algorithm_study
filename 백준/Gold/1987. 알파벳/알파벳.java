import java.io.*;
import java.util.*;

public class Main {
    // 세로 R칸, 가로 C칸으로 된 표 모양의 보드가 있다.
    static char[][] board; // 보드
    
    static int R; // 세로
    static int C; // 가로
    
    // 말은 상하좌우로 인접한 네 칸 중의 한 칸으로 이동할 수 있다.
    static int[] dy = {-1, 1, 0, 0}; // 상하좌우
    static int[] dx = {0, 0, -1, 1}; // 상하좌우
    
    // 새로 이동한 칸에 적혀 있는 알파벳은 지금까지 지나온 모든 칸에 적혀 있는 알파벳과는 달라야 한다.
    // 즉, 같은 알파벳이 적힌 칸을 두 번 지날 수 없다.
    // static HashSet<Character> checker = new HashSet<>(); // 알파벳 중복확인용
    static boolean[] checker = new boolean[26];
    
    // 말이 지나는 칸은 좌측 상단의 칸도 포함된다.
    static int answer = 1; // 일단 첫번째칸은 방문하니까
    
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
        
        
        // checker.add(board[0][0]); // 시작 알파벳 넣기
        int idx = board[0][0] - 'A';
        checker[idx] = true;
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
            // if (checker.contains(cur)) {
            //     continue; // 이미 사용한 알파벳
            // }
             int idx = cur - 'A';
            
            if (checker[idx]){
                continue; // 이미 사용한 알파벳
            }
            
            
            // checker.add(cur);
            
             checker[idx] = true;
            dfs(nx, ny, len + 1);
            // checker.remove(cur); // 백트래킹 (상태 복원)
             checker[idx] = false;
        }
    }
}
