import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 100001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수빈
        int K = Integer.parseInt(st.nextToken()); // 동생

        int[] dist = new int[MAX];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Deque<Integer> dq = new ArrayDeque<>();

        dq.add(N);
        dist[N] = 0;

        while (!dq.isEmpty()) {
            int cur = dq.poll();

            if (cur == K) {
                System.out.println(dist[cur]);
                return;
            }

            // 1. 순간이동 (비용 0)
            if (cur * 2 < MAX && dist[cur * 2] > dist[cur]) {
                dist[cur * 2] = dist[cur];
                dq.addFirst(cur * 2);
            }

            // 2. -1 (비용 1)
            if (cur - 1 >= 0 && dist[cur - 1] > dist[cur] + 1) {
                dist[cur - 1] = dist[cur] + 1;
                dq.addLast(cur - 1);
            }

            // 3. +1 (비용 1)
            if (cur + 1 < MAX && dist[cur + 1] > dist[cur] + 1) {
                dist[cur + 1] = dist[cur] + 1;
                dq.addLast(cur + 1);
            }
        }
    }
}