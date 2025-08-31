import java.util.*;
import java.io.*;

public class Main{
    
    // 이제 이런 문제는 암시적 그래프라고 정의하자
    static int[][] map;
    static int N = 0; // 행
    static int M = 0; // 열
    static int T = 0; // T시간 내에 공주님이 있는 곳에 도달해야한다.
    
    static int[] dy = {-1, 1, 0, 0}; // 상,하,좌,우
    static int[] dx = {0, 0, -1, 1}; // 상,하,좌,우
    
    static int[][] distance;
    
    static int shortestTime = 100*100*10; // N x M x 10 ( 실패를 하기 위한 충분히 큰 시간 )
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str, " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
     
        distance = new int[N][M];
        for (int[] row : distance) {
            Arrays.fill(row, -1); // 거리 정보와 방문 여부
        }
        
        for (int r=0; r<N ; r++) {
            str = br.readLine();
            st = new StringTokenizer(str, " ");
            for (int c=0; c<M ; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        
        bfs(0,0);
        
        // int answer = 0; // 1. 직행불가 검 경유 가능, 2. 둘 다 불가, 3. 
        int answer = shortestTime;
        int direct = distance[N-1][M-1];
        if (direct != -1){
            answer = Math.min(answer, direct);
        }
        
        if ( answer <= T ){
            System.out.println(answer);
        } else {
            System.out.println("Fail");
        }
    }
    
    public static void bfs(int sr, int sc){
        Deque<int[]> q = new ArrayDeque<int[]>();
        
        q.offer(new int[]{sr,sc});
        distance[sr][sc] = 0; // 0으로 초기화하는게 맞음 // 출발지에 공주가 있지는 않으니까
        
        while ( !q.isEmpty() ){
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            
            // 목적지 도착 시 거리 반환
            if(r == N - 1 && c == M - 1){
                shortestTime = Math.min(shortestTime, distance[r][c]);
                break;
            }
            
            for (int i=0; i < 4; i++){
                int nr = r + dy[i];
                int nc = c + dx[i];
                
                if(isInbound(nr, nc)){
                    if (map[nr][nc] == 2){ // 전설의 명검 "그람" 만나면 바로 남은 거리 벽 무시하고 계산하여 Return
                        // distance[N-1][M-1] = distance[r][c] + N - 1 - nr + M - 1 - nc + 1;
                        // 위 처럼 초기화하면 검을 획득했을 때와 획득하지 않고 갔을 때를 비교할 수 없다.
                        int swordTime = distance[r][c] + N - 1 - nr + M - 1 - nc + 1; // 따로 관리
                        shortestTime = Math.min(shortestTime, swordTime);
                        // break;
                        // 검을 만나서 길을 가도 검을 안가지고 갔을 때 괜찮았을 경우가 있다.
                    }
                    
                    if (map[nr][nc] == 1){ // 벽은 통과 못함
                        continue;
                    }
                        
                    if(distance[nr][nc] == -1){
                        distance[nr][nc] = distance[r][c] + 1;
                        q.add(new int[]{nr, nc});
                    }
                    
                    
                }
            }
        }
  
    }    
    
    public static boolean isInbound(int r, int c){
        if ( c >=0 && c < M && r >= 0 && r < N ){
            return true;
        }
        return false;
    }
}