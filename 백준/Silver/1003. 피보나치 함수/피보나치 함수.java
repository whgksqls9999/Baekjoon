import java.util.*;
import java.io.*;

public class Main {
	static int[][] dp = new int[41][2];
	static int cnt0, cnt1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		dp[0][0] = 1;
		dp[0][1] = 0;

		dp[1][0] = 0;
		dp[1][1] = 1;

		dp[2][0] = 1;
		dp[2][1] = 1;

		for (int i = 3; i < dp.length; i++) {
			dp[i][0] = dp[i - 1][0] + dp[i - 2][0];
			dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
		}

		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());
			System.out.println(dp[N][0] + " " + dp[N][1]);
		}
	}

}
