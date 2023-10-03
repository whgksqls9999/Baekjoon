import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M, order;
	static int[] tree, lazy;
	static int[][] rel;
	static List<Integer>[] nodes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken(" "));

		nodes = new ArrayList[N + 1];
		for (int i = 1; i < nodes.length; i++) {
			nodes[i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int input = Integer.parseInt(st.nextToken(" "));

			if (input == 0)
				continue;

			nodes[input].add(i);
		}

		int h = (int) Math.ceil(Math.log(N) / Math.log(2));
		int size = 1 << h + 1;

		tree = new int[size];
		lazy = new int[size];
		rel = new int[N + 1][2];

		Arrays.fill(lazy, -1);

		DFS(1);

		init(1, 1, N);

		M = Integer.parseInt(br.readLine());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken(" "));
			int a = Integer.parseInt(st.nextToken(" "));

			if (type == 1) {
				update(1, 1, N, rel[a][0] + 1, rel[a][1], 1);
			} else if (type == 2) {

				update(1, 1, N, rel[a][0] + 1, rel[a][1], 0);
			} else {
				sb.append(query(1, 1, N, rel[a][0] + 1, rel[a][1])).append("\n");
			}
//			System.out.println(Arrays.toString(tree));
		}
//		for (int i = 0; i < rel.length; i++) {
//			System.out.println(Arrays.toString(rel[i]));
//		}
		System.out.println(sb);
	}

	static void DFS(int node) {
		rel[node][0] = ++order;

		for (int i : nodes[node]) {
			DFS(i);
		}

		rel[node][1] = order;
	}

	static void init(int node, int start, int end) {
		if (start == end) {
			tree[node] = 1;
			return;
		}

		int mid = (start + end) / 2;
		init(node * 2, start, mid);
		init(node * 2 + 1, mid + 1, end);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

	static void update(int node, int start, int end, int left, int right, int val) {
		updateLazy(node, start, end);
		if (right < start || end < left) {
			return;
		}

		if (left <= start && end <= right) {
			tree[node] = val * (end - start + 1);

			if (start != end) {
				lazy[node * 2] = val;
				lazy[node * 2 + 1] = val;
			}
			return;
		}
		int mid = (start + end) / 2;

		update(node * 2, start, mid, left, right, val);
		update(node * 2 + 1, mid + 1, end, left, right, val);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];

	}

	static int query(int node, int start, int end, int left, int right) {
		updateLazy(node, start, end);

		if (right < start || end < left) {
			return 0;
		}

		if (left <= start && end <= right) {
			return tree[node];
		}

		int mid = (start + end) / 2;
		int l = query(node * 2, start, mid, left, right);
		int r = query(node * 2 + 1, mid + 1, end, left, right);

		return l + r;
	}

	static void updateLazy(int node, int start, int end) {
		if (lazy[node] != -1) {
			tree[node] = lazy[node] * (end - start + 1);

			if (start != end) {
				lazy[node * 2] = lazy[node];
				lazy[node * 2 + 1] = lazy[node];
			}

			lazy[node] = -1;
		}
	}
}