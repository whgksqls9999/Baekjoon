import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { -1, 0, 1, 0, 0, 0 };
	static int[] dc = { 0, 1, 0, -1, 0, 0 };
	static int[] dz = { 0, 0, 0, 0, 1, -1 };
	static int[][][] visited;
	static char[][][] map;
	static int L = 1;
	static int R = 1;
	static int C = 1;
	static int[] sPos;
	static int[] ePos;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			if (L == 0 && R == 0 && C == 0) {
				break;
			}
			visited = new int[L][R][C];
			map = new char[L][R][C];
			for (int i = 0; i < L; i++) {
				for (int j = 0; j < R; j++) {
					map[i][j] = br.readLine().toCharArray();
					for (int k = 0; k < C; k++) {
						if (map[i][j][k] == 'S') {
							sPos = new int[] { i, j, k };
						}
						if (map[i][j][k] == 'E') {
							ePos = new int[] { i, j, k };
						}
					}
				}
				br.readLine();
			} // 맵 설정
			BFS(sPos);
			if (visited[ePos[0]][ePos[1]][ePos[2]] == 0) {
				System.out.println("Trapped!");
			} else {
				System.out.printf("Escaped in %d minute(s).\n", visited[ePos[0]][ePos[1]][ePos[2]] - 1);
			}
		}
	} // main

	static void BFS(int[] start) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(start);
		visited[start[0]][start[1]][start[2]] = 1;
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			for (int i = 0; i < 6; i++) {
				int nz = now[0] + dz[i];
				int nr = now[1] + dr[i];
				int nc = now[2] + dc[i];
				if (nc >= 0 && nr >= 0 && nz >= 0 && nc < C && nr < R && nz < L) {
					if (visited[nz][nr][nc] == 0 && map[nz][nr][nc] != '#') {
						queue.add(new int[] { nz, nr, nc });
						visited[nz][nr][nc] = visited[now[0]][now[1]][now[2]] + 1;
					}
				}
			}
		}
	}

}