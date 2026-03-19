import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;

        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};

        Queue<int[]> q = new LinkedList<>();
        
        q.offer(new int[]{0, 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int row = cur[0];
            int col = cur[1];

            for (int i = 0; i < 4; i++) {
                int nextRow = row + dRow[i];
                int nextCol = col + dCol[i];

                if (nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= m){
                    continue;  
                } 
                if (maps[nextRow][nextCol] == 0){
                    continue;
                }
                if (maps[nextRow][nextCol] > 1) continue;

                maps[nextRow][nextCol] = maps[row][col] + 1;
                q.offer(new int[]{nextRow, nextCol});
            }
        }

        return maps[n - 1][m - 1] == 1 ? -1 : maps[n - 1][m - 1];
    }
}