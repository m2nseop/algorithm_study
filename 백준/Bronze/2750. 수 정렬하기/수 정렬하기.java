import java.util.Scanner;
import java.util.Arrays;

public class Main {
 
	public static void main(String[] args) {
 
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
        int arr[] = new int[n];
		for (int i = 0; i < n ; i++){
            arr[i] = in.nextInt();    
        }
        
        Arrays.sort(arr);
        
        for (int i = 0; i < n ; i++){
            System.out.println(arr[i]);    
        }
		
		in.close();
	}
}