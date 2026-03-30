import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] map;
    
    static class Fish implements Comparable<Fish>{
        int size;
        int x;
        int y;
        int distance;
        
        Fish (int size, int x, int y, int distance){
            this.size = size;
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
        
        @Override 
        public int compareTo(Fish o){
            if (this.distance != o.distance) return this.distance - o.distance;
            if (this.x != o.x) return this.x - o.x;
            return this.y - o.y;
        }
    }
    
    static class BabyShark{
        int size;
        int exp;
        int x;
        int y;
        int surviveTime;
        
        BabyShark (int size, int exp, int x, int y, int surviveTime){
            this.size = size;
            this.exp = exp;
            this.x = x;
            this.y = y;
            this.surviveTime = surviveTime;
        }
        
        public void eat(Fish f){
            this.surviveTime += f.distance;
            this.x = f.x;
            this.y = f.y;
            this.exp++;
            
            if(this.exp == this.size){
                this.size++;
                this.exp = 0;
            }
        }
    }
    

    static boolean inRange(int x, int y){
        return x >= 0 && y >= 0 && x < N && y < N;
    }
    

    static Fish bfs(BabyShark bs){
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        q.add(new int[]{bs.x, bs.y, 0});
        visited[bs.x][bs.y] = true;
        
        List<Fish> candidates = new ArrayList<>();

        int minDist = Integer.MAX_VALUE;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int dist = cur[2];
            
            if(dist > minDist) break;
            if(map[x][y] > 0 && map[x][y] < bs.size){
                candidates.add(new Fish(map[x][y],x,y,dist));
                minDist = dist;
            }

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (!inRange(nx, ny) || visited[nx][ny]) continue;
                if (map[nx][ny] > bs.size) continue;
                
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny, dist+1});
                   
                
            }
        }
        
        if(candidates.isEmpty()) return null;
        
        Collections.sort(candidates);
        
        return candidates.get(0);
    }
    
    public static void main(String[] args) throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        
        map = new int[N][N];
        
        BabyShark bs = null;
        
        for ( int i =0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for ( int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9){
                    bs = new BabyShark(2,0,i,j,0);
                    map[i][j] = 0;
                }
            }
        }
        
        Fish eatableFish = null;
        
        // 물고기 찾기
        while(true) {
            Fish target = bfs(bs);

            if (target == null) break;

            bs.eat(target);
            map[target.x][target.y] = 0;
        }

        System.out.println(bs.surviveTime);
        
    }
}