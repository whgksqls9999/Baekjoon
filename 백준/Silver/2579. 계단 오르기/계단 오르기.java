import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int[] input = new int[N + 1];
		int[] dp = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			input[i] = Integer.parseInt(br.readLine());
		}
		int sum = 0;
		if (N <= 2) {
			for (int i = 1; i < N+1; i++) {
				sum += input[i];
			}
			System.out.println(sum);
			return;
		}

		dp[1] = input[1];
		dp[2] = input[2] + input[1];
		for (int i = 3; i < N + 1; i++) {
			dp[i] = Math.max(dp[i - 3] + input[i - 1] + input[i], dp[i - 2] + input[i]);
		}

		System.out.println(dp[N]);

	} // main

}
