import java.io.*;
import java.util.*;

public class Main{
    
    public static void main (String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 수의 개수
        int M = Integer.parseInt(st.nextToken()); // 쌍의 개수
        
        int[] arr = new int[N];
        int[] minTree = new int[4*N];
        int[] maxTree = new int[4*N];
        
        for(int i = 0;i<N;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        
        initMinTree(arr, minTree, 1, 0, N-1); // 최소 세그먼트 트리 생성
        initMaxTree(arr, maxTree, 1, 0, N-1); // 최대 세그먼트 트리 생성
        
        int a,b = 0;
        int minResult = 0;
        int maxResult = 0;
        
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()); // 시작 범위
            b = Integer.parseInt(st.nextToken()); // 끝 범위
        
            minResult = minQuery(arr, minTree, 1, 0, N-1, a-1, b-1);
            maxResult = maxQuery(arr, maxTree, 1, 0, N-1, a-1, b-1);
            System.out.println(minResult + " " + maxResult);
            
        } 
        
    }
    
    public static void initMinTree(int[] a, int[] tree, int node, int start, int end){
        if(start==end){
            tree[node] = a[start];
            return ;
        }
        initMinTree(a, tree, 2*node, start, (start+end)/2);
        initMinTree(a, tree, 2*node + 1, (start+end)/2 + 1, end);
        tree[node] = Math.min(tree[2*node],tree[2*node + 1]);
    }
    
    public static void initMaxTree(int[] a, int[] tree, int node, int start, int end){
        if(start==end){
            tree[node] = a[start];
            return ;
        }
        initMaxTree(a, tree, 2*node, start, (start+end)/2);
        initMaxTree(a, tree, 2*node + 1, (start+end)/2 + 1, end);
        tree[node] = Math.max(tree[2*node],tree[2*node + 1]);
    }
    
    public static int minQuery(int[] a, int[] tree, int node, int start, int end, int left, int right ){
        if(end < left || start > right){
            return Integer.MIN_VALUE;
        }
        if(start >= left && end <= right){
            return tree[node];
        }
        int lmin = minQuery(a, tree, 2*node, start, (start+end)/2, left, right);
        int rmin = minQuery(a, tree, 2*node + 1, (start+end)/2 + 1, end,left, right);
        if(lmin == Integer.MIN_VALUE){
            return rmin;
        }
        if(rmin == Integer.MIN_VALUE){
            return lmin;
        }
        return Math.min(lmin , rmin);
    }
    
    public static int maxQuery(int[] a, int[] tree, int node, int start, int end, int left, int right ){
        if(end < left || start > right){
            return Integer.MAX_VALUE;
        }
        if(start >= left && end <= right){
            return tree[node];
        }
        int lmax = maxQuery(a, tree, 2*node, start, (start+end)/2, left, right);
        int rmax = maxQuery(a, tree, 2*node + 1, (start+end)/2 + 1, end,left, right);
        if(lmax == Integer.MAX_VALUE){
            return rmax;
        }
        if(rmax == Integer.MAX_VALUE){
            return lmax;
        }
        return Math.max(lmax , rmax);
    }
}