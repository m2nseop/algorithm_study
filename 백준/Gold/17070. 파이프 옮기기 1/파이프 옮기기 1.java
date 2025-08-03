import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    
    static int method = 0;
    static int N;
    static int[][] house;
 
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str, " ");
        N = Integer.parseInt(st.nextToken());
        
        house = new int[N][N];
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j < N; j++){
                house[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        dfs(0,1,0);
        System.out.println(method);
        
    }
    
    // dir = 0 가로
    // dir = 1 세로
    // dir = 2 대각선
    
    static void dfs(int x, int y, int dir){
        int nx=0,ny=0;
        if(x == N-1 && y == N-1){
            method++;
            return;
        }
        
        // 현재 파이프가 가로 or 대각선일때 가로로 갈 수 있음
        if( dir == 0 || dir == 2){
            nx = x;
            ny = y + 1;
            if(isValidateBoundary(nx, ny) && house[nx][ny] == 0){
                dfs(nx, ny, 0);
            }
        }
        
        // 현재 파이프가 세로 or 대각선일때 세로로 갈 수 있음
        if( dir == 1 || dir == 2){
            nx = x + 1;
            ny = y;
            if(isValidateBoundary(nx, ny) && house[nx][ny] == 0){
                dfs(nx, ny, 1);
            }
        }
        
        // 현재 파이프가 가로 or 세로 or 대각선일 때 대각선으로 갈 수 있음
        nx = x + 1;
        ny = y + 1;
        if(isValidateBoundary(nx, ny) && house[nx][ny] == 0 && house[x+1][y] == 0 && house[x][y+1] == 0){
            dfs(nx, ny, 2);
        }
        
        
        
        
    }
    
    static boolean isValidateBoundary(int x, int y){
        if(x < N && y < N && x >= 0 && y >= 0){
            return true;
        }
        return false;
    }
}