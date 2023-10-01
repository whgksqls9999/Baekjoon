import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static long[] arr;
	static int[] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());

		arr = new long[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken(" "));
		}

		int h = (int) Math.ceil(Math.log(N) / Math.log(2));
		int size = 1 << (h + 1);

		tree = new int[size];

		init(1, 0, N - 1);

		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			if (type == 1) {
				int b = Integer.parseInt(st.nextToken());
				Long c = Long.parseLong(st.nextToken());

				update(1, 0, N - 1, b - 1, c);
			} else {
//				query(1, 0, N - 1);
				sb.append(tree[1] + 1).append("\n");
			}
		}
		System.out.println(sb);
	} // main

	static void init(int node, int start, int end) {
		if (start == end) {
			tree[node] = start;
			return;
		}

		init(node * 2, start, (start + end) / 2);
		init(node * 2 + 1, (start + end) / 2 + 1, end);

		if (arr[tree[node * 2]] <= arr[tree[node * 2 + 1]]) {
			tree[node] = tree[node * 2];
		} else {
			tree[node] = tree[node * 2 + 1];
		}
	}

	static void update(int node, int start, int end, int idx, long val) {
		if (idx < start || idx > end) {
			return;
		}

		if (start == end) {
			arr[idx] = val;
			return;
		}
		update(node * 2, start, (start + end) / 2, idx, val);
		update(node * 2 + 1, (start + end) / 2 + 1, end, idx, val);

		if (arr[tree[node * 2]] <= arr[tree[node * 2 + 1]]) {
			tree[node] = tree[node * 2];
		} else {
			tree[node] = tree[node * 2 + 1];
		}
	}
}