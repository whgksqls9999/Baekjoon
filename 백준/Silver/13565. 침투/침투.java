import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] arr;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][M];

		for (int i = 0; i < N; ++i) {
			String input = br.readLine();
			for (int j = 0; j < M; ++j) {
				arr[i][j] = Integer.parseInt(input.substring(j, j + 1));
			}
		}
		visited = new boolean[N][M];

		for (int i = 0; i < M; ++i) {
			if (arr[0][i] == 0) {
				DFS(0, i);
			}
		}
		System.out.println("NO");
	}

	public static void DFS(int r, int c) {
		if (r == N - 1) {
			System.out.println("YES");
			System.exit(0);
		}

		visited[r][c] = true;

		for (int i = 0; i < 4; ++i) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
				if (arr[nr][nc] == 0 && !visited[nr][nc]) {
					DFS(nr, nc);
				}
			}
		}
	}
}