import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().trim().toCharArray();
		}

		int answer = 1;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'L') {
					boolean[][] visited = new boolean[N][M];
					answer = Math.max(answer, BFS(i, j, visited));
				}
			}
		}
		System.out.println(answer);
	}

	public static int BFS(int r, int c, boolean[][] visited) {
		Queue<int[]> queue = new ArrayDeque<>();

		queue.add(new int[] { r, c, 0 });
		visited[r][c] = true;

		int[] last = new int[2];

		int[] dr = new int[] { -1, 0, 1, 0 };
		int[] dc = new int[] { 0, 1, 0, -1 };

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			last = cur;

			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
					if (!visited[nr][nc] && map[nr][nc] == 'L') {

						queue.add(new int[] { nr, nc, cur[2] + 1 });
						visited[nr][nc] = true;
					}
				}
			}
		}
		return last[2];

	}

//	public static int BFS(int r, int c, int[][] visited) {
//		Queue<int[]> queue = new ArrayDeque<>();
//
//		queue.add(new int[] { r, c, 0 });
//		visited[r][c] = 0;
//
//		int[] last = new int[2];
//
//		int[] dr = new int[] { -1, 0, 1, 0 };
//		int[] dc = new int[] { 0, 1, 0, -1 };
//
//		while (!queue.isEmpty()) {
//			int[] cur = queue.poll();
//			last = cur;
//
//			for (int i = 0; i < 4; i++) {
//				int nr = cur[0] + dr[i];
//				int nc = cur[1] + dc[i];
//
//				if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
//					if ((visited[nr][nc] == -1 && map[nr][nc] == 'L')) {
//
//						queue.add(new int[] { nr, nc, cur[2] + 1 });
//						visited[nr][nc] = 0;
//					}
//				}
//			}
//		}
//
//		visited[last[0]][last[1]] = 1;
//		queue.add(new int[] { last[0], last[1], 0 });
//
//		while (!queue.isEmpty()) {
//			int[] cur = queue.poll();
//			last = cur;
//			for (int i = 0; i < 4; i++) {
//				int nr = cur[0] + dr[i];
//				int nc = cur[1] + dc[i];
//
//				if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
//					if (visited[nr][nc] == 0 && map[nr][nc] == 'L') {
//						queue.add(new int[] { nr, nc, cur[2] + 1 });
//						visited[nr][nc] = 1;
//					}
//				}
//			}
//		}
//
//		return last[2];
//	}
}