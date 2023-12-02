import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] arr = new int[N + 1][M + 1];
		int[][] prefixSum = new int[N + 1][M + 1];

		for (int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; ++j) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= N; ++i) {
			for (int j = 1; j <= M; ++j) {
				prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1] + arr[i][j];
			}
//			System.out.println(Arrays.toString(prefixSum[i]));
		}

		int K = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		while (K-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			sb.append(prefixSum[c][d] - prefixSum[a - 1][d] - prefixSum[c][b - 1] + prefixSum[a - 1][b - 1])
					.append("\n");
		}

		System.out.println(sb);

	}
}