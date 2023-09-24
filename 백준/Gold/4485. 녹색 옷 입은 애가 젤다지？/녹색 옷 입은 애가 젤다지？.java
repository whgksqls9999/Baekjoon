import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map;
	static int[][] dist;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; true; tc++) {
			// 맵 크기 N 입력받기
			N = Integer.parseInt(br.readLine());

			if (N == 0)
				break;

			sb.append("Problem ").append(tc).append(": ");

			// 원본 지도 map, 최단 경로 담을 dist 선언
			map = new int[N][N];
			dist = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
				dist[i] = map[i].clone();
			} // for

			Dijkstra(0, 0);
			sb.append(dist[N - 1][N - 1]).append("\n");

		} // while
		System.out.println(sb);
	} // main

	static void Dijkstra(int r, int c) {
		Queue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});

		pq.add(new int[] { r, c, 0 });
		for (int i = 0; i < N; i++) {
			Arrays.fill(dist[i], 99999);
		}
		dist[r][c] = map[r][c];

		while (!pq.isEmpty()) {
			int[] now = pq.poll();
			for (int i = 0; i < 4; i++) {
				int nr = now[0] + dr[i];
				int nc = now[1] + dc[i];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
					if (dist[nr][nc] > dist[now[0]][now[1]] + map[nr][nc]) {
						dist[nr][nc] = dist[now[0]][now[1]] + map[nr][nc];
						pq.add(new int[] { nr, nc, map[nr][nc] });
					}
				}
			}
		}
	}
}