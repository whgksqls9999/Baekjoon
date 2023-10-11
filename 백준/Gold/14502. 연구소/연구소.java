import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static List<int[]> virusPos = new ArrayList<>();
	static int[][] map;
	static int N, M;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int maxSafe;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// 입력 및 맵 생성
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					virusPos.add(new int[] { i, j }); // 바이러스 좌표들 미리 넣어두기
				}
			}
		}

		DFS(0);
		System.out.println(maxSafe);

		// 1. 백트래킹을 통해 맵에 벽 3개를 생성
		// 2. 벽이 추가된 맵에서 바이러스를 퍼트려본다.
		// 3. 안전 영역의 넓이도 구해본다
		// 4. 다시 1번으로 돌아가 새로운 벽을 만든후 반복한다

	} // main

	static void DFS(int wall) {
		if (wall == 3) { // 벽 3개를 설치했으면 bfs 돌려 확인
			BFS();
			return;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) { // 평지일 경우에 벽 설치
					map[i][j] = 1;
					DFS(wall + 1); // 다음 벽 설치
					map[i][j] = 0; // 원상 복구
				}
			}
		}
	} // DFS

	static void BFS() { // 바이러스 퍼뜨리는 BFS
		int[][] tmpMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			tmpMap[i] = map[i].clone();
		}

		Queue<int[]> queue = new LinkedList<>();
		for (int i = 0; i < virusPos.size(); i++) {
			queue.add(virusPos.get(i));
		}
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nr = now[0] + dr[i];
				int nc = now[1] + dc[i];
				if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
					if (tmpMap[nr][nc] == 0) {
						tmpMap[nr][nc] = 2;
						queue.add(new int[] { nr, nc });
					}
				}
			}
		} // while

		check(tmpMap);

	}

	static void check(int[][] tmpmap) {
		int safeCnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tmpmap[i][j] == 0) {
					safeCnt++;
				}
			}
		}
		maxSafe = Math.max(safeCnt, maxSafe);
	}
}
