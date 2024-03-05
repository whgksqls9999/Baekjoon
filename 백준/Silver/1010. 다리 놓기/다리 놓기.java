import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int[][] binoArr = new int[31][31];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int[] i : binoArr) {
			Arrays.fill(i, -1);
		}

		StringBuilder answer = new StringBuilder();

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

			int res = bino(M, N);
			answer.append(res).append("\n");
		}

		System.out.print(answer);
	}

	public static int bino(int M, int N) {
		if (N == 0 || N == M) {
			return binoArr[M][N] = 1;
		}

		if (binoArr[M][N] != -1) {
			return binoArr[M][N];
		}

		binoArr[M][N] = bino(M - 1, N - 1) + bino(M - 1, N);

		return binoArr[M][N];
	}
}