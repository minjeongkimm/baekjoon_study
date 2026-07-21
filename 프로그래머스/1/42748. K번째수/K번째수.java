import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int index = 0;
        
        for(int[] tmp : commands){
            int i = tmp[0];
            int j = tmp[1];
            int k = tmp[2];
            
            ArrayList<Integer> arr = new ArrayList<>();
            for(int idx=i-1; idx<j; idx++){
                arr.add(array[idx]);
            }
            
            Collections.sort(arr);
            answer[index++] = arr.get(k-1);            
        }
        
        return answer;
    }
}