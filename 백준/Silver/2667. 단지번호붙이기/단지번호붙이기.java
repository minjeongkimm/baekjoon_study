import java.io.*;
import java.util.*;

public class Main {
    static int N;   //지도의 크기
    static int[][] map; //지도 정보
    static boolean[][] visited;
    //단지 수, 단지별 가구 수
    static int villageCnt;
    static List<Integer> homeCnt;

    //4방향에 대한 배열(상하좌우)
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //1.입력
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        homeCnt = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        //2. 반복문 돌면서 확인
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                //집이 있는 곳이면서 아직 방문 안한 곳 탐색
                if (map[i][j] == 1 && !visited[i][j]) {
                    bfs(i, j);
                    villageCnt++;
                }
            }
        }

        System.out.println(villageCnt);

        Collections.sort(homeCnt);
        for (Integer n : homeCnt) {
            System.out.println(n);
        }

    }// main


    static void bfs(int sr, int sc) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sr, sc});
        visited[sr][sc] = true;
        int cnt = 0;

        while (!q.isEmpty()) {
            //1. 현재 좌표 꺼냄
            int[] curr = q.poll();
            int cr = curr[0];
            int cc = curr[1];
            cnt++;

            //3. 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];

                //1. 범위 체크
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

                //2. 방문 체크
                if (visited[nr][nc]) continue;

                //3. 집 체크
                if (map[nr][nc] == 1) {
                    //4. 방문 처리
                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc});

                }
            }

        }

        homeCnt.add(cnt);

    }

}
