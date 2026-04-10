import java.io.*;
import java.util.*;

class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(bf.readLine());
        
        int[] arr = new int[T];
        int max = 0;

        for (int i=0;i<T;i++){
            arr[i] = Integer.parseInt(bf.readLine());
            max = Math.max(arr[i], max);
        }
        
        StringBuilder sb = new StringBuilder();
        
        int[] dp = new int[max + 1];
        dp[0] = 1;
        
        for (int i = 1; i <= max; i++){
            dp[i] += dp[i-1];
        }
        
        for (int i = 2; i <= max; i++){
            dp[i] += dp[i-2];
        }
        
        for(int i=3; i<=max;i++){
            dp[i] += dp[i-3];
        }
        
        for(int i=0;i<arr.length;i++){
            sb.append(dp[arr[i]] + "\n");    
        }
        
        
        System.out.println(sb);
    } 
}