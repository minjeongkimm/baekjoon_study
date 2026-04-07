import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] W;     // 물건들의 무게
    static int[] V;     // 물건들의 가치
    static int[] dp;    // dp (무게 당 넣을 수 있는 최대 가치)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. 입력
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        W = new int[N];
        V = new int[N];
        dp = new int[K + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        // 2. dp (물건 입력 순으로 하나씩 넣어보기)
        for (int i = 0; i < N; i++) {
            // 최대 무게부터 현재 물건의 무게까지 진행 (현재 물건 넣을지 말지 ..)
            for (int w = K; w >= W[i]; w--) {
                dp[w] = Math.max(dp[w], dp[w - W[i]] + V[i]);
            }
        }

        System.out.println(dp[K]);
    }
}
