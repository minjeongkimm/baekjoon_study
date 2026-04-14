import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    [문제 핵심]
    최대 사용할 수 있는 회의의 개수

    [문제 풀이 흐름]
    1. 끝나는 시간 기준으로 오름차순 정렬 진행
    2. 끝나는 시간이 가장 작은 회의 먼저 선택
    3. 다음 회의 선택 기준: 
    3-1. 이전 회의 끝나는 시간 이후(같은 시간도 포함) 시작하는 거
    3-2. 3-1를 만족하면서 끝나는 시간이 나머지 중 가장 작은 것 
*/


public class Main {

    static class Conference implements Comparable<Conference>{
        private int start;
        private int end;

        public Conference(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Conference o) {
            if(this.end != o.end)
                return this.end - o.end;
            return this.start - o.start;
        }

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Conference[] conferences = new Conference[N];
        
        // 1. 회의 입력
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            
            conferences[i] = new Conference(s, e);
        }
        
        // 2. 끝나는 시간 기준 정렬
        Arrays.sort(conferences);

        // 3. 회의 고르기
        int ans = 1;
        int curEnd = conferences[0].end;

        for(int i=1; i<N; i++){
            // 3-1. 시작 시간 체크
            if(conferences[i].start < curEnd) continue;

            // 3-2. 끝나는 시간 업데이트
            curEnd = conferences[i].end;
            ans++;
        }

        // 4. 정답 출력
        System.out.println(ans);

    }
}
