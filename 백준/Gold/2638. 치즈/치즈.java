import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static Queue<int[]> queue = new LinkedList<>();
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 입력 받기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 맵 생성
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 외부 공기, 내부 공기 나누기
		BFS(0, 0);

		// 다음에 녹는 치즈(근처에 공기가 2칸인 치즈) 찾기
		FindCheese();

		int times = 0;
		while (!queue.isEmpty()) {
			++times;
			int qSize = queue.size(); // 현재 녹일 치즈의 개수

			for (int i = 0; i < qSize; i++) {
				int[] now = queue.poll(); // 녹일 치즈의 위치를 받아옴
				map[now[0]][now[1]] = 2; // 치즈 녹이기
				for (int j = 0; j < dr.length; j++) {
					int nr = now[0] + dr[j];
					int nc = now[1] + dc[j];
					if (nr >= 0 && nc >= 0 && nr < N && nc < M && map[nr][nc] == 0) {
						BFS(nr, nc);
					}
				}
			} // for문

			FindCheese(); // 다음 녹일 치즈 찾기

		} // while문

		System.out.println(times);
	} // main

	static void FindCheese() {
		// 다음에 녹는 치즈(근처에 공기가 2칸인 치즈) 찾기

		// 맵 탐색하며 치즈(1) 찾기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					int cnt = 0; // 치즈 근처의 공기 수를 체크할 변수
					// 치즈 근처 4방향을 탐색하여 공기(0)의 개수를 파악
					for (int k = 0; k < dr.length; k++) {
						int nr = i + dr[k];
						int nc = j + dc[k];
						if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
							if (map[nr][nc] == 2) {
								cnt++;
							}
						}
					} // delta for문
						// 주위에 공기가 2칸 이상일 때 녹일 치즈 목록(큐)에 넣음
					if (cnt >= 2) {
						queue.add(new int[] { i, j });
					}
				} // if문
			}
		}
	} // findCheese

	static void BFS(int r, int c) {
		Queue<int[]> queue2 = new LinkedList<>();
		queue2.add(new int[] { r, c });
		map[r][c] = 2;
		while (!queue2.isEmpty()) {
			int now[] = queue2.poll();
			for (int i = 0; i < dr.length; i++) {
				int nr = now[0] + dr[i];
				int nc = now[1] + dc[i];
				if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
					if (map[nr][nc] == 0) {
						map[nr][nc] = 2;
						queue2.add(new int[] { nr, nc });
					}
				}
			}
		}
	}
}