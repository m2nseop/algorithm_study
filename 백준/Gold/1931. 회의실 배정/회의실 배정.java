import java.io.*;
import java.util.*;

public class Main {
    // 배열보다는 클래스 만드는게 더 가독성이 좋음
    // int[][] meeting = new int[][] 보다는 ..
    static class Meeting {
        int start, end;
        Meeting(int s, int e) { 
            start = s; end = e; 
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        
        List<Meeting> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list.add(new Meeting(s, e));
        }

        // 종료시간 오름 차순 정렬, 종료시간 같으면 시작시간 오름 차순 정렬
        // 람다 함수
        
        //Java에서 익명 함수를 간단히 표현하는 문법
        //Comparator 같은 인터페이스 구현을 한 줄로 표현 가능
        //(a, b) -> { 비교 로직 } 형태
        
        // a.end - b.end로 대소비교에 대한 값을 return 하면 오버 플로우가 발생할 수 있으니
        // 이를 방지하고자 Integer.compare씀. 얘는 리턴을 -1, 0, 1을 함 
        list.sort((a, b) -> {
            if (a.end != b.end) return Integer.compare(a.end, b.end);
            return Integer.compare(a.start, b.start);
        });

        int cnt = 0, lastEnd = 0;
        for (Meeting m : list) {
            if (m.start >= lastEnd) {
                cnt++;
                lastEnd = m.end;
            }
        }
        System.out.println(cnt);
    }
}