import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder answer = new StringBuilder();

		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());

			int[] A = new int[N];
			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}

			int[] B = new int[M];
			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < M; i++) {
				B[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(B);

			int res = 0;
			for (int n : A) {
				int lo = 0, hi = B.length;

				while (lo < hi) {
					int mid = (lo + hi) / 2;

					if (n <= B[mid]) {
						hi = mid;
					} else {
						lo = mid + 1;
					}
				}
				res += lo;
			}
			answer.append(res).append("\n");
		}
		System.out.println(answer);
	}
}
