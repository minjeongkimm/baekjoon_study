import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Queue<Integer> q = new ArrayDeque<>();
        for(int i=1; i<=N; i++){
            q.offer(i);
        }

        while(q.size() != 1){

            //1. 제일 위에 있는 카드 버리기
            q.poll();

            //2. 제일 위에 있는 카드 마지막 층으로 옮기기
            q.offer(q.poll());

        }

        //3. 마지막 카드 출력
        System.out.println(q.poll());

    }
}
