import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int[][] map;
	static int[][] tmap;
	static boolean[][] visited;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		visited = new boolean[n][m];

		int[] start = null;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					start = new int[] { i, j };
				}
			}
		}
		tmap = new int[n][m];
		BFS(start);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (tmap[i][j] == 0 && map[i][j] == 1) {
					System.out.print(-1 + " ");
				} else {
					System.out.print(tmap[i][j] + " ");
				}
			}
			System.out.println();
		}
	}// main

	static void BFS(int[] start) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(start);
		visited[start[0]][start[1]] = true;

		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nr = now[0] + dr[i];
				int nc = now[1] + dc[i];
				if (nr >= 0 && nc >= 0 && nr < n && nc < m) {
					if (!visited[nr][nc] && map[nr][nc] == 1) {
						visited[nr][nc] = true;
						queue.add(new int[] { nr, nc });
						tmap[nr][nc] = tmap[now[0]][now[1]] + 1;
					}
				}
			}
		}
	}

}