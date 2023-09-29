import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken(" "));
		}

		int[] dp = new int[N];
		int max = arr[0];
		for (int i = 0; i < N; i++) {
			dp[i] = arr[i];

			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i] && dp[j] + arr[i] > dp[i]) {
					dp[i] = dp[j] + arr[i];
					if (max < dp[i]) {
						max = dp[i];
					}
				}
			}
		}
		System.out.println(max);
	}
}