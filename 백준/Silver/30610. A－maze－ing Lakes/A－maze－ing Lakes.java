import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] arr;
	static boolean[][] visited;

	static int[] dr = new int[] { -1, 0, 1, 0 };
	static int[] dc = new int[] { 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

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

		int cnt = 0;

		List<Integer> size = new ArrayList<>();

		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (arr[i][j] == 1 && !visited[i][j]) {
					++cnt;
					size.add(BFS(i, j));
				}
			}
		}

		Collections.sort(size);

		sb.append(cnt).append("\n");
		for (int i : size) {
			sb.append(i).append(" ");
		}

		System.out.print(sb);
	}

	static int BFS(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();

		visited[r][c] = true;
		queue.add(new int[] { r, c });
		int size = 1;

		while (!queue.isEmpty()) {
			int[] now = queue.poll();

			for (int i = 0; i < dr.length; ++i) {
				int nr = now[0] + dr[i];
				int nc = now[1] + dc[i];

				if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
					if (arr[nr][nc] == 1 && !visited[nr][nc]) {
						queue.add(new int[] { nr, nc });
						visited[nr][nc] = true;
						++size;
					}
				}
			}
		}

		return size;
	}
}