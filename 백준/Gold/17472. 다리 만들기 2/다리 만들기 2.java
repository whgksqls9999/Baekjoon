import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static boolean[][] visited;

	// prim
	static class Edge {
		int dest, dist;

		public Edge(int dest, int dist) {
			super();
			this.dest = dest;
			this.dist = dist;
		}

		@Override
		public String toString() {
			return "Edge [dest=" + dest + ", dist=" + dist + "]";
		}

	}

	static boolean[] connected;
	static List<Edge>[] nodes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		List<int[]> list = new ArrayList<>();
		// 주어진 지도를 통해서 다리 간 연결 정보를 얻어와야한다
		// 각 섬과 섬을 잇는 양방향 간선정보
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 맵 생성하기
		map = new int[N][M];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());

				// 육지일 경우 해당 좌표를 큐에 저장해둔다
				if (map[i][j] == 1) {
					list.add(new int[] { i, j });
				}
			}
		} // for

		visited = new boolean[N][M];

		// 육지 좌표를 꺼내어 BFS를 통해 섬에 번호를 붙여준다
		int cnt = 0;
		int j = 1;
		for (int i = 0; i < list.size(); ++i) {
			boolean check = false;
			int[] tmp = list.get(i);
			if (!visited[tmp[0]][tmp[1]] && map[tmp[0]][tmp[1]] == 1) {
				check = BFS(tmp[0], tmp[1], j);
				++cnt;
			}
			if (check) {
				++j;
			}

		}

		// 인접 리스트 만들기
		nodes = new ArrayList[cnt + 1];
		for (int i = 1; i < nodes.length; i++) {
			nodes[i] = new ArrayList<>();
		}

		// 각 섬들을 연결하는 길이가 2 이상인 간선 찾아 인접리스트 만들기

		for (int i = 0; i < list.size(); i++) {
			int[] tmp = list.get(i);
			int r = tmp[0];
			int c = tmp[1];
			int start = map[r][c];

			for (int k = 0; k < 4; k++) {
				int end = -1;
				int length = 0;
				int nr = r + dr[k];
				int nc = c + dc[k];
				if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
					if (map[nr][nc] == 0) {
						while (nr >= 0 && nc >= 0 && nr < N && nc < M) {
							if (map[nr][nc] != 0) {
								end = map[nr][nc];
								break;
							}
							nr += dr[k];
							nc += dc[k];
							++length;
						} // while
						if (end != -1 && length >= 2) {
							nodes[start].add(new Edge(end, length));
						}
					}
				}
			} // 4방탐색 for
		} // 간선 찾는 for

		// prim 알고리즘
		Queue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {

			@Override
			public int compare(Main.Edge o1, Main.Edge o2) {
				return o1.dist - o2.dist;
			}
		});

		// 방문배열 만들기
		connected = new boolean[cnt + 1];

		pq.add(new Edge(1, 0));

		int minDist = 0;
		int connect = 0;

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			int now = cur.dest;

			if (connected[now])
				continue;
			minDist += cur.dist;
			connected[now] = true;

			++connect;
			if (connect == cnt)
				break;

			for (int i = 0; i < nodes[now].size(); i++) {
				Edge next = nodes[now].get(i);
				if (!connected[next.dest]) {
					pq.add(next);
				}
			}
		}

		if (connect == cnt) {
			System.out.println(minDist);
		} else {
			System.out.println(-1);
		}
//
//		for (int i = 0; i < map.length; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
//		for (int i = 1; i < nodes.length; i++) {
//			System.out.println(nodes[i]);
//		}
//		System.out.println(connect);
//		System.out.println(cnt);
//		System.out.println(minDist);
	} // main

	static boolean BFS(int r, int c, int num) {
		Queue<int[]> queue = new LinkedList<>();

		queue.add(new int[] { r, c });

		// 섬에 번호 붙여주기
		map[r][c] = num;

		// 방문처리
		visited[r][c] = true;

		while (!queue.isEmpty()) {
			int[] now = queue.poll();

			// 4방탐색
			for (int i = 0; i < 4; i++) {
				int nr = now[0] + dr[i];
				int nc = now[1] + dc[i];

				// 유효성 검사
				if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
					if (!visited[nr][nc] && map[nr][nc] == 1) {
						queue.add(new int[] { nr, nc });

						// 섬에 번호 붙여주기
						map[nr][nc] = num;

						// 방문처리
						visited[nr][nc] = true;
					}
				}
			}
		}

		// BFS가 작동했다면(새로운 육지에 번호를 붙였다면) true 반환
		return true;
	}
}