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
	static int idx;
	static int[][] minus;
	static boolean[][] visited;
	static int[][] map;
	static int N;
	static int M;
	static int cnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// BFS를 돌려 덩어리 수를 센다
		// 2가 아니면 빙하를 녹이고 다시 돌린다
		// 2일대 까지 반복한다
		// 다 녹았는데 2 안나오면 0 출력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 맵 생성

		int check = 0;
		int sum = 999;
		while (cnt < 2 && sum != 0) {
			visited = new boolean[N][M];
			minus = new int[N][M];
			sum = 0;
			cnt = 0;
			if (check == 1) {
				idx++;
				for (int k = 0; k < N; k++) {
					for (int l = 0; l < M; l++) {
						if (map[k][l] != 0) {
							for (int m = 0; m < 4; m++) {
								int nr = k + dr[m];
								int nc = l + dc[m];
								if (nr >= 0 && nc >= 0 && nr < N && nc < M && map[nr][nc] == 0) {
									minus[k][l]++;
								}
							}
						}
					}
				}
				for (int k = 0; k < N; k++) {
					for (int l = 0; l < M; l++) {
						if (map[k][l] != 0) {
							map[k][l] -= minus[k][l];
						}
						if (map[k][l] < 0) {
							map[k][l] = 0;
						}
					}
				}
			} // 빙하를 녹인다
//			for (int[] asdf : map) {
//				System.out.println(Arrays.toString(asdf));
//			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (!visited[i][j] && map[i][j] != 0) {
						cnt++;
						BFS(i, j);
					}
				}
			}
			if (cnt < 2) {
				check = 1;
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					sum += map[i][j];
				}
			}
//			System.out.println("sum : " + sum);
//			System.out.println("cnt : " + cnt);
		}
//		System.out.println("cnt : " + cnt);
		if (cnt >= 2) {
			System.out.println(idx);
		} else {
			System.out.println(0);
		}
	}

	static void BFS(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { r, c });
		visited[r][c] = true;
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nr = now[0] + dr[i];
				int nc = now[1] + dc[i];
				if (nr >= 0 && nc >= 0 && nr < N && nc < M && map[nr][nc] != 0) {
					if (!visited[nr][nc]) {
						visited[nr][nc] = true;
						queue.add(new int[] { nr, nc });
					}
				}
			}

		}

	}
}
