import java.io.*;
import java.util.*;

public class Main {
    public static int N, K;
    public static int[] num;

    public static int ans = 0;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        num = new int[N];
        for(int i=0; i<N; i++){
            num[i] = Integer.parseInt(br.readLine());
        }

        

        for(int i=N-1; i>=0; i--){
            if(N<=0) break;

            ans += K/num[i];
            K%=num[i];
        }

        System.out.println(ans);
    }
}