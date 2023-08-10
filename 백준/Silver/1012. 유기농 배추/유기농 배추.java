import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int M; // 가로길이
	static int N; // 세로길이
	static int K; // 배추 수
	static boolean[][] visited; // 방문 배열
	static int[][] map; // 맵
	static int cnt; // 필요한 배추 흰지렁이 수

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());

		for (int tc = 0; tc < T; tc++) {
			cnt=0;
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			visited = new boolean[N][M];
			map = new int[N][M];

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				map[b][a] = 1;
			} // 배추 심기

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (!visited[i][j] && map[i][j] != 0) {
						cnt++;
						DFS(i, j);
					}
				}
			}
			System.out.println(cnt);
		}
	}

	static void DFS(int r, int c) {
		if (visited[r][c]) {
			return;
		}

		visited[r][c] = true;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
				if (map[nr][nc] != 0 && !visited[nr][nc]) {
					DFS(nr, nc);
				}
			}
		}
	}
}