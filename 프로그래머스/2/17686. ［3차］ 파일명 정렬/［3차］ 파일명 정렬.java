import java.util.*;

class Solution {
    
    public class File implements Comparable<File> {
        String head;
        String number;
        String tail;
        String fileName;
        
        public File (String fileName){
            this.fileName = fileName;
                
            int numStartIndex = -1;
            int numEndIndex = -1;
             
            for (int i=0; i < fileName.length();i++){
                
                if (Character.isDigit(fileName.charAt(i))) {
                    if (numStartIndex == -1) {
                        numStartIndex = i;
                    }
                } else {
                    if (numStartIndex != -1) {
                        numEndIndex = i;
                        break;
                    }
                }
                    

            

            }
            
            if (numEndIndex == -1) {
                numEndIndex = fileName.length();
            }

            if (numEndIndex - numStartIndex > 5) {
                numEndIndex = numStartIndex + 5;
            }
            
            this.head = fileName.substring(0, numStartIndex);
            this.number = fileName.substring(numStartIndex, numEndIndex);
            this.tail = fileName.substring(numEndIndex);
        }
        
        // compareTo 메서드 구현
        @Override
        public int compareTo(File other) {
            int result = this.head.toLowerCase().compareTo(other.head.toLowerCase());
            
            if( result != 0){
                return result;
            }
            return Integer.parseInt(this.number) - Integer.parseInt(other.number);
                   
        }

    }
    
    
    public String[] solution(String[] files) {
        List<File> list = new ArrayList<>();
        for(String f : files){
            
            list.add(new File(f));
        }
        
        Collections.sort(list);
        
        String[] answer = new String[files.length];
        
        for(int i =0; i<list.size();i++){
            answer[i] = list.get(i).fileName;
        }
        return answer;
    }
}