import java.io.*;
import java.util.*;

class Point {
	int r, c;

	public Point() {
	}

	public Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
	
}

public class Main {
	static int N, M;
	static int[][] map;
	static int ans = Integer.MAX_VALUE;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static List<Point> virus = new ArrayList<>();
	static List<List<Point>> picked = new ArrayList<>();

	static int blankCount = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N]; // 연구소 배열
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int info = Integer.parseInt(st.nextToken());
				map[i][j] = info;
				
				if (info == 0)	//빈칸 개수 카운트
					blankCount++;
				else if (info == 2)
					virus.add(new Point(i, j)); // 바이러스 좌표 리스트에 저장

			}
		}

		// M개 뽑는 조합 생성
		pickVirus(0, 0, new ArrayList<>());

		// 조합 리스트에서 케이스 뽑아가면서 BFS 수행
		for (List<Point> active : picked) {
			int time = bfs(active);
			ans = Math.min(ans, time);
		}
		
		if(ans == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);

	}// main

	// backtracking
	static void pickVirus(int start, int depth, List<Point> path) {
		// M개 다 뽑으면 picked 리스트에 추가후 리턴
		if (depth == M) {
			picked.add(new ArrayList<>(path));
			return;
		}

		// 계속 뽑음..
		for (int i = start; i < virus.size(); i++) {
			path.add(virus.get(i));
			pickVirus(i + 1, depth + 1, path);
			path.remove(path.size() - 1);
		}

	}

	// BFS
	static int bfs(List<Point> active) {
		// 거리 겸 방문 체크 배열 매 조합마다 초기화
		int[][] dist = new int[N][N];
		for (int i = 0; i < N; i++)
			Arrays.fill(dist[i], -1);

		Queue<Point> q = new ArrayDeque<>();
		// 활성 바이러스 좌표 큐에 넣고 시작
		for (Point p : active) {
			dist[p.r][p.c] = 0;
			q.offer(p);
		}

		int infectedBlank = 0; // 감염된 빈칸 수
		int maxTime = 0; // 빈칸 감염걸리는데 걸린 최대 시간

		
		//만약 빈칸이 없으면 조기종료
		if (blankCount == 0)
			return 0;

		
		while (!q.isEmpty()) {
			Point curr = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nr = curr.r + dr[i];
				int nc = curr.c + dc[i];
				// 범위 체크
				if (nr < 0 || nr >= N || nc < 0 || nc >= N)
					continue; 
				// 벽 체크
				if (map[nr][nc] == 1)
					continue; 
				// 방문 체크
				if (dist[nr][nc] != -1)
					continue; 

				//거리 증가 후 큐에 넣음
				dist[nr][nc] = dist[curr.r][curr.c] + 1;
				q.offer(new Point(nr, nc));

				//빈칸일 때만 
				if(map[nr][nc] == 0) {
					infectedBlank++;	//감염된 빈칸 수 증가
					maxTime = Math.max(maxTime, dist[nr][nc]);
					//모든 빈칸 다 감염시키면 조기 종료
					if(infectedBlank == blankCount)
						return maxTime;
				}
				
			}//4방향 탐색

		}

		return Integer.MAX_VALUE;
	}

}
