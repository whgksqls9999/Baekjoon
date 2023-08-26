import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// n-1 => 세로 2*1
		// n-2 => 2*2 하나 / 가로 2*1 둘 / [세로 2*1 둘 : 이건 중복];
		// n = n-1 + 2*(n-2);

		int N = Integer.parseInt(st.nextToken());
		int[] dp = new int[N + 1];
		if (N == 1) {
			System.out.println(1);
			return;
		} else if (N == 2) {
			System.out.println(3);
			return;
		}
		dp[1] = 1;
		dp[2] = 3;
		for (int i = 3; i < N + 1; i++) {
			dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % 10007;
		}
		System.out.println(dp[N]);

	} // main
}