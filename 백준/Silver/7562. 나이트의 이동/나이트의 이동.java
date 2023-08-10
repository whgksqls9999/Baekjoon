import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { -2, -2, -1, 1, 2, 2, 1, -1 };
	static int[] dc = { -1, 1, 2, 2, 1, -1, -2, -2 };

	static int[][] map; // 체스판
	static int idx = 0;
	static int[] start;
	static int[] end;
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];

			st = new StringTokenizer(br.readLine());
			int[] start = { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
			st = new StringTokenizer(br.readLine());
			int[] end = { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };

			BFS(start, end);
		}
	}

	static void BFS(int[] pos, int[] end) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(pos);
		map[pos[0]][pos[1]] = 1;
		int[] now;
		while (!queue.isEmpty()) {
			now = queue.poll();
			// 뺀다, 연결된 다음 좌표들의 유효성 검사 후 큐에 넣어준다
			for (int i = 0; i < dr.length; i++) {
				int nr = now[0] + dr[i];
				int nc = now[1] + dc[i];
				if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
					if (map[nr][nc] == 0) { // 방문하지 않았다면
						queue.add(new int[] { nr, nc });
						map[nr][nc] = map[now[0]][now[1]] + 1;
					}
				}
			}

		}
		System.out.println(map[end[0]][end[1]] - 1);

	}
}