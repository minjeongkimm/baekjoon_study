import java.util.*;
import java.io.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        
        String answer = "";
        
        // 1. 이름과 인원을 저장하는 해쉬맵 생성
        Map<String, Integer> cnt = new HashMap<>();
        for(String s : participant){
            cnt.put(s, cnt.getOrDefault(s, 0) + 1);
        }
        
        // 2. 완주자 배열 돌면서 다시 인원 감소
        for(String s : completion){
            cnt.put(s, cnt.getOrDefault(s, 0) - 1);
        }
        
        // 3. 인원이 남아있는 참가자 검토
        for(Map.Entry<String, Integer> e : cnt.entrySet()){
            if(e.getValue() != 0)
                answer = e.getKey();
        }
        
        return answer;
    }
}