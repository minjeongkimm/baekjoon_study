class Solution {
    boolean solution(String s) {

        // 1. 소문자로 변환
        s = s.toLowerCase();

        // 2. 각 p의 개수와 y의 개수 카운트
        int pCount = 0;
        int yCount = 0;
        
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == 'p')  pCount++;
            else if(s.charAt(i) == 'y') yCount++;
        }
        
        if(pCount == yCount) return true;
        else return false;
    }
}