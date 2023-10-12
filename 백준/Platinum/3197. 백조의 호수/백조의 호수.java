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
	static int R, C, days;
	static char[][] map;
	static int[][][] visited;
	static int[] Pos, Pos2; // 백조 1, 백조 2

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Queue<int[]> queue = new LinkedList<>(); // 녹일 빙하 저장용 큐

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		visited = new int[R][C][];

		for (int i = 0; i < R; i++) { // 맵 생성하는 for문 : 백조 위치 기억해야함.
			String input = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = input.charAt(j);
				if (map[i][j] == 'L') {
					if (Pos == null) {
						Pos = new int[] { i, j };
					} else {
						Pos2 = new int[] { i, j };
					}
				} // 두 백조의 위치를 기억하는 if문
			}
		}

		int nr = 0;
		int nc = 0;
		int[] melt = new int[2];
		for (int i = 0; i < R; i++) {
			search: for (int j = 0; j < C; j++) {
				if (map[i][j] == 'X') {
					for (int k = 0; k < dr.length; k++) {
						nr = i + dr[k];
						nc = j + dc[k];
						if (nr >= 0 && nc >= 0 && nr < R && nc < C && (map[nr][nc] == '.' || map[nr][nc] == 'L')) {
							queue.add(new int[] { i, j });
							continue search;
						}
					}
				}
			}
		} // 녹일 빙하 기억하는 queue에 넣어주는 for문

		// 새로운 빙하가 녹을 때 마다, 새 빙하를 대표노드로 만들어 대표 노드를 계속 변경하기
		int idx = 0;
		BFS(Pos[0], Pos[1]);
		BFS(Pos2[0], Pos2[1]);

		while (!checkSame(find(Pos), find(Pos2))) {
			// 1. 검사하고
			// 2. 녹이고
			++days; // 하루가 간다,,
			int qSize = queue.size();
			for (int i = 0; i < qSize; i++) { // 큐에서 녹일 것들을 꺼낸다. => 다음 녹일걸 정해두고 => 전에 정해둔걸 녹인다

				// 큐에서 녹일 빙하를 꺼낸다
				int[] now = queue.poll(); // 녹일 것 꺼내기

				// 이미 녹아있는 빙하는 넘긴다
				if (map[now[0]][now[1]] == '.')
					continue;

				// 녹인다
				map[now[0]][now[1]] = '.'; // 녹이기

				// 녹은 빙하의 위치에서 사방을 탐색한다
				for (int j = 0; j < dr.length; j++) {
					nr = now[0] + dr[j];
					nc = now[1] + dc[j];
					if (nr >= 0 && nc >= 0 && nr < R && nc < C) {

						// 녹은 빙하의 위치 근처에서 다른 빙하를 만났다면
						if (map[nr][nc] == 'X') {

							// 큐에 해당 빙하를 넣어 다음날에 녹을 것이라는 것을 표시해준다
							queue.add(new int[] { nr, nc });
						}

						// 녹은 빙하의 위치 근처에 빙하가 아니라 물이나 백조를 만났다면 (백조도 물위에 떠있어서 사실상 물이다)
						else if (map[nr][nc] == '.' || map[nr][nc] == 'L') {
							if (visited[nr][nc] == null) {
								visited[nr][nc] = new int[] { nr, nc };
							}
							if (visited[now[0]][now[1]] == null) {
								visited[now[0]][now[1]] = new int[] { now[0], now[1] };
							}
							union(now, visited[nr][nc]);
						} // 물일 경우 union하기
					}
				} // 녹은 곳 주변을 탐색, 땅은 다음 녹이는 큐로, 물은 유니온
			} // 큐에서 녹은거 꺼내서 녹이는 for문
		} // while문
		System.out.println(days);
	}

	static void BFS(int r, int c) { // 초기 유니온 파인더 용 BFS
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { r, c });
		visited[r][c] = queue.peek();
		while (!queue.isEmpty()) {
			int now[] = queue.poll();
			for (int i = 0; i < dr.length; i++) {
				int nr = now[0] + dr[i];
				int nc = now[1] + dc[i];
				if (nr >= 0 && nc >= 0 && nr < R && nc < C) {
					if ((map[nr][nc] == '.' || map[nr][nc] == 'L') && visited[nr][nc] == null) {
						queue.add(new int[] { nr, nc });
						visited[nr][nc] = new int[] { nr, nc };
						union(visited[now[0]][now[1]], visited[nr][nc]);
					}
				}
			}
		}
	}

	static int[] find(int[] a) {
		if (a == null) {
			return a;
		}
		if (checkSame(a, visited[a[0]][a[1]])) {
			return a;
		}

		return visited[a[0]][a[1]] = find(visited[a[0]][a[1]]);
	}

	static void union(int[] b, int[] a) {
		a = find(a);
		b = find(b);

		if (!checkSame(a, b)) { // a의 대표노드와 b의 대표노드가 다를 때 만
			// b의 대표노드를 a의 대표노드로 바꾼다
			visited[b[0]][b[1]] = new int[] { a[0], a[1] };
		}
	}

	static boolean checkSame(int[] a, int[] b) {
		return (a[0] == b[0] && a[1] == b[1]);
	} // 두 좌표가 같을 때 true 리턴 - 두 좌표의 대표가 동일한지 찾는데 쓰임
}