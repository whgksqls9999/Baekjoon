import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, r, c, K;
	static int[][] map;

	// 동, 서, 북, 남
	static int[] dc = new int[] { 1, -1, 0, 0, };
	static int[] dr = new int[] { 0, 0, -1, 1 };

	// lr : {상, 서, 하, 동}
	// ud : {상, 북, 하, 남}
	static int[][] lrud = new int[][] { { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };

	static int[] pos;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// 변수 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		pos = new int[] { r, c };

//		lrud[0][2] = map[pos[0]][pos[1]];
//		lrud[1][2] = map[pos[0]][pos[1]];

		// 지도 생성
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; ++i) {
			int input = Integer.parseInt(st.nextToken());

			int nr = pos[0] + dr[input - 1];
			int nc = pos[1] + dc[input - 1];
			if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
				turn(input);

				if (map[nr][nc] == 0) {
					map[nr][nc] = lrud[(input ^ 8) / 11][2];
				} else {
					lrud[(input ^ 8) / 11][2] = map[nr][nc];
					map[nr][nc] = 0;
				}
				pos[0] = nr;
				pos[1] = nc;

				lrud[((input ^ 8) / 11) ^ 1][0] = lrud[(input ^ 8) / 11][0];
				lrud[((input ^ 8) / 11) ^ 1][2] = lrud[(input ^ 8) / 11][2];

				sb.append(lrud[(input ^ 8) / 11][0]).append("\n");
			}
		}

		System.out.println(sb);

	}

	static void turn(int dir) {
		int tmp = 0;
		switch (dir) {
		case 1:
			tmp = lrud[0][0];
			for (int i = 0; i < 3; ++i) {
				lrud[0][i] = lrud[0][i + 1];
			}
			lrud[0][3] = tmp;
			break;
		// 서
		case 2:
			tmp = lrud[0][3];
			for (int i = 3; i > 0; --i) {
				lrud[0][i] = lrud[0][i - 1];
			}
			lrud[0][0] = tmp;
			break;
		// 북
		case 3:
			tmp = lrud[1][3];
			for (int i = 3; i > 0; --i) {
				lrud[1][i] = lrud[1][i - 1];
			}
			lrud[1][0] = tmp;
			break;
		// 남
		case 4:
			tmp = lrud[1][0];
			for (int i = 0; i < 3; ++i) {
				lrud[1][i] = lrud[1][i + 1];
			}
			lrud[1][3] = tmp;
			break;
		}
	}
}