import java.util.*;
import java.io.*;

public class Main{
public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    StringBuilder S = new StringBuilder(br.readLine());
    
    int len = S.length();
    
    int countOne = ( S.length() - S.toString().replace("1","").length() ) / 2;
    int countZero = ( S.length() - S.toString().replace("0","").length() ) / 2;
    
    for ( int i = 0; i < len ; i++){
        
        if (S.charAt(i) == '1' && countOne != 0){
            // 없애야함
            S.setCharAt(i, ' ');
            countOne--;
        }
        if (S.charAt(len -1 -i) == '0' && countZero != 0 ){
            // 없애야함
            S.setCharAt(len -1 -i, ' ');
            countZero--;
        }

    }
    System.out.println(S.toString().replace(" ", ""));
}
}