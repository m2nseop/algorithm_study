import java.util.*;
import java.io.*;

public class Main{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str, " ");
        
        int N = Integer.parseInt(st.nextToken()); 
        // N + 1 = 퇴사일 -> 실제로 일 할 수 있는 마지막 날은 N
        
        int[] T = new int[N+2]; // 상담 시간
        // i일에 상담 시작 → T[i]일 동안 진행 → i ~ i+T[i]-1을 차지
        int[] P = new int[N+2]; // 상담 금액
        
        for (int i=1; i<=N;i++ ){
            str = br.readLine();
            st = new StringTokenizer(str, " ");       
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        
        // "i일에 상담을 할지/말지 선택하고, 겹치지 않게 여러 번 골라
        // N일까지 총 보수 최대로 만들기”
        
        // 그럼 작은 문제는 N-1일 부터 N일까지 총 보수 최대로 만들기
        // 그다음은 N-2일 부터 N일까지 총 보수 최대로 만들기
        // ...
        // 1일부터 N일까지 총 보수 최대로 만들기
        
        // 근데 
        
        
        int[] dp = new int[N+2]; // i일부터 N일까지 받을 수 있는 최대의 보수
        
        
        for (int i=N; i>=1 ; i--){
            // 상담 가능 여부 확인
            if (i + T[i] <= N + 1 ) { // 상담이 가능하다.
                dp[i] = Math.max(dp[i+1], P[i] + dp[i + T[i]]);
                // 다음날부터 종료일까지 총 보수와
                // 오늘 보수 + 오늘시작한 상담이 끝난 날의 그다음날 시작한 상담의 보수
                // 비교하여 저장한다.
            } else { // 상담이 불가능하다.
                dp[i] = dp[i + 1]; // 상담 불가 → 다음 날과 같음
            }
        }
        
        
        System.out.println(dp[1]);
        
    }

    

}