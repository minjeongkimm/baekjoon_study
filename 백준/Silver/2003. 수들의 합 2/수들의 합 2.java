import java.io.*;
import java.util.*;

public class Main {
    static int N, M;    //N: 수의 개수, M: 합
    static int[] nums;
    static int ans;     //경우의 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //1. 입력
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        //2. 투 포인터 이용해서 구간 합 구하기
        int l = 0;
        int r = l;
        int sum = nums[0];

        while (r < N) {
            //2-1. M과 비교
            if (sum == M) {        //값이 같으면 
                ans++;
                sum -= nums[l];
                l++;
            } else if (sum > M) {  //값이 크면
                sum -= nums[l];
                l++;
            } else {               //값이 작으면
                r++;
                if (r < N) sum += nums[r];
            }

        }

        System.out.println(ans);

    }
}
