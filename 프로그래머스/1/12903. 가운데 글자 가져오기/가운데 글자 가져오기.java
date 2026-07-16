class Solution {
    public String solution(String s) {
        
        StringBuilder sb = new StringBuilder(s);

        if(s.length()%2 == 0){
            return sb.substring(s.length()/2-1, s.length()/2+1);           
        }else{
            return sb.substring(s.length()/2, s.length()/2+1);
        }
    
    }
}