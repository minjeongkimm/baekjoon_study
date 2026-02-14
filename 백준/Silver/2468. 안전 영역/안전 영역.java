import java.io.*;
import java.util.*;

/*
    [문제 요약]
    안전 영역: 특정 높이 초과의 모든 영역
    문제에서 구하는 것: 안전 영역 덩어리의 최대 개수
    [문제 접근]
    높이를 1부터 100까지 높여가면서 안전 영역 덩어리 개수 카운트
    안전 영역 덩어리가 0이하가 되면 -> 즉시 조기 종료
    BFS => 하나하나 체크? 각 원소 값이 특정 높이 이하인지 아닌지 + 방문 체크

    [문제 조건]
    2 <= N <= 100, 1 <= h <= 100

    [고민]
    최적화 고민 됨 -> 이미 특정 높이 이하리고 체크된건 다음 단계에서도 마찬가지니까
    -> 이걸 누적으로 처리할 수 없을까?

 */


public class Main {
    static int N;       //배열의 크기
    static int[][] map; //배열 정보

    //4 방향 배열 (상우하좌)
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean[][] visited; //방문 체크

    static int ans = 0; //정답 변수
    static int cnt;                //덩어리 개수


    static Queue<int[]> q;      //큐

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }//배열 입력 끝

        //bfs 수행 (비가 오는 높이 0부터 100까지)
        for (int h = 0; h <= 100; h++) {
            visited = new boolean[N][N];    //방문 배열 초기화
            cnt = 0;                        //카운트 초기화
            q = new ArrayDeque<>();         //큐 초기화

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && map[i][j] > h) {    //특정 높이 초과면서 아직 방문 처리 안된거
                        q.add(new int[]{i, j});
                        visited[i][j] = true;
                        findSafeZone(h);
                        cnt++;
                    }
                }
            }

            if (cnt == 0)    //cnt가 0이면 조기 종료
                break;

            ans = Math.max(ans, cnt);

        }//높이 1부터 100까지 체크

        //정답 출력
        System.out.println(ans);


    }//main

    public static void findSafeZone(int height) {

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int cr = temp[0];
            int cc = temp[1];

            for (int i = 0; i < 4; i++) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];

                //1. 범위 체크
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

                //2. 방문 체크
                if (visited[nr][nc]) continue;

                //3. 안전영역 여부 체크
                if (map[nr][nc] <= height) continue;

                //4. 위 조건 다 충족하면
                q.add(new int[]{nr, nc});
                visited[nr][nc] = true;

            }


        }//q가 빌 때까지 반복

    }//bfs


}
