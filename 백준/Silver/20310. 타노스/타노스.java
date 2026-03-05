import java.util.*;
import java.io.*;

public class Main{
public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    String S = br.readLine();
    // S 길이 -> 4 8 12
    // S 길이 / 2 -> 2 4 6
    // S 길이 / 4 -> 1 2 3
    int len = S.length()/4; // 0 또는 1을 빼야하는 횟수 = 반복문 횟수
   

    String result = "0".repeat(len) + "1".repeat(len);

    System.out.println(result);
}
}