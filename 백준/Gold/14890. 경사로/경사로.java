import java.io.*;
import java.util.*;

public class Main {
	static int N, L;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 지도 크기
		L = Integer.parseInt(st.nextToken()); // 경사로 길이
		ans = 0;

		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 입력 끝

		// 행 체크
		for (int i = 0; i < N; i++) {
			checkRoad(map[i]);
		}

		// 열 체크
		for (int j = 0; j < N; j++) {
			int[] copy = new int[N];
			for (int i = 0; i < N; i++) {
				copy[i] = map[i][j];
			}
			checkRoad(copy);
		}

		//정답 출력
		System.out.println(ans);

	}// main

	// 길이 되는지 체크하는 메서드
	static void checkRoad(int[] copy) {
		int prev = copy[0];
		boolean[] used = new boolean[N]; // 경사로 만들었는지 여부

		for (int i = 1; i < N; i++) {
			int diff = copy[i] - prev; // 현재 요소와 이전 값의 차이

			// 차이가 2 이상이면 조기 종료
			if (Math.abs(diff) > 1)
				return;

			// 경사로(하강) -> 미래 L개를 봐야함
			if (diff == -1) {
				// 경계 초과하면 조기 종료
				if (i + L - 1 >= N)
					return;
				// 경계 초과하지 않으면 경사로 확인
				prev = copy[i];
				for (int j = i; j <= i + L - 1; j++) {
					if (prev != copy[j] || used[j]) // 이전 값과 같지 않거나 이미 경사로 만들었으면 조기 종료
						return;

					used[j] = true;
				}

			} else if (diff == 1) { // 경사로(상승) -> 과거 L개를 봐야함
				// 경계 조건 확인
				if (i - L < 0)
					return;
				// 경계 초과하지 않으면 경사로 확인
				prev = copy[i - 1];
				for (int j = i - 1; j >= i - L; j--) {
					if (prev != copy[j] || used[j])
						return;
					used[j] = true;
				}
				prev = copy[i]; // 다시 원래 현재 값으로 되돌림
			}
		} // 전체 요소 탐색

		// 위 for 문을 통과하면 길이 됨
		ans++;
		return;

	}
}
