
class Solution {
    public boolean isValid(String s) {
        
        // 1. 괄호 쌍 HashMap 생성
        Map<Character, Character> pair = new HashMap<>();
        pair.put(')', '(');
        pair.put('}', '{');
        pair.put(']', '[');

        // 2. Stack 생성
        Deque<Character> stack = new ArrayDeque<>();

        // 3. 문자열 하나씩 꺼내기
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            
            if(pair.containsKey(c)){    // 3-1. 닫는 괄호면
                if(stack.isEmpty() || stack.pop() != pair.get(c))
                    return false;
            }else{                      // 3-2. 여는 괄호면 
                stack.push(c);
            }

        }

        // 4. 스택 비어있는지 확인 
        if(stack.isEmpty()) return true;
        else return false;

    }

}