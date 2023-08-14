import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 델타
	static int[] dh = { 0, 0, 0, 0, 1, -1 };
	static int[] dr = { -1, 0, 1, 0, 0, 0 };
	static int[] dc = { 0, 1, 0, -1, 0, 0 };
	// 상자의 크기와 관련된 변수
	static int M;
	static int N;
	static int H;
	static boolean[][][] visited;
	static int[][][] arr;
	static int idx;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		M = Integer.parseInt(st.nextToken()); // 열
		N = Integer.parseInt(st.nextToken()); // 행
		H = Integer.parseInt(st.nextToken()); // 높이
		arr = new int[H][N][M];
		visited = new boolean[H][N][M];
		for (int k = 0; k < H; k++) {
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					arr[k][i][j] = Integer.parseInt(st.nextToken());
				}
			}
		} // 상자 채우기
		BFS();

	}// main

	static void BFS() {
		int a = 0;
		Queue<int[]> queue = new LinkedList<>();
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if (arr[i][j][k] == 1) {
						queue.add(new int[] { i, j, k });
						visited[i][j][k] = true;
					}
					if (arr[i][j][k] == -1) {
						a++;
					}

				}
			}
		} // 3차원에서 여러개일 수 있는 토마토를 찾는다.

		if (queue.size() + a == H * N * M) {
			System.out.println(0);
			return;
		}
		int[] now;

		while (!queue.isEmpty()) {
			now = queue.poll();
			for (int i = 0; i < 6; i++) {
				int nh = now[0] + dh[i];
				int nr = now[1] + dr[i];
				int nc = now[2] + dc[i];
				if (nh >= 0 && nh < H && nr >= 0 && nr < N && nc >= 0 && nc < M) {
					if (arr[nh][nr][nc] == 0 && !visited[nh][nr][nc]) {
						queue.add(new int[] { nh, nr, nc });
						arr[nh][nr][nc] = arr[now[0]][now[1]][now[2]] + 1;
						visited[nh][nr][nc] = true;
						idx = arr[now[0]][now[1]][now[2]] + 1;
					}
				}
			}
		}
		boolean check = true;
		outer: for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if (arr[i][j][k] == 0) {
						check = false;
						break outer;
					}
				}
			}
		} // 안 익은 토마토가 있는지 검사
		if (check) {
			System.out.println(idx - 1);
		} else {
			System.out.println(-1);
		}
	}
}