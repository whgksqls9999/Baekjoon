import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] arr, tree;
	static int INF = 1_000_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken(" "));

		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken(" "));
		}

		int h = (int) Math.ceil(Math.log(N) / Math.log(2));
		int size = 1 << (h + 1);
		tree = new int[size];

		init(1, 0, N - 1);

		M = Integer.parseInt(br.readLine());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken(" "));
			int b = Integer.parseInt(st.nextToken(" "));

			if (type == 1) {
				int val = Integer.parseInt(st.nextToken(" "));
				update(1, 0, N - 1, b - 1, val);
			} else {
				int c = Integer.parseInt(st.nextToken(" "));
				sb.append(query(1, 0, N - 1, b - 1, c - 1) + 1).append("\n");
			}
		}
		System.out.println(sb);
	} // main

	static void init(int node, int start, int end) {
		if (start == end) {
			tree[node] = start;
			return;
		}

		int mid = (start + end) / 2;
		init(node * 2, start, mid);
		init(node * 2 + 1, mid + 1, end);

		if (arr[tree[node * 2]] <= arr[tree[node * 2 + 1]]) {
			tree[node] = tree[node * 2];
		} else {
			tree[node] = tree[node * 2 + 1];
		}
	}

	static void update(int node, int start, int end, int idx, int val) {
		if (idx < start || idx > end) {
			return;
		}

		if (start == end) {
			arr[idx] = val;
			return;
		}

		int mid = (start + end) / 2;

		update(node * 2, start, mid, idx, val);
		update(node * 2 + 1, mid + 1, end, idx, val);

		if (arr[tree[node * 2]] <= arr[tree[node * 2 + 1]]) {
			tree[node] = tree[node * 2];
		} else {
			tree[node] = tree[node * 2 + 1];
		}
	}

	static int query(int node, int start, int end, int left, int right) {
		if (left > end || right < start) {
			return INF;
		}

		if (left <= start && end <= right) {
			return tree[node];
		}

		int mid = (start + end) / 2;
		int l = query(node * 2, start, mid, left, right);
		int r = query(node * 2 + 1, mid + 1, end, left, right);

		if (l == INF) {
			return r;
		} else if (r == INF) {
			return l;
		} else if (arr[l] <= arr[r]) {
			return l;
		} else {
			return r;
		}
	}
}