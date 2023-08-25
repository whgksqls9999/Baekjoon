import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// dp[n] = dp[n-1] + dp[n-2]
		int n = Integer.parseInt(st.nextToken());
		dp = new int[n + 1];
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i < n + 1; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2]) % 15746;
		}
		System.out.println(dp[n]);
	} // main

}