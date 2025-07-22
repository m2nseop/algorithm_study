import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];    // 이동값
        boolean[] visited = new boolean[N];  // 터진 풍선 체크

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        int idx = 0;  // 현재 풍선 위치
        int count = 0;

        while (count < N) {
            sb.append(idx + 1).append(" ");  // 1-based 출력
            visited[idx] = true;
            count++;

            if (count == N) break;

            int move = arr[idx];
            if (move > 0) {
                // 오른쪽 이동
                int step = 0;
                while (step < move) {
                    idx = (idx + 1) % N;
                    if (!visited[idx]) step++;
                }
            } else {
                // 왼쪽 이동
                int step = 0;
                while (step < Math.abs(move)) {
                    idx = (idx - 1 + N) % N;
                    if (!visited[idx]) step++;
                }
            }
        }

        System.out.println(sb);
    }
}
