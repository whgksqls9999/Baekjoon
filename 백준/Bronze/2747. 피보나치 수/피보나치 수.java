import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		dp = new int[n + 1];

		for (int i = 2; i < dp.length; i++) {
			dp[i] = -1;
		}
		dp[0] = 0;
		dp[1] = 1;

		System.out.println(fibo(n));
	} // main

	static int fibo(int num) {
		if (dp[num] != -1) {
			return dp[num];
		}
		return dp[num] = fibo(num - 1) + fibo(num - 2);
	}
}