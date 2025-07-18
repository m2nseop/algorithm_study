import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Stack;

public class Main {
 
	public static void main(String[] args) throws IOException {
 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

 		String str = br.readLine();
		StringTokenizer ster = new StringTokenizer(str," ");
        int N = Integer.parseInt(ster.nextToken());

		int X = 0;
        int num = 0;
        Stack<Integer> st = new Stack<>();
        
        for (int i = 0; i<N; i++){
            ster = new StringTokenizer(br.readLine()," ");
            X = Integer.parseInt(ster.nextToken());
            if ( X == 1 ){
                num = Integer.parseInt(ster.nextToken());
                st.push(num);
            } else if ( X == 2 ){
                if (!st.isEmpty()) {
                    num = st.pop();
                    System.out.println(num);
                } else {
                    System.out.println(-1);
                }
            } else if ( X == 3 ){
                System.out.println(st.size());
            } else if ( X == 4 ){
                if (st.isEmpty()){
                    System.out.println(1);
                } else {
                    System.out.println(0);
                }
            } else if ( X == 5 ){
                if (!st.isEmpty()) {
                    num = st.peek();
                    System.out.println(num);
                } else {
                    System.out.println(-1);
                }
            }



        }

	}
}