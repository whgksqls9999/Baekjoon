import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int w, h;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while (true) {
			int cnt = 0;
			Queue<int[]> queue = new LinkedList<>();
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if (w == 0 && h == 0) {
				break;
			}
			visited = new boolean[h][w];
			map = new int[h][w];
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						queue.add(new int[] { i, j });
					}
				}
			} // 맵 생성
			while (!queue.isEmpty()) {
				int[] now = queue.poll();
				if (!visited[now[0]][now[1]]) {
					BFS(now[0], now[1]);
					cnt++;
				}
			}
			System.out.println(cnt);
		}
	}// main

	static void BFS(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { r, c });
		int[] now;
		while (!queue.isEmpty()) {
			now = queue.poll();
			for (int i = 0; i < dr.length; i++) {
				int nr = now[0] + dr[i];
				int nc = now[1] + dc[i];
				if (nr >= 0 && nc >= 0 && nr < h && nc < w) {
					if (!visited[nr][nc] && map[nr][nc] != 0) {
						queue.add(new int[] { nr, nc });
						visited[nr][nc] = true;
					}
				}
			}
		}
	}

}