import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M, order;
	static long[] tree, lazy;
	static int[][] rel;
	static List<Integer>[] nodes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken(" "));

		nodes = new ArrayList[N + 1];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int input = Integer.parseInt(st.nextToken(" "));

			if (input == -1)
				continue;

			nodes[input].add(i);
		}

		int h = (int) Math.ceil(Math.log(N) / Math.log(2));
		int size = 1 << h + 1;

		rel = new int[size][2];
		tree = new long[size];
		lazy = new long[size];

		DFS(1);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken(" "));
			int a = Integer.parseInt(st.nextToken(" "));

			if (type == 1) {
				long b = Long.parseLong(st.nextToken(" "));
				update(1, 1, N, a, b);
			} else {
				sb.append(query(1, 1, N, a)).append("\n");
			}
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

//	static void init(int node, int start, int end) {
//		
//	}

	static void update(int node, int start, int end, int idx, long val) {
		updateLazy(node, start, end);

		if (rel[idx][1] < start || rel[idx][0] > end) {
			return;
		}

		if (rel[idx][0] <= start && end <= rel[idx][1]) {
			lazy[node] += val;

			updateLazy(node, start, end);
			return;
		}

		update(node * 2, start, (start + end) / 2, idx, val);
		update(node * 2 + 1, (start + end) / 2 + 1, end, idx, val);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];

	}

	static long query(int node, int start, int end, int idx) {
		updateLazy(node, start, end);

		if (rel[idx][0] < start || end < rel[idx][0]) {
			return 0;
		}

		if (start == end) {
			return tree[node];
		}

		long l = query(node * 2, start, (start + end) / 2, idx);
		long r = query(node * 2 + 1, (start + end) / 2 + 1, end, idx);
		return l + r;

	}

	static void updateLazy(int node, int start, int end) {
		if (lazy[node] == 0)
			return;

		tree[node] += lazy[node] * (end - start + 1);

		if (start != end) {
			lazy[node * 2] += lazy[node];
			lazy[node * 2 + 1] += lazy[node];
		}

		lazy[node] = 0;
	}
}