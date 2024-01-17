import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[][] arr = new int[N][3];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; ++j) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][][] dp = new int[2][3][2];
		for (int i = 0; i < 3; ++i) {
			dp[0][i][0] = arr[0][i];
			dp[0][i][1] = arr[0][i];
		}

		int[] answer = new int[2];
		answer[1] = Integer.MAX_VALUE;

		for (int i = 1; i < N; ++i) {
			int cur = i % 2;
			int pre = 1 - (i % 2);

			for (int j = 0; j < 3; ++j) {
				int max = 0;
				int min = Integer.MAX_VALUE;

				for (int k = -1; k <= 1; ++k) {
					if (j + k >= 0 && j + k < 3) {
						max = Math.max(max, dp[pre][j + k][0]);
						min = Math.min(min, dp[pre][j + k][1]);
					}
				}
				dp[cur][j][0] = arr[i][j] + max;
				dp[cur][j][1] = arr[i][j] + min;
			}
		}

		int idx;
		if (N % 2 == 0) {
			idx = 1;
		} else {
			idx = 0;
		}

		for (int i = 0; i < 3; ++i) {
			answer[0] = Math.max(answer[0], dp[idx][i][0]);
			answer[1] = Math.min(answer[1], dp[idx][i][1]);
		}

		System.out.print(answer[0] + " " + answer[1]);
	}
}