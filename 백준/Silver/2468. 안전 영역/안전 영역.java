import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int idx;
	static boolean[][] visited;
	static int[][] map;
	static int N;
	static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 맵 입력받기
		int max = 0;
		for (int k = 0; k <= 100; k++) {
			int cnt = 0;
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && map[i][j] > k) {
						cnt++;
						BFS(i, j, k);
					}
				} // 방문한 적 없는 좌표라면 BFS 실행
			}
			max = cnt > max ? cnt : max;
		}
		System.out.println(max);

	}

	static void BFS(int r, int c, int rain) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { r, c });
		int[] now;
		while (!queue.isEmpty()) {
			now = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nr = now[0] + dr[i];
				int nc = now[1] + dc[i];
				if (nr >= 0 && nc >= 0 && nr < N && nc < N && !visited[nr][nc]) {
					if (map[nr][nc] > rain) {
						visited[nr][nc] = true;
						queue.add(new int[] { nr, nc });
					}
				}
			}
		}
	}
}