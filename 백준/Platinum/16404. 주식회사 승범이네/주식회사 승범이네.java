import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] arr;
	static long[] tree, lazy;
	static int[][] rel;
	static List<Integer>[] nodes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		rel = new int[N + 1][2];

		int h = (int) Math.ceil(Math.log(N) / Math.log(2));
		int size = 1 << h + 1;

		tree = new long[size];
		lazy = new long[size];

		nodes = new ArrayList[size];
		for (int i = 1; i < nodes.length; i++) {
			nodes[i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int input = Integer.parseInt(st.nextToken());
			if (input == -1)
				continue;

			nodes[input].add(i);
		}

		DFS(1);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int type = Integer.parseInt(st.nextToken());

			int a = Integer.parseInt(st.nextToken());
			if (type == 1) {
				int x = Integer.parseInt(st.nextToken());
				update(1, 1, N, rel[a][0], rel[a][1], x);
			} else {
				sb.append(query(1, 1, N, rel[a][0], rel[a][0])).append("\n");
			}
		}
		System.out.print(sb);
	}

	static int order;

	static void DFS(int node) {
		rel[node][0] = ++order;
		for (int i : nodes[node]) {
			DFS(i);
		}
		rel[node][1] = order;
	}

	static void update(int node, int start, int end, int left, int right, int val) {
		updateLazy(node, start, end);

		if (right < start || end < left) {
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

		if (end < left || left < start) {
			return 0;
		}

//		if (left <= start && end <= right) {
		if (start == end) {
			return tree[node];
		}

		int mid = (start + end) / 2;
		long l = query(node * 2, start, mid, left, right);
		long r = query(node * 2 + 1, mid + 1, end, left, right);
		return l + r;
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