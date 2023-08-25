import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] dp;
	static int cnt;
	static int idx;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());

		dp = new int[n + 1];
		dp[1] = 1;
		dp[2] = 1;
		for (int i = 3; i < n + 1; i++) {
			dp[i] = -1;
		}
//		System.out.println(Arrays.toString(dp));
		fibo(n);
		fibonacci(n);
		System.out.println(cnt + " " + idx);
	} // main

	static int fibo(int n) {
		if (dp[n] != -1) {
			return dp[n];
		}
		idx++;
		return dp[n] = fibo(n - 1) + fibo(n - 2);
	}

	static int fibonacci(int n) {
		if (n == 1 || n == 2) {
			cnt++;
			return 1;
		}
		return fibonacci(n - 1) + fibonacci(n - 2);
	}
}