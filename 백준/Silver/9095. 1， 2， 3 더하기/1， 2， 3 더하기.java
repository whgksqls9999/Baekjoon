import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// n-1 : 1
		// n-2 : 2
		// n-3 : 4
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int[] dp = new int[n + 1];
			if (n == 1) {
				sb.append(1 + "\n");
				continue;
			} else if (n == 2) {
				sb.append(2 + "\n");
				continue;
			} else if (n == 3) {
				sb.append(4 + "\n");
				continue;
			}
			dp[1] = 1;
			dp[2] = 2;
			dp[3] = 4;
			for (int i = 4; i < dp.length; i++) {
				dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
			}
			sb.append(dp[n] + "\n");
		}
		System.out.println(sb);
	} // main
}