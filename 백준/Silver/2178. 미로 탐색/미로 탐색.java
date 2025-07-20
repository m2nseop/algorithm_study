import java.util.*;
import java.io.*;

public class Main {
        static int N, M;
        static int[][] miro;
        static boolean[][] visited;
        static int[] dx = {-1, 1, 0, 0};
        static int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        miro = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String rl = br.readLine();
            for (int j = 0; j < M; j++) {
                miro[i][j] = rl.charAt(j) - '0';
            }
        }

        bfs(0, 0);

        System.out.println(miro[N - 1][M - 1]);
    }

    public static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] n = queue.poll();
            int nX = n[0];
            int nY = n[1];

            for (int i = 0; i < 4; i++) {
                int nextX = nX + dx[i];
                int nextY = nY + dy[i];

                if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < M) {
                    if (miro[nextX][nextY] == 1 && !visited[nextX][nextY]) {
                        queue.offer(new int[]{nextX, nextY});
                        visited[nextX][nextY] = true;
                        miro[nextX][nextY] = miro[nX][nY] + 1;
                    }
                }
            }
        }
    }
}
