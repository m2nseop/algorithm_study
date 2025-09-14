import java.util.*;
import java.io.*;

public class Main{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str, " ");
        
        int n = Integer.parseInt(st.nextToken()); // n -> 카드 개수
        int m = Integer.parseInt(st.nextToken()); // m -> 카드 합체 총 횟수
        
        str = br.readLine();
        st = new StringTokenizer(str, " ");
        
        PriorityQueue<Long> pq = new PriorityQueue<>();
        long sum = 0;

        for (int i = 0; i < n; i++) {
            long x = Long.parseLong(st.nextToken());
            pq.add(x);
            sum += x;
        }

        for (int i = 0; i < m; i++) {
            long a = pq.poll();
            long b = pq.poll();
            long s = a + b;
            sum += s;
            pq.add(s);
            pq.add(s);
        }

        System.out.println(sum);
        
        
        
        
    }

    

}