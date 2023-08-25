import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int N, M, K;
	static int[][] arr;
	static boolean[][] visited;
	static int size;
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()); // 테케 입력받는 br
		StringBuilder sb = new StringBuilder();
		Queue<int[]> queue = new LinkedList<>();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			arr[n - 1][r - 1] = 1;
			queue.add(new int[] { n - 1, r - 1 });
		}
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			if (!visited[now[0]][now[1]]) {
				size = 0;
				DFS(now[0], now[1]);
			}
		}
		System.out.println(max);
	}

	static void DFS(int r, int c) {
		if (visited[r][c]) {
			return;
		}
		size++;
		max = Math.max(size, max);

		visited[r][c] = true;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
				if (!visited[nr][nc] && arr[nr][nc] == 1) {
					DFS(nr, nc);
				}
			}
		}
	}
}
