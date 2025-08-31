import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static char[][] normal; // 일반사람
    static char[][] blind; // 적록색약
    static int[] dr = {-1, 1, 0, 0}; // 상하좌우
    static int[] dc = {0, 0, -1, 1}; // 상하좌우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        normal = new char[N][N];
        blind  = new char[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                char ch = str.charAt(j);
                normal[i][j] = ch;
                // 그냥 넣을때 G => R로 변경
                blind[i][j] = (ch == 'G') ? 'R' : ch;
            }
        }

        boolean[][] visitedN = new boolean[N][N];
        boolean[][] visitedB = new boolean[N][N];
        
        int countN = 0;
        int countB = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visitedN[i][j]) { 
                    bfs(normal, visitedN, i, j); 
                    countN++; 
                }
                if (!visitedB[i][j]) { 
                    bfs(blind,  visitedB, i, j); 
                    countB++; 
                }
            }
        }

        System.out.println(countN + " " + countB);
    }
    
    // map이랑 visited를 인자로 받아서 쓰는게 더 나을 것 같다..
    static void bfs(char[][] map, boolean[][] visited, int sr, int sc) {
        char color = map[sr][sc]; // 색깔 하나 잡고 공략
        Deque<int[]> q = new ArrayDeque<>();
        visited[sr][sc] = true;
        q.offer(new int[]{sr, sc});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if (!isInbound(nr, nc)){
                    continue;
                } 
                if (visited[nr][nc]) {
                    continue;
                }
                if (map[nr][nc] != color) {
                    continue;
                }
                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc});
            }
        }
    }

    public static boolean isInbound(int r, int c){
        if ( c >=0 && c < N && r >= 0 && r < N ){
            return true;
        }
        return false;
    }

}
