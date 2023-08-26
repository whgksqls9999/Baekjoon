import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			int k = Integer.parseInt(br.readLine()); // k층
			int n = Integer.parseInt(br.readLine()); // n호
			// k층 n호에 살기 위해서는 k-1층 1호 ~ n호 수의 합
			int[][] dp = new int[k + 1][n + 1];

			for (int i = 1; i < n + 1; i++) {
				dp[0][i] = i;
			}

			for (int i = 1; i < k + 1; i++) {
				for (int j = 1; j < n + 1; j++) {
					dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
				}
			}
			System.out.println(dp[k][n]);

		}
	} // main

}