import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[] dp = new int[k + 1];
		int[] arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}


		Arrays.fill(dp, 999999);
		dp[0] = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < dp.length; j++) {
				if (arr[i] > j) {
					dp[j] = dp[j];
				} else {
					dp[j] = Math.min(dp[j - arr[i]] + 1, dp[j]);
				}
			}
		}
		
		System.out.println(dp[k] == 999999? -1: dp[k]);

	}
}