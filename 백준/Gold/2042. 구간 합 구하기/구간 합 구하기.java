import java.io.*;
import java.util.*;

public class Main{
    
    public static void main (String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 수의 개수
        int M = Integer.parseInt(st.nextToken()); // 수의 변경이 일어나느 횟수
        int K = Integer.parseInt(st.nextToken()); // 구간의 합을 구하는 횟수
        
        long[] arr = new long[N];
        long[] tree = new long[4*N];
        
        // 원본배열 값 넣기
        for (int i = 0; i < N; i++){
            arr[i] = Long.parseLong(br.readLine());
        }
        
        init(arr, tree, 1, 0, N-1); // 세그먼트 트리 생성
        
        int a,b = 0;
        long c = 0;
        long queryResult = 0;
        
        for (int i = 0; i < M + K; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()); // 쿼리 종류
            b = Integer.parseInt(st.nextToken()); // 업뎃 index or 구간합 시작범위
            c = Long.parseLong(st.nextToken()); // 업뎃 val or 구간합 끝범위
        
            if ( a==1 ){
                update(arr, tree, 1, 0, N-1, b-1, c);
            }
            if ( a==2 ){
                queryResult = query(arr, tree, 1, 0, N-1, b-1, c-1);
                System.out.println(queryResult);
            }
            
        }
        
    
        
    }
    
    public static void init(long[] a, long[] tree, int node, int start, int end){
        if(start==end){
            tree[node] = a[start];
            return ;
        }
        init(a, tree, 2*node, start, (start+end)/2);
        init(a, tree, 2*node + 1, (start+end)/2 + 1, end);
        tree[node] = tree[2*node] + tree[2*node + 1];
    }
    
    public static void update(long[] a, long[] tree, int node, int start, int end,  int index, long val){
        if (index < start || index > end){
            return ;
        }
        if (start == end){
            a[index] = val;
            tree[node] = val;
            return ;
        }
        update(a, tree, 2*node, start, (start+end)/2, index, val);
        update(a, tree, 2*node + 1, (start+end)/2 + 1 ,end, index, val);
        tree[node] = tree[2*node] + tree[2*node + 1];
    }
    
    public static long query(long[] a, long[] tree, int node, int start, int end, int left, long right ){
        if(end < left || start > right){
            return 0;
        }
        if(start >= left && end <= right){
            return tree[node];
        }
        long lsum = query(a, tree, 2*node, start, (start+end)/2, left, right);
        long rsum = query(a, tree, 2*node + 1, (start+end)/2 + 1, end,left, right);
        return lsum + rsum;
    }
}