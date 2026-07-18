import java.util.*;


class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        
        // 1. 남은 작업일수 저장하는 큐 (기능 순)
        Deque<Integer> q = new ArrayDeque<>();
        for(int i=0; i<progresses.length; i++){
            q.offer((100-progresses[i] + speeds[i] - 1)/speeds[i]);
        }
        
        // 2. 큐에서 꺼내서 비교
        ArrayList<Integer> deploy = new ArrayList<>();
        int cnt = 1;
        int max = q.poll();
        
        while(!q.isEmpty()){
            // 3. 뒤에 오는 작업들이 끝났는지 확인
            if(max >= q.peek()){    // 3-1. 뒤에 오는 작업이 끝났으면
                cnt++;
                q.poll();
                
            }else{                  // 3-2. 뒤에 오는 작업이 끝나지 않았으면 
                deploy.add(cnt);
                cnt = 1;
                max = q.poll();
            }
        }
        
        deploy.add(cnt);    // 마지막 꺼 삽입 
        
        // 4. 배열 변환 (-> 이부분 최적화 방법 알아보기)
        int[] answer = new int[deploy.size()];
        for(int i=0; i<deploy.size(); i++){
            answer[i] = deploy.get(i);
        }
        
        return answer;
    }
}