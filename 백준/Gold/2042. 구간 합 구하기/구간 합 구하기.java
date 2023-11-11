import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static long[] arr, tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// 수의 개수 N
		N = Integer.parseInt(st.nextToken());

		// 수의 변경이 일어나는 횟수 M
		M = Integer.parseInt(st.nextToken());

		// 구간 합을 구하는 횟수 K
		K = Integer.parseInt(st.nextToken());

		arr = new long[N + 1];
		tree = new long[N + 1];

		// 값 초기화
		for (int i = 1; i <= N; i++) {
			long input = Long.parseLong(br.readLine());
			update(i, input);
			arr[i] = input;
		}

		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());

			int b = Integer.parseInt(st.nextToken());

			if (type == 1) {
				long c = Long.parseLong(st.nextToken());
				update(b, c - arr[b]);
				arr[b] = c;
			} else {
				int c = Integer.parseInt(st.nextToken());
				sb.append(intervalSum(b, c)).append("\n");
			}

		} // for

		System.out.println(sb);
	} // main

	static long prefixSum(int i) {
		long result = 0;
		while (i > 0) {
			result += tree[i];
			i -= (i & -i);
		}
		return result;
	}

	static void update(int i, long val) {
		while (i <= N) {
			tree[i] += val;
			i += (i & -i);
		}
	}

	static long intervalSum(int start, int end) {
		return prefixSum(end) - prefixSum(start - 1);
	}
}