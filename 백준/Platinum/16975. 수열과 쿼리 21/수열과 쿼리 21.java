import java.io.*;
import java.util.*;

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
			arr[i] = Integer.parseInt(st.nextToken(" "));
		}

		int h = (int) Math.ceil(Math.log(N) / Math.log(2));
		int size = 1 << (h + 1);

		tree = new long[size];
		lazy = new long[size];

		init(1, 0, N - 1);

		M = Integer.parseInt(br.readLine());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken(" "));
			int b = Integer.parseInt(st.nextToken(" "));

			if (type == 1) {
				int c = Integer.parseInt(st.nextToken(" "));
				long val = Long.parseLong(st.nextToken(" "));
				update(1, 0, N - 1, b - 1, c - 1, val);
			} else {
				sb.append(query(1, 0, N - 1, b - 1)).append("\n");
			}
		}
		System.out.println(sb);
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

	static void update(int node, int start, int end, int left, int right, long val) {
		updateLazy(node, start, end);

		if (left > end || right < start) {
			return;
		}

		if (left <= start && end <= right) {
			tree[node] += val * (end - start + 1);

			// 리프 노드가 아니라면
			if (start != end) {
				lazy[node * 2] += val;
				lazy[node * 2 + 1] += val;
			}

			return;
		}

		int mid = (start + end) / 2;
		update(node * 2, start, mid, left, right, val);
		update(node * 2 + 1, mid + 1, end, left, right, val);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

	static long query(int node, int start, int end, int idx) {
		updateLazy(node, start, end);

		if (idx < start || idx > end) {
			return 0;
		}

		if (start == end) {
			return tree[node];
		}

		int mid = (start + end) / 2;
		long lSum = query(node * 2, start, mid, idx);
		long rSum = query(node * 2 + 1, mid + 1, end, idx);
		return lSum + rSum;
	}

	static void updateLazy(int node, int start, int end) {
		if (lazy[node] != 0) {
			tree[node] += lazy[node] * (end - start + 1);

			if (start != end) {
				lazy[node * 2] += lazy[node];
				lazy[node * 2 + 1] += lazy[node];
			}

			lazy[node] = 0;
		}
	}
}