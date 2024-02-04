import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int N, M;
	public static int[][] arr;
	public static Queue<int[]> queue = new ArrayDeque<>();
	public static int[] dr = new int[] { -1, -1, 0, 1, 1, 1, 0, -1 };
	public static int[] dc = new int[] { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1) {
					queue.add(new int[] { i, j });
				}
			}
		}
		int answer = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int cur = -1;
				if (arr[i][j] == 0) {
					cur = BFS(new int[] { i, j });
				} else if (arr[i][j] == 1) {
					cur = 0;
				}
				answer = Math.max(answer, cur);
			}
		}

		System.out.println(answer);
	}

	public static int BFS(int[] input) {
		Queue<int[]> bfsQueue = new ArrayDeque<>();
		int[][] visited = new int[N][M];

		int answer = Integer.MAX_VALUE;

		bfsQueue.add(input);
		while (!bfsQueue.isEmpty()) {
			int[] cur = bfsQueue.poll();

			for (int i = 0; i < 8; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
					if (visited[nr][nc] == 0) {
						bfsQueue.add(new int[] { nr, nc });
						visited[nr][nc] = visited[cur[0]][cur[1]] + 1;
						if (arr[nr][nc] == 1) {
							answer = Math.min(answer, visited[nr][nc]);
						}
					}
				}
			}
		}

		return answer;
	}
}