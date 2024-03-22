import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

class State {
	int r, c, cnt;

	public State(int r, int c, int cnt) {
		super();
		this.r = r;
		this.c = c;
		this.cnt = cnt;
	}

}

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.print(solution());
	}

	public static int solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		char[][] map = new char[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		return BFS(0, 0, N, map);
	}

	public static int BFS(int r, int c, int N, char[][] map) {
		Queue<State> queue = new ArrayDeque<>();

		queue.add(new State(r, c, 0));

		int[] dr = new int[] { -1, 0, 1, 0 };
		int[] dc = new int[] { 0, 1, 0, -1 };
		int[][] visited = new int[N][N];
		for (int[] i : visited) {
			Arrays.fill(i, Integer.MAX_VALUE);
		}

		while (!queue.isEmpty()) {
			State cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];

				if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
					if (visited[nr][nc] > cur.cnt) {
						queue.add(new State(nr, nc, map[nr][nc] == '0' ? cur.cnt + 1 : cur.cnt));
						visited[nr][nc] = map[nr][nc] == '0' ? cur.cnt + 1 : cur.cnt;
					}
				}
			}
		}

		return visited[N - 1][N - 1];

	}
}