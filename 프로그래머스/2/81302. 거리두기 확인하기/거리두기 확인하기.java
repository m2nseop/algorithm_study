import java.util.*;

class Solution {
    static final int N = 5; // 대기실 개수 및 각 대기실 크기
    static final int[] dr = {-1, 1, 0, 0}; // 상하좌우
    static final int[] dc = {0, 0, -1, 1}; // 상하좌우

    public int[] solution(String[][] places) {
        int[] ans = new int[places.length];

        for (int p = 0; p < places.length; p++) {
            char[][] g = new char[N][N];
            for (int r = 0; r < N; r++) {
                g[r] = places[p][r].toCharArray();
            }
            ans[p] = check(g) ? 1 : 0; // 1: 거리두기 지킴, 0: 위반
        }
        return ans;
    }

    private boolean check(char[][] g) {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (g[r][c] == 'P') {
                    if (!bfs(g, r, c)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // 시작점 (sr, sc)에서 맨해튼 거리 <= 2 이내에 도달 가능한 'P'가 있으면 false
    private boolean bfs(char[][] g, int sr, int sc) {
        boolean[][] visited = new boolean[N][N];
        Deque<int[]> q = new ArrayDeque<>();
        visited[sr][sc] = true;
        q.offer(new int[]{sr, sc, 0}); // r, c, dist

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int d = cur[2];

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                int nd = d + 1;

                if (!isInbound(nr, nc)) {
                    continue;
                }
                if (visited[nr][nc]) {
                    continue;
                }
                if (nd > 2) {
                    continue;        // “두 칸 이내”만 본다
                }
                if (g[nr][nc] == 'X'){
                    continue; // 파티션은 진행 불가    
                } 
                // nd == 1 or 2에서 다른 'P'를 만나면 위반
                if (g[nr][nc] == 'P') {
                    return false;
                }
                // 빈 테이블 'O'면 계속 확장
                visited[nr][nc] = true;
                if (g[nr][nc] == 'O') {
                    q.offer(new int[]{nr, nc, nd});
                }
            }
        }
        return true;
    }

    private boolean isInbound(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}