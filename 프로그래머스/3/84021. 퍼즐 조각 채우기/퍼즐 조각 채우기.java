import java.util.*;

class Solution {

    int N;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    public int solution(int[][] game_board, int[][] table) {
        N = game_board.length;

        List<List<int[]>> blanks = getBlocks(game_board, 0);
        List<List<int[]>> puzzles = getBlocks(table, 1);

        boolean[] used = new boolean[puzzles.size()];
        int answer = 0;

        for (List<int[]> blank : blanks) {
            for (int i = 0; i < puzzles.size(); i++) {
                if (used[i]) continue;

                List<int[]> puzzle = puzzles.get(i);

                if (blank.size() != puzzle.size()) continue;

                if (match(blank, puzzle)) {
                    used[i] = true;
                    answer += blank.size();
                    break;
                }
            }
        }

        return answer;
    }

    List<List<int[]>> getBlocks(int[][] map, int target) {
        boolean[][] visited = new boolean[N][N];
        List<List<int[]>> blocks = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] == target) {
                    blocks.add(normalize(bfs(i, j, map, visited, target)));
                }
            }
        }

        return blocks;
    }

    List<int[]> bfs(int x, int y, int[][] map, boolean[][] visited, int target) {
        Queue<int[]> q = new LinkedList<>();
        List<int[]> points = new ArrayList<>();

        visited[x][y] = true;
        q.add(new int[]{x, y});
        points.add(new int[]{x, y});

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];

                if (inRange(nx, ny) && !visited[nx][ny] && map[nx][ny] == target) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                    points.add(new int[]{nx, ny});
                }
            }
        }

        return points;
    }

    boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    List<int[]> normalize(List<int[]> points) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        for (int[] p : points) {
            minX = Math.min(minX, p[0]);
            minY = Math.min(minY, p[1]);
        }

        List<int[]> normalized = new ArrayList<>();

        for (int[] p : points) {
            normalized.add(new int[]{p[0] - minX, p[1] - minY});
        }

        normalized.sort((a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });

        return normalized;
    }

    List<int[]> rotate(List<int[]> points) {
        List<int[]> rotated = new ArrayList<>();

        for (int[] p : points) {
            rotated.add(new int[]{p[1], -p[0]});
        }

        return normalize(rotated);
    }

    boolean match(List<int[]> blank, List<int[]> puzzle) {

        for (int r = 0; r < 4; r++) {
            if (isSame(blank, puzzle)) return true;
            puzzle = rotate(puzzle);
        }

        return false;
    }

    boolean isSame(List<int[]> a, List<int[]> b) {
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i)[0] != b.get(i)[0] || a.get(i)[1] != b.get(i)[1]) {
                return false;
            }
        }
        return true;
    }
}