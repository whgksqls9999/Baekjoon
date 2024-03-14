import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

class Pipe {
	int r, c, type;

	public Pipe(int r, int c, int type) {
		super();
		this.r = r;
		this.c = c;
		this.type = type;
	}
}

public class Main {
	static int N;
	static int[][] arr;
	static int[][][] dp;
	static int[][][] dr = { { { 0 }, { 0, 1, 1 } }, { { 0 }, { 0, 1, 1 }, { 1 } }, { { 1 }, { 0, 1, 1 } } };
	static int[][][] dc = { { { 1 }, { 1, 1, 0 } }, { { 1 }, { 1, 1, 0 }, { 0 } }, { { 0 }, { 1, 1, 0 } } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		dp = new int[3][N][N];
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(DFS(new Pipe(0, 1, 0)));

	}

	public static int DFS(Pipe pipe) {
		int r = pipe.r;
		int c = pipe.c;
		int type = pipe.type;

		if (r == N - 1 && c == N - 1) {
			dp[type][r][c] = 1;
		}

		if (dp[type][r][c] != 0) {
			return dp[type][r][c];
		}

		int answer = 0;

		int[][] ar = dr[pipe.type];
		int[][] ac = dc[pipe.type];

		for (int i = 0; i < ar.length; i++) {
			boolean check = true;
			for (int j = 0; j < ar[i].length; j++) {
				int nr = r + ar[i][j];
				int nc = c + ac[i][j];

				if (!(nr >= 0 && nr < N && nc >= 0 && nc < N) || arr[nr][nc] == 1) {
					check = false;
					break;
				}
			}

			int nextType = 0;
			int nextR = r;
			int nextC = c;
			switch (pipe.type) {
			case 0:
				if (i == 0) {
					nextType = 0;
					nextC++;
				} else if (i == 1) {
					nextType = 1;
					nextR++;
					nextC++;
				}
				break;
			case 1:
				if (i == 0) {
					nextType = 0;
					nextC++;
				} else if (i == 1) {
					nextType = 1;
					nextR++;
					nextC++;
				} else if (i == 2) {
					nextType = 2;
					nextR++;
				}
				break;
			case 2:
				if (i == 0) {
					nextType = 2;
					nextR++;
				} else if (i == 1) {
					nextType = 1;
					nextR++;
					nextC++;
				}
				break;
			}

			if (check) {
				answer += DFS(new Pipe(nextR, nextC, nextType));
			}
		}

		return dp[type][r][c] = answer;
	}
}