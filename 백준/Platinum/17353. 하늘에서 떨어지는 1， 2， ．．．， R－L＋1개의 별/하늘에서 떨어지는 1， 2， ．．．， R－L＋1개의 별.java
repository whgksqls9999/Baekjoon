import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
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

//		init(1, 0, N - 1);

		M = Integer.parseInt(br.readLine());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());

			if (type == 1) {
				int b = Integer.parseInt(st.nextToken());
				update(1, 0, N - 1, a - 1, b - 1, 1);
				update(1, 0, N - 1, b, b, -(b - a + 1));
			} else {
				sb.append(query(1, 0, N - 1, 0, a - 1) + arr[a - 1]).append("\n");
			}
//			System.out.println(Arrays.toString(tree));
//			System.out.println(Arrays.toString(lazy));

		}
		System.out.print(sb);

	}

	static void init(int node, int start, int end) {
		if (start == end) {
			tree[node] = arr[start];
			return;
		}

		int mid = (start + end) / 2;
		init(node * 2, start, mid);
		init(node * 2 + 1, mid + 1, end);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

	static void update(int node, int start, int end, int left, int right, int val) {
		updateLazy(node, start, end);

		if (end < left || right < start) {
			return;
		}

		if (left <= start && end <= right) {
			lazy[node] = val;

			updateLazy(node, start, end);
			return;
		}
		int mid = (start + end) / 2;
		update(node * 2, start, mid, left, right, val);
		update(node * 2 + 1, mid + 1, end, left, right, val);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

	static long query(int node, int start, int end, int left, int right) {
		updateLazy(node, start, end);

		if (end < left || right < start) {
			return 0;
		}

		if (left <= start && end <= right) {
			return tree[node];
		}
		int mid = (start + end) / 2;
		long l = query(node * 2, start, mid, left, right);
		long r = query(node * 2 + 1, mid + 1, end, left, right);
		return l + r;
	}

	static void updateLazy(int node, int start, int end) {
		if (lazy[node] == 0)
			return;

		tree[node] += lazy[node] * (end - start + 1);

		if (start != end) {
			lazy[node * 2 + 1] += lazy[node];
			lazy[node * 2] += lazy[node];
		}

		lazy[node] = 0;
	}

}