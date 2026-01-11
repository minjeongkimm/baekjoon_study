import java.io.*;
import java.util.*;


public class Main {
    static int K, sum;

    static Stack<Integer> st = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());
        for(int i=0; i<K; i++){
            int num = Integer.parseInt(br.readLine());
            if(num == 0)
                st.pop();
            else st.push(num);
        }

        sum = 0;
        while(!st.isEmpty()){
            sum += st.pop();
        }

        System.out.println(sum);
    }
}
