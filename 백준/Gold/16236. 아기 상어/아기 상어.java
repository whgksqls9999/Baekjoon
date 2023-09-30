import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static Queue<int[]> pos = new LinkedList<>();
	static int[][] map;
	static int[][] dist;
	static int[][] times;

	static int[] dr = { -1, 0, 0, 1 };
	static int[] dc = { 0, -1, 1, 0 };

	static int N;
	static int cnt; // 상어가 먹은 물고기
	static int time; // 시간
	static int shark = 2; // 상어의 크기

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken()); // 공간의 크기

		map = new int[N][N];
		dist = new int[N][N];
		times = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					pos.add(new int[] { i, j });
				}
			}
		}

		while (!pos.isEmpty()) {
			int[] now = pos.poll();
			findRoute(now[0], now[1]);
		}

//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
//		System.out.println();
////		for (int i = 0; i < N; i++) {
////			System.out.println(Arrays.toString(dist[i]));
////		}
//		System.out.println();
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(times[i]));
//		}
//		System.out.println();
		System.out.println(time);
//		System.out.println();
//		System.out.println(shark);
//		System.out.println(cnt);
	} // main

	static void findRoute(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		Queue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				int ans = 0;
				if (o1[2] != o2[2]) {
					ans = o1[2] - o2[2];

				} else if (o1[0] != o2[0]) {
					ans = o1[0] - o2[0];

				} else if (o1[1] != o2[1]) {
					ans = o1[1] - o2[1];
				}

				return ans;
			}
		});
		boolean[][] visited = new boolean[N][N];

		queue.add(new int[] { r, c });
		visited[r][c] = true;
		dist[r][c] = 0;

		while (!queue.isEmpty()) {
			int[] now = queue.poll();

			for (int i = 0; i < 4; i++) {

				int nr = now[0] + dr[i];
				int nc = now[1] + dc[i];

				if (nr >= 0 && nc >= 0 && nr < N && nc < N) {

					if (!visited[nr][nc]) {

						visited[nr][nc] = true;
						dist[nr][nc] = dist[now[0]][now[1]] + 1;

						// 나보다 작은 먹잇감을 찾았다면
						if (map[nr][nc] != 0 && map[nr][nc] < shark) {
							// 같은 거리에 있다면 우선순위큐에 넣어준다
							if (pq.isEmpty() || (!pq.isEmpty() && pq.peek()[2] == dist[nr][nc])) {
								pq.add(new int[] { nr, nc, dist[nr][nc] });
							}
							// 이미 다른 먹이를 찾았고, 더 먼 거리를 탐색하기 시작했다면
							else if (!pq.isEmpty() && pq.peek()[2] != dist[nr][nc]) {
								int[] next = pq.poll();
								int newR = next[0];
								int newC = next[1];
								eat(r, c, newR, newC);
								return;
							}
//							eat(r, c, nr, nc);
//							return;
						}

						if (map[nr][nc] <= shark) {
							queue.add(new int[] { nr, nc });
						}
					}
				}
			}
		}
		if (!pq.isEmpty()) {
			int[] next = pq.poll();
			int newR = next[0];
			int newC = next[1];
			eat(r, c, newR, newC);
			return;
		}

	}

	static void eat(int r1, int c1, int r2, int c2) {

		// 상어 이동시키기
		map[r2][c2] = map[r1][c1];
		map[r1][c1] = 0;

		// 시간 증가시키기
		time += dist[r2][c2];

		times[r2][c2] = time; // 테스트용

		cnt++;

		if (cnt == shark) {
			map[r2][c2] = ++shark;
			cnt = 0;
		}

//		System.out.println(r2 + " : " + c2 + " : " + time);
		// 현재 상어의 좌표 저장하기
		pos.add(new int[] { r2, c2 });
	}
}