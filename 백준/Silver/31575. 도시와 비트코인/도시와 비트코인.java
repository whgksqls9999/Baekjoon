import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int[] dr = { 1, 0 };
	public static int[] dc = { 0, 1 };
	public static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		boolean[][] visited = new boolean[M][N];
		int[][] arr = new int[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		BFS(0, 0, visited, arr);
	}

	public static void BFS(int r, int c, boolean[][] visited, int[][] arr) {
		Queue<int[]> queue = new ArrayDeque<>();

		visited[r][c] = true;
		queue.add(new int[] { r, c });

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			for (int i = 0; i < 2; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];

				if (nr >= 0 && nr < M && nc >= 0 && nc < N) {
					if (!visited[nr][nc] && arr[nr][nc] == 1) {
						queue.add(new int[] { nr, nc });
						visited[nr][nc] = true;
					}
				}
			}
		}

		System.out.println(visited[M - 1][N - 1] ? "Yes" : "No");
	}
}