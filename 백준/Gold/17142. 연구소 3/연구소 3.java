import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int N, L;
	static int[][] map;
	static boolean[][] visited;
	static List<int[]> list = new ArrayList<>();
	static boolean[] used;
	static int max = 99999;
	static int count0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// 1. 백트래킹으로 어떤 바이러스를 활성시킬 지 고른다
		// 2. BFS로 퍼트린다
		// 2-2. 다른 바이러스를 만나면, 해당 바이러스에 대한 기능 구현 따로
		// 3. for문으로 맵에 0이 있는지 없는지 검사
		// 3-1. 0이 있다면 -1, 없다면 max 출력
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken()); // 바이러스 수

		// 맵 생성
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 바이러스가 심어져 있는 좌표를 리스트에 담는다.
				if (map[i][j] == 2) {
					list.add(new int[] { i, j });
				} else if (map[i][j] == 0) {
					count0++;
				}
			}
		}
		used = new boolean[list.size()];
		select(0, 0);
		if (count0 == 0) {
			System.out.println(0);
		} else if (max == 99999) {
			System.out.println(-1);
		} else {
			System.out.println(max);
		}
	} // main

	static void select(int depth, int start) {
		if (depth == L) {
			BFS();
			return;
		}

		for (int i = start; i < list.size(); i++) {
			used[i] = true;
			select(depth + 1, i + 1);
			used[i] = false;
		}

	} // select

	static void BFS() {
		int singleMax = 0;
		int count00 = 0;
		Queue<int[]> queue = new LinkedList<>();
		visited = new boolean[N][N];
		int[][] nMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			nMap[i] = map[i].clone();
		}

		// 활성화 시킬 바이러스들을 큐에 넣는다.
		for (int i = 0; i < used.length; i++) {
			if (used[i]) {
				queue.add(list.get(i));
				visited[list.get(i)[0]][list.get(i)[1]] = true;
				nMap[list.get(i)[0]][list.get(i)[1]] = 0;
			}
		}
		while (!queue.isEmpty()) {
			// 큐에 들어있는 좌표를 뽑는다.
			int[] now = queue.poll();
			// 걸린 시간 초를 갱신시킨다
			singleMax = nMap[now[0]][now[1]] > singleMax ? nMap[now[0]][now[1]] : singleMax;

			for (int i = 0; i < 4; i++) {
				int nr = now[0] + dr[i];
				int nc = now[1] + dc[i];
				if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
					// 그냥 바닥일 때 (0일 때)
					if (nMap[nr][nc] == 0 && !visited[nr][nc]) {
						nMap[nr][nc] = nMap[now[0]][now[1]] + 1;
						queue.add(new int[] { nr, nc });
						visited[nr][nc] = true;
						count00++;
					} else if (nMap[nr][nc] == 2 && !visited[nr][nc]) {
						if (count00 == count0)
							break;
						// 비활설 바이러스일 때
						nMap[nr][nc] = nMap[now[0]][now[1]] + 1;
						visited[nr][nc] = true;
						queue.add(new int[] { nr, nc });
//						for (int j = 0; j < 4; j++) {
//							int jnr = nr + dr[j];
//							int jnc = nc + dc[j];
//							if (jnr >= 0 && jnc >= 0 && jnr < N && jnc < N) {
//								if (nMap[jnr][jnc] == 0) {
//									visited[jnr][jnc] = true;
//									queue.add(new int[] { jnr, jnc });
//									nMap[jnr][jnc] = nMap[now[0]][now[1]] + 2;
//									count00++;
//								}
//							}
//						}
					}
				}
			}
		} // while
//		for (int i = 0; i < )
		if (count0 == count00) {
			if (max > singleMax) {
				max = singleMax;
			}
		}
	} // BFS
}
