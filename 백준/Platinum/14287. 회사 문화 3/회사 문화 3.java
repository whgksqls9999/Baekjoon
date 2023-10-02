import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M, order;
	static int[] tree;
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

		tree = new int[size];
		rel = new int[N + 1][2];

		DFS(1);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken(" "));

			int a = Integer.parseInt(st.nextToken(" "));

			if (type == 1) {
				int c = Integer.parseInt(st.nextToken(" "));
				update(1, 1, N, rel[a][0], c);
			} else {

				sb.append(query(1, 1, N, rel[a][0], rel[a][1])).append("\n");
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

	static void update(int node, int start, int end, int idx, int val) {
		if (idx < start || idx > end) {
			return;
		}

		if (start == end) {
			tree[node] += val;
			return;
		}

		update(node * 2, start, (start + end) / 2, idx, val);
		update(node * 2 + 1, (start + end) / 2 + 1, end, idx, val);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

	static int query(int node, int start, int end, int left, int right) {
		if (end < left || right < start) {
			return 0;
		}

		if (start == end) {
//			System.out.println(node + " : " + tree[node]);
			return tree[node];
		}

		int l = query(node * 2, start, (start + end) / 2, left, right);
		int r = query(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
//		System.out.println(idx + " : " + l + " : " + r);
		return l + r;
	}
}