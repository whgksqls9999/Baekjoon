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
			int N = Integer.parseInt(br.readLine());
			if (N >= 1 && N <= 3) {
				System.out.println(1);
				continue;
			} else if (N >= 4 && N <= 5) {
				System.out.println(2);
				continue;
			}

			long[] dp = new long[N + 1];
			dp[1] = 1;
			dp[2] = 1;
			dp[3] = 1;
			dp[4] = 2;
			dp[5] = 2;

			for (int i = 6; i < N + 1; i++) {
				dp[i] = dp[i - 1] + dp[i - 5];
			}
			System.out.println(dp[N]);
		}
	} // main
}

// dp[n] = dp[n-1] + dp[n-5]
