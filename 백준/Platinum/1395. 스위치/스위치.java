import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] tree, lazy;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 스위치 수
		M = Integer.parseInt(st.nextToken()); // 처리할 일의 개수

		int h = (int) Math.ceil(Math.log(N) / Math.log(2));
		int size = 1 << (h + 1);

		tree = new int[size];
		lazy = new int[size];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (type == 0) {
				update(1, 0, N - 1, a - 1, b - 1);
			} else {
				sb.append(query(1, 0, N - 1, a - 1, b - 1)).append("\n");
			}

		}
		System.out.println(sb);
	}

	static void update(int node, int start, int end, int left, int right) {
		updateLazy(node, start, end);

		if (end < left || right < start) {
			return;
		}

		if (left <= start && end <= right) {
//			int lazys = tree[node];
//			tree[node] = (end - start + 1) - tree[node];
//
//			if (start != end) {
//				lazy[node * 2] += lazys;
//				lazy[node * 2 + 1] += lazys;
//			}
			lazy[node] = 1;
			updateLazy(node, start, end);
			return;
		}

		update(node * 2, start, (start + end) / 2, left, right);
		update(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

	static int query(int node, int start, int end, int left, int right) {
		updateLazy(node, start, end);

		if (end < left || right < start) {
			return 0;
		}

		if (left <= start && end <= right) {
			return tree[node];
		}

		int l = query(node * 2, start, (start + end) / 2, left, right);
		int r = query(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
		return l + r;
	}

	static void updateLazy(int node, int start, int end) {
		if (lazy[node] % 2 == 1) {
			tree[node] = (end - start + 1) - tree[node];

			if (start != end) {
				lazy[node * 2] += lazy[node];
				lazy[node * 2 + 1] += lazy[node];
			}

			lazy[node] = 0;
		}
	}
}