import java.util.*;

public class Main {
    static boolean[] v;
    static List<Integer>[] g;
    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        g = new ArrayList[n + 1];
        v = new boolean[n + 1];

        for (int i=1; i<= n; i++ ) {
            g[i] = new ArrayList<>();
        }

        for (int i = 0; i<m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            g[a].add(b);
            g[b].add(a);
        }

        dfs(1);
        System.out.println(count - 1);
    }

    static void dfs(int node) {
        v[node] = true;
        count++;

        for (int next : g[node]) {
            if (!v[next]) {
                dfs(next);
            }
        }
    }
}
