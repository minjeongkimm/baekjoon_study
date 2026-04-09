import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;

        // 1. 공백 제거 후 set에 저장
        HashSet<String> phoneSet = new HashSet<>();
        for(String s : phone_book){
            phoneSet.add(s.replaceAll(" ", ""));
        }
       
        //2. 접두사 찾기
        for(String s : phoneSet){
            //수 쪼개서 탐색 (본인 제외)
            for(int i=0; i < s.length(); i++){
                if(phoneSet.contains(s.substring(0, i))){
                    answer = false;
                    break;
                };
            }                 
        }
        
        
        return answer;
    }
}