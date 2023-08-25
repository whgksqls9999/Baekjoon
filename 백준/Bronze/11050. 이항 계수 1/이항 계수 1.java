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

		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int[][] dp = new int[11][11];

		for (int i = 0; i < 11; i++) {
			// 조합에서의 기본적인 dp테이블 초기화
			// 1. D[i][1] = ㅑ
			// 2. D[i][0] = 1
			// 3. D[i][i] = 1
			dp[i][1] = i;
			dp[i][0] = 1;
			dp[i][i] = 1;
		}
		for (int i = 2; i < 11; i++) {
			for (int j = 2; j < i; j++) {
				dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
			}
		}

		System.out.println(dp[a][b]);
	} // main

}