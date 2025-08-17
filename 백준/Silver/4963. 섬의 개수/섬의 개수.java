import java.io.*;
import java.util.*;

public class Main {
    
    static int w;
    static int h;
    static int[][] map;
    static boolean[][] visited;
    
    // 8방향
    static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1}; 
    static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1}; 
    

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str, " ");
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if ( w == 0 && h == 0){
                break;
            }
            
            map = new int[h][w];
            for (int i = 0 ; i < h ; i++) {
                str = br.readLine();
                st = new StringTokenizer(str, " ");
                for ( int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }  
            
            visited = new boolean[h][w];
            int islands = 0;
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    if (map[y][x] == 1 && !visited[y][x]) {
                        bfs(y, x);
                        islands++;
                    }
                }
            }
            System.out.println(islands);
        }
    }
    
    static void bfs(int sy, int sx) {
        ArrayDeque<int[]> q = new ArrayDeque<>(); // 제일 효율적인 Queue의 구현체
        visited[sy][sx] = true;
        q.offer(new int[]{sy, sx});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0];
            int x = cur[1];
            for (int i = 0; i < 8; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny < 0 || ny >= h || nx < 0 || nx >= w){
                    continue;  
                } 
                if (map[ny][nx] == 1 && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    q.offer(new int[]{ny, nx});
                }
            }
        }
    }
}