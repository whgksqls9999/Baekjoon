import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// dp테이블에 해당 인덱스를 값으로 할 때 까지 변경했을 때의 최소값을 담는다???
		int N = Integer.parseInt(st.nextToken());

		int[] dp = new int[N + 1];
		for (int i = 2; i < N + 1; i++) {

			dp[i] = dp[i - 1] + 1; // 뺴주는 것

			if (i % 2 == 0) {
				dp[i] = Math.min(dp[i], dp[i / 2] + 1);
			}
			if (i % 3 == 0) {
				dp[i] = Math.min(dp[i], dp[i / 3] + 1);
			}
		}
		System.out.println(dp[N]);
	} // main

}