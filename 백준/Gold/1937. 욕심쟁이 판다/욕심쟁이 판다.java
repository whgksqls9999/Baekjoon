import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int[][] arr;
	static int[][] dp;

	static int N;
	static int ans = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		arr = new int[N][N];
		dp = new int[N][N];

		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (dp[i][j] == 0) {
					DFS(i, j);
				}
			}
		}
        
        System.out.println(ans);
	}

	public static void DFS(int r, int c) {
		++dp[r][c];

		for (int i = 0; i < 4; ++i) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
				if (arr[nr][nc] > arr[r][c]) {
					if (dp[nr][nc] == 0) {
						DFS(nr, nc);
					}
					if(dp[nr][nc] >= dp[r][c]) {
						dp[r][c] = dp[nr][nc] + 1;						
					}
				}
			}
		}
		if (dp[r][c] > ans) {
			ans = dp[r][c];
		}
	}
}