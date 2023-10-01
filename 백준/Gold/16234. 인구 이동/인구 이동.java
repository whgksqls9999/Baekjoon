import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		Node rep;
		int size, pop, r, c;
		int repPop;

		public Node(int size, int pop, int r, int c) {
			super();
			this.rep = this;
			this.size = size;
			this.pop = pop;
			this.r = r;
			this.c = c;
			this.repPop = pop;
		}

		@Override
		public String toString() {
			return Integer.toString(size) + " : " + Integer.toString(pop) + " : " + Integer.toString(repPop);
		}

	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int N, L, R;
	static Node[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new Node[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = new Node(1, Integer.parseInt(st.nextToken()), i, j);
			}
		}

		int day = 0;
		for (; true; ++day) {
			visited = new boolean[N][N];
			if (!popDiffCheck(0, 0)) {
				break;
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						BFS(i, j);
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					Node now = map[i][j];
					find(now);
					now.pop = now.rep.repPop / now.rep.size;

				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					Node now = map[i][j];

					now.repPop = now.pop;
					now.rep = now;
					now.size = 1;
				}
			}

		}
		System.out.println(day);
	}

	static boolean popDiffCheck(int r, int c) {
		Queue<Node> queue = new LinkedList<>();

		boolean[][] checked = new boolean[N][N];

		boolean check = false;

		queue.add(map[r][c]);

		checked[r][c] = true;

		while (!queue.isEmpty()) {
			Node now = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];
				if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
					Node next = map[nr][nc];

					if (!checked[nr][nc]) {
						if (Math.abs(now.pop - next.pop) >= L && (Math.abs(now.pop - next.pop) <= R)) {
							return true;
						} else {
							checked[nr][nc] = true;
							queue.add(map[nr][nc]);
						}
					} else {
						if (Math.abs(now.pop - next.pop) >= L && (Math.abs(now.pop - next.pop) <= R)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	static void BFS(int r, int c) {
		Queue<Node> queue = new LinkedList<>();

		int check = 0;

		queue.add(map[r][c]);

		visited[r][c] = true;

		while (!queue.isEmpty()) {
			Node now = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];
				if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
					Node next = map[nr][nc];

					if (!visited[nr][nc] && Math.abs(now.pop - next.pop) >= L && (Math.abs(now.pop - next.pop) <= R)) {
						queue.add(next);
						visited[nr][nc] = true;

						Node tmpA = find(now);
						Node tmpB = find(next);

						union(tmpA, tmpB);
					}
				}
			}
		}
	}

	static void union(Node a, Node b) {
		if (a != b) {
			a.rep = b;
			b.size += a.size;
			b.repPop += a.repPop;
		}
	}

	static Node find(Node a) {
		if (a.rep == a) {
			return a;
		}
		return a.rep = find(a.rep);
	}
}