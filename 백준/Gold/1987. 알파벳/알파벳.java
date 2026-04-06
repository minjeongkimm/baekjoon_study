import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static char[][] map;
    static int ans;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        ans = 1;
        /// DFS

        dfs(0, 0, 1, 1 << (map[0][0] - 'A'));
        System.out.println(ans);

    }// main

    // dfs
    static void dfs(int cr, int cc, int cnt, int mask) {

        // 최댓값 갱신
        ans = Math.max(ans, cnt);

        for (int i = 0; i < 4; i++) {
            int nr = cr + dr[i];
            int nc = cc + dc[i];

            // 1. 범위 체크
            if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;

            // 2. 방문 체크
            int nAlpha = map[nr][nc] - 'A';
            if ((mask & (1 << nAlpha)) != 0) continue;

            // 3. 위 조건 다 통과하면
            dfs(nr, nc, cnt + 1, mask | 1 << nAlpha);
        }

    }//dfs

}
