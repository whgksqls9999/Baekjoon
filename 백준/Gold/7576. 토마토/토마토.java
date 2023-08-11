import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static boolean[][] visited;
	static int[][] map;
	static int N;
	static int M;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		visited = new boolean[N][M];
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 맵 생성하는 for

		BFS();
		boolean check = true;
		outer: for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					check = false;
					break outer;
				}
			}
		}
		if (check) {
			System.out.println(cnt);
		} else {
			System.out.println(-1);
		}
	}

	static void BFS() {
		Queue<int[]> queue = new LinkedList<>();
		// 출발점이 여러개일 수 있으니, for문으로 출발점을 찾아 큐에 넣어준다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					queue.add(new int[] { i, j });
				}
			}
		} // 출발점을 인식해 큐에 넣어주는 for
		int startP = queue.size(); // 출발점의 수를 기록한다.
		while (!queue.isEmpty()) {
			for (int i = 0; i < startP; i++) {
				if (!queue.isEmpty()) {
					int[] now = queue.poll();
					for (int j = 0; j < 4; j++) {
						int nr = now[0] + dr[j];
						int nc = now[1] + dc[j];
						if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
							if (map[nr][nc] == 0) {
								queue.add(new int[] { nr, nc });
								map[nr][nc] = map[now[0]][now[1]] + 1;
								cnt = map[now[0]][now[1]];
							}
						}
					}
				} else {
					break;
				}
			} // 출발점의 수 만큼 싸이클 돌기
		}

	}
}
