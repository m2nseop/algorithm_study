import java.io.*;
import java.util.*;

public class Main{
    
    public static void main (String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 수의 개수

        int[] arr = new int[N];
        int[] tree = new int[4*N];
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int queryNum = Integer.parseInt(br.readLine());
        
        init(arr, tree, 1, 0, N-1); // 세그먼트 트리 생성
        
        int a,b,c = 0;
        int queryResult = 0;
        
        for(int i=0;i<queryNum;i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()); // 쿼리 종류
            b = Integer.parseInt(st.nextToken()); // 업뎃 index or 최솟값 시작범위
            c = Integer.parseInt(st.nextToken()); // 업뎃 val or 최솟값 끝범위
        
            if ( a==1 ){
                update(arr, tree, 1, 0, N-1, b-1, c);
            }
            if ( a==2 ){
                queryResult = query(arr, tree, 1, 0, N-1, b-1, c-1);
                System.out.println(queryResult);
            }
        } 
        
    }
    
    public static void init(int[] a, int[] tree, int node, int start, int end){
        if(start==end){
            tree[node] = a[start];
            return ;
        }
        init(a, tree, 2*node, start, (start+end)/2);
        init(a, tree, 2*node + 1, (start+end)/2 + 1, end);
        tree[node] = Math.min(tree[2*node],tree[2*node + 1]);
    }
    
    public static void update(int[] a, int[] tree, int node, int start, int end,  int index, int val){
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
        tree[node] = Math.min(tree[2*node],tree[2*node + 1]);
    }
    
    public static int query(int[] a, int[] tree, int node, int start, int end, int left, int right ){
        if(end < left || start > right){
            return -1;
        }
        if(start >= left && end <= right){
            return tree[node];
        }
        int lmin = query(a, tree, 2*node, start, (start+end)/2, left, right);
        int rmin = query(a, tree, 2*node + 1, (start+end)/2 + 1, end,left, right);
        if(lmin == -1){
            return rmin;
        }
        if(rmin == -1){
            return lmin;
        }
        return Math.min(lmin , rmin);
    }
}