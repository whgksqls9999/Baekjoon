import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R, C;
	static char[][] map;
	static int[][] visited;
	static int[] start;
	static List<int[]> fires;
	static boolean escape;

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		R = Integer.parseInt(st.nextToken(" "));
		C = Integer.parseInt(st.nextToken(" "));

		map = new char[R][C];

		fires = new ArrayList<>();

		for (int i = 0; i < R; i++) {
			String input = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = input.charAt(j);
				if (map[i][j] == 'J') {
					start = new int[] { i, j };
				} else if (map[i][j] == 'F') {
					fires.add(new int[] { i, j });
				}
			}
		}
		BFS();

		System.out.println("IMPOSSIBLE");

	}

	static void BFS() {
		Queue<int[]> queue = new LinkedList<>();

		visited = new int[R][C];

		for (int[] i : fires) {
			queue.add(i);
		}
		queue.add(start);
		visited[start[0]][start[1]] = 1;

		while (!queue.isEmpty()) {
			int[] now = queue.poll();

			if (map[now[0]][now[1]] == 'J' && (now[0] == 0 || now[1] == 0 || now[0] == R - 1 || now[1] == C - 1)) {
				System.out.println(visited[now[0]][now[1]]);
				System.exit(0);
			}

			for (int i = 0; i < 4; i++) {
				int nr = now[0] + dr[i];
				int nc = now[1] + dc[i];

				if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
					if (map[nr][nc] == '.') {
						map[nr][nc] = map[now[0]][now[1]];
						visited[nr][nc] = visited[now[0]][now[1]] + 1;
						queue.add(new int[] { nr, nc });
					} else if (map[now[0]][now[1]] == 'F' && map[nr][nc] == 'J'
							&& visited[nr][nc] == 0) {
						map[nr][nc] = 'F';
					}
				}
			}
		}
	}

}
