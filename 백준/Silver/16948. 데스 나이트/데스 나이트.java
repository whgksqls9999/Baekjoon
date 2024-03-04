import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		int[] knight = new int[2];
		for (int i = 0; i < 2; i++) {
			knight[i] = Integer.parseInt(st.nextToken());
		}

		int[] target = new int[2];
		for (int i = 0; i < 2; i++) {
			target[i] = Integer.parseInt(st.nextToken());
		}

		System.out.print(BFS(knight, target, N));
	}

	public static int BFS(int[] knight, int[] target, int N) {
		int r = knight[0], c = knight[1];
		int fr = target[0], fc = target[1];

		int[] dr = new int[] { -2, -2, 0, 0, 2, 2 };
		int[] dc = new int[] { -1, 1, -2, 2, -1, 1 };

		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(knight);

		int[][] visited = new int[N][N];

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			for (int i = 0; i < dr.length; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];

				if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
					if (visited[nr][nc] == 0) {
						queue.add(new int[] { nr, nc });
						visited[nr][nc] = visited[cur[0]][cur[1]] + 1;
					}
				}
			}
		}

		return visited[target[0]][target[1]] == 0 ? -1 : visited[target[0]][target[1]];
	}
}