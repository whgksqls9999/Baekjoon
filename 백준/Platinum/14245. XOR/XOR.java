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

		N = Integer.parseInt(st.nextToken());

		arr = new long[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		M = Integer.parseInt(br.readLine());

		int h = (int) Math.ceil(Math.log(N) / Math.log(2));
		int size = 1 << h + 1;

		tree = new long[size];
		lazy = new long[size];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());

			if (type == 1) {
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());

				update(1, 0, N - 1, a, b, c);
			} else {
				sb.append(query(1, 0, N - 1, a)).append("\n");
			}
		}
//		System.out.println(Arrays.toString(tree));
//		System.out.println(Arrays.toString(lazy));
//		System.out.println(Arrays.toString(arr));
		System.out.println(sb);
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
	}

	static long query(int node, int start, int end, int idx) {
		updateLazy(node, start, end);

		if (idx < start || idx > end) {
			return 0;
		}

		if (start == end) {
			return tree[node] ^ arr[idx];
		}

		long l = query(node * 2, start, (start + end) / 2, idx);
		long r = query(node * 2 + 1, (start + end) / 2 + 1, end, idx);

		if (l == 0) {
			return r;
		} else {
			return l;
		}
	}

	static void updateLazy(int node, int start, int end) {
		if (lazy[node] != 0) {
			tree[node] ^= lazy[node];

			if (start != end) {
				lazy[node * 2] ^= lazy[node];
				lazy[node * 2 + 1] ^= lazy[node];
			}

			lazy[node] = 0;
		}
	}

}