import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue <Integer> pq = new PriorityQueue<>();
    
        for(int n : scoville){
            pq.offer(n);
        }
        
        if(pq.peek() >= K) return 0;
        
        while(pq.size() >= 2){
            int i = pq.poll();
            int j = pq.poll();
            
            answer++;
            pq.offer(i + j*2);
            if(pq.peek() >= K) return answer;
        }
        
        return -1;
        
    }
}