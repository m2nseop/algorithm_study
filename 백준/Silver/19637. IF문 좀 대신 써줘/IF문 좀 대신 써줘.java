import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // N: 칭호 개수, M: 캐릭터 수
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] title = new String[N];
        int[] power = new int[N];

        // 칭호 정보 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            title[i] = st.nextToken();
            power[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();

        // 캐릭터 전투력 입력
        for (int i = 0; i < M; i++) {

            int value = Integer.parseInt(br.readLine());

            int left = 0;
            int right = N - 1;

            while (left <= right) {
                int mid = (left + right) / 2;
                if (power[mid] >= value) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            sb.append(title[left]).append("\n");
        }

        System.out.print(sb);
    }
}