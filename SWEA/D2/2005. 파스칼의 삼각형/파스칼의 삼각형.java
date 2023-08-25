import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + "\n");
			int N = Integer.parseInt(br.readLine());
			int[][] dp = new int[N][N];
			for (int i = 0; i < N; i++) {
				dp[i][i] = 1;
				dp[i][0] = 1;
			}
			for (int i = 1; i < N; i++) {
				for (int j = 1; j < N; j++) {
					dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j <= i; j++) {
					sb.append(dp[i][j] + " ");
				}
				sb.append("\n");
			}
		} // tc
		System.out.println(sb);

	} // main

}