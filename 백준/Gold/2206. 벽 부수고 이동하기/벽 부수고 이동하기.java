import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int r, c, isBreak, cnt;

	public Node(int r, int c, int isBreak, int cnt) {
		super();
		this.r = r;
		this.c = c;
		this.isBreak = isBreak;
		this.cnt = cnt;
	}

}

public class Main {

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int[][] map;
	static boolean[][][] visited;
	static int N, M;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열

		map = new int[N][M]; // 맵 생성
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(input.substring(j, j + 1));
			}
		} // 맵 생성하는 for 문

		visited = new boolean[N][M][2]; // 각 좌표에 대한 방문배열, 3차원의 배열은 벽을 부쉈는지[0] 안부쉈는지[1]에 따른 분기
		System.out.println(BFS(0, 0));
	} // main

	static int BFS(int r, int c) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(r, c, 0, 1));
		visited[r][c][0] = true;
		visited[r][c][1] = true;

		while (!queue.isEmpty()) {
			Node now = queue.poll();

			// 만약 최종 목적지에 도착했다면, 경로를 반환시켜 메서드를 끝낸다.
			if (now.r == N - 1 && now.c == M - 1)
				return now.cnt;

			for (int i = 0; i < dr.length; i++) {
				int nr = now.r + dr[i];
				int nc = now.c + dc[i];

				if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
					// 다음 좌표가 벽일때와 벽이 아닐때를 구분한다
					// 1. 벽이 아닐 때
					if (map[nr][nc] == 0) {
						// 해방 좌표에 도달해본적이 없을 때만!
						if (!visited[nr][nc][now.isBreak]) {
							// 벽을 부쉈는지 안부쉈는지에 대한 정보는 기존에 가지고 있던 것을 사용
							// cnt는 하나씩 늘려주며 거리를 넣어준다.
							queue.add(new Node(nr, nc, now.isBreak, now.cnt + 1));
							visited[nr][nc][now.isBreak] = true; // 벽 부순 정보에 따라 맞춰서 방문배열 기록
						}
					}
					// 2. 벽을 만났고, 기존에 벽을 부숴본 적이 없다면?
					else if (map[nr][nc] == 1) {

						// 벽을 부숴본적이 없고, 벽을 부순 적이 있다면, 해당 벽을 지나지 않았을 경우에만!
						if (now.isBreak == 0 && !visited[nr][nc][1]) {

							// 벽 부순 상태를 변경시키고, 방문배열도 맞춰서 기록
							queue.add(new Node(nr, nc, 1, now.cnt + 1)); // 벽 부숨 여부를 1로 기록
							visited[nr][nc][1] = true;
						}
					}
				}
			}
		}

		return -1;
	}
}
