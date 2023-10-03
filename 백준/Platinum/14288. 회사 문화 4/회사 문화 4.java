import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M, order;
	static int[] tree, tree2, lazy;
	static int[][] rel;
	static List<Integer>[] nodes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		nodes = new ArrayList[N + 1];
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

		int h = (int) Math.ceil(Math.log(N) / Math.log(2));
		int size = 1 << h + 1;

		tree = new int[size]; // up
		tree2 = new int[size]; // down
		lazy = new int[size];
		rel = new int[N + 1][2];

		DFS(1);

		boolean mode = false;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken(" "));

			if (type == 1) {
				int a = Integer.parseInt(st.nextToken(" "));
				int c = Integer.parseInt(st.nextToken(" "));

				if (mode) {
					updateUp(1, 1, N, rel[a][0], c);
				} else {
					updateDown(1, 1, N, a, c);
				}
			} else if (type == 2) {
				int a = Integer.parseInt(st.nextToken(" "));

				sb.append((query2(1, 1, N, a) + query(1, 1, N, a))).append("\n");
			} else {
				mode = !mode;
			}
//			System.out.println(Arrays.toString(tree));
		}
		System.out.println(sb);
	}

	static void DFS(int node) {
		rel[node][0] = ++order;

		for (int i : nodes[node]) {
			DFS(i);
		}

		rel[node][1] = order;
	}

	static void updateUp(int node, int start, int end, int idx, int val) {
		if (idx < start || end < idx) {
			return;
		}

		if (start == end) {
			tree[node] += val;
			return;
		}

		int mid = (start + end) / 2;
		updateUp(node * 2, start, mid, idx, val);
		updateUp(node * 2 + 1, mid + 1, end, idx, val);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

	static void updateDown(int node, int start, int end, int idx, int val) {
		updateLazy(node, start, end);

		if (rel[idx][1] < start || end < rel[idx][0]) {
			return;
		}

		if (rel[idx][0] <= start && end <= rel[idx][1]) {
			lazy[node] += val;

			updateLazy(node, start, end);
			return;
		}

		int mid = (start + end) / 2;
		updateDown(node * 2, start, mid, idx, val);
		updateDown(node * 2 + 1, mid + 1, end, idx, val);
		tree2[node] = tree2[node * 2] + tree2[node * 2 + 1];
	}

	static void updateLazy(int node, int start, int end) {
		if (lazy[node] == 0)
			return;

		tree2[node] += lazy[node] * (end - start + 1);

		if (start != end) {
			lazy[node * 2] += lazy[node];
			lazy[node * 2 + 1] += lazy[node];
		}

		lazy[node] = 0;
	}

	static int query(int node, int start, int end, int idx) {
		if (end < rel[idx][0] || rel[idx][1] < start) {
			return 0;
		}

		if (start == end) {
			return tree[node];
		}

		int l = query(node * 2, start, (start + end) / 2, idx);
		int r = query(node * 2 + 1, (start + end) / 2 + 1, end, idx);

		return l + r;
	}

	static int query2(int node, int start, int end, int idx) {
		updateLazy(node, start, end);

		if (rel[idx][0] < start || end < rel[idx][0]) {
			return 0;
		}

		if (start == end) {
			return tree2[node];
		}

		int l = query2(node * 2, start, (start + end) / 2, idx);
		int r = query2(node * 2 + 1, (start + end) / 2 + 1, end, idx);
		return l + r;

	}

}