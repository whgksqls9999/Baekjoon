import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class Main {
	static int[][] sum, num;
	static int N, M;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		sum = new int[N][N];
		num = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				num[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 1행 / 1열 채우기
		sum[0][0] = num[0][0];
		if (N != 1) {
			for (int i = 1; i < N; i++) {
				sum[0][i] = sum[0][i - 1] + num[0][i];
				sum[i][0] = sum[i - 1][0] + num[i][0];
			}
			for (int i = 1; i < N; i++) {
				for (int j = 1; j < N; j++) {
					sum[i][j] = num[i][j] + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
				}
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r1 = Integer.parseInt(st.nextToken());
			int c1 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());

			if (r1 == 1 && c1 == 1) {
				System.out.println(sum[r2 - 1][c2 - 1]);
			} else if (r1 == 1) {
				System.out.println(sum[r2 - 1][c2 - 1] - sum[r2 - 1][c1 - 2]);
			} else if (c1 == 1) {
				System.out.println(sum[r2 - 1][c2 - 1] - sum[r1 - 2][c2 - 1]);
			} else if (r1 == r2 && c1 == c2) {
				System.out.println(num[r2 - 1][c2 - 1]);
			} else {
				System.out
						.println(sum[r2 - 1][c2 - 1] + sum[r1 - 2][c1 - 2] - sum[r1 - 2][c2 - 1] - sum[r2 - 1][c1 - 2]);
			}
		}

	}// main
}