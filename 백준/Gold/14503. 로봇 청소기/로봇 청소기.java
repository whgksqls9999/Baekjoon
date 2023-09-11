import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Robot {
	int r, c, dir;

	public Robot(int r, int c, int dir) {
		super();
		this.r = r;
		this.c = c;
		this.dir = dir;
	}

}

public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int N, M;
	static int[][] map;
	static int cnt;
	static Robot robot;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		robot = new Robot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()));
		// 현재 위치, 방향값을 가지고 있는 로봇 인스턴스 생성

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 0 : 청소되지 않은 빈 칸
				// 1 : 벽
			}
		} // 맵 생성하기

		clean(robot.r, robot.c);
		System.out.println(cnt);

	} // main
		// 1. 현재 칸이 청소되지 않음 -> 청소함
		// 2. 주변 4칸이 모두 청소됨
		// 후진 가능하면 후진 후 1
		// 후진 불가능 => 작동 멈춤
		// 3. 주변 4칸중 청소되지 않은 칸 있을 경우
		// 반시계 방향 90도 회전
		// 앞이 청소 안한 칸이면 한칸 전진

	static void clean(int r, int c) {

		// 1, 아직 청소되지 않은 칸이라면, 청소한다
		if (map[r][c] == 0) {
			map[r][c] = 2;
			cnt++;
		}
		// 2. 주변에 청소되지 않은 칸이 있는지 없는지 확인
		int check = 0;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
				if (map[nr][nc] == 0) {
					check++;
				}
			}
		}

		// 2-1. 청소되지 않은 칸이 없다면
		if (check == 0) {
			int nr = r - dr[robot.dir];
			int nc = c - dc[robot.dir];
			if (nr >= 0 && nc >= 0 && nr < N && nc < M && map[nr][nc] != 1) {
				clean(nr, nc);
			} else {
				return;
			}
		}

		// 2-2. 청소되지 않은 칸이 있다면
		if (check > 0) {
			// 시계방향으로 회전하기
			int nr = 0;
			int nc = 0;
			do {
				robot.dir = (robot.dir + 3) % 4;
				nr = r + dr[robot.dir];
				nc = c + dc[robot.dir];
			} while (nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] == 1 || map[nr][nc] == 2);
			clean(nr, nc);
		}
	}
}
