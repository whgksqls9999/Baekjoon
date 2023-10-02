import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static long[] arr, tree, lazy;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken(" "));

		arr = new long[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken(" "));
		}

		int h = (int) Math.ceil(Math.log(N) / Math.log(2));
		int size = 1 << h + 1;

		tree = new long[size];
		lazy = new long[size];

		init(1, 0, N - 1);

		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken(" "));
			int a = Integer.parseInt(st.nextToken(" "));
			int b = Integer.parseInt(st.nextToken(" "));

			if (type == 1) {
				int c = Integer.parseInt(st.nextToken(" "));
				update(1, 0, N - 1, a, b, c);
			} else {
				sb.append(query(1, 0, N - 1, a, b)).append("\n");
			}

		}
		System.out.println(sb);
	}

	static void init(int node, int start, int end) {
		if (start == end) {
			tree[node] = arr[start];
			return;
		}

		init(node * 2, start, (start + end) / 2);
		init(node * 2 + 1, (start + end) / 2 + 1, end);
		tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
	}

	static void update(int node, int start, int end, int left, int right, int val) {
		updateLazy(node, start, end);

		if (end < left || right < start) {
			return;
		}

		if (left <= start && end <= right) {
			lazy[node] ^= val;

			updateLazy(node, start, end);
			return;
		}

		update(node * 2, start, (start + end) / 2, left, right, val);
		update(node * 2 + 1, (start + end) / 2 + 1, end, left, right, val);
		tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
	}

	static Long query(int node, int start, int end, int left, int right) {
		updateLazy(node, start, end);

		if (end < left || right < start) {
			return 0L;
		}

		if (left <= start && end <= right) {
			updateLazy(node, start, end);

			return tree[node];
		}

		long l = query(node * 2, start, (start + end) / 2, left, right);
		long r = query(node * 2 + 1, (start + end) / 2 + 1, end, left, right);

		return l ^ r;

	}

	static void updateLazy(int node, int start, int end) {
		if (lazy[node] != 0) {

			// xor을 짝수번 시행하면 자기 자신으로 돌아온다
			// 그런데 for문으롤 안돌리고 * 길이 연산으로 하면
			// 짝수번 시행했을 때 자기 자신으로 되돌리는 기능이 구현되지 않아
			// 값이 달라짐 = > 그래서 길이가 짝수일때는 그냥 예외처리해버리기

			if ((end - start + 1) % 2 == 1) {
				tree[node] ^= lazy[node];
			}

//			for (int i = 0; i < end - start + 1; i++) {
//				tree[node] ^= lazy[node];
//			}

			if (start != end) {
				lazy[node * 2] ^= lazy[node];
				lazy[node * 2 + 1] ^= lazy[node];
			}

			lazy[node] = 0;
		}
	}

}