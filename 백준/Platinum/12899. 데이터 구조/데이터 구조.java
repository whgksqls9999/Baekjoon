import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] arr, tree;
	static int length = 2_000_001;
	static int res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());

		arr = new int[length];

		tree = new int[length * 4];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken(" "));
			int a = Integer.parseInt(st.nextToken(" "));

			if (type == 1) {
				update(1, 1, length, a, 1);
			} else {
				query(1, 1, length, a);
				sb.append(res).append("\n");
				update(1, 1, length, res, -1);
			}
		}

		System.out.println(sb);
	}

	static void update(int node, int start, int end, int idx, int val) {
		if (idx < start || end < idx) {
			return;
		}

		if (start == end) {
			tree[node] += val;
			return;
		}

		int mid = (start + end) / 2;
		update(node * 2, start, mid, idx, val);
		update(node * 2 + 1, mid + 1, end, idx, val);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

	static void query(int node, int start, int end, int idx) {
		if (start == end) {
			res = start;
			return;
		}

		int mid = (start + end) / 2;

		if (tree[node * 2] >= idx) {
			query(node * 2, start, mid, idx);
		} else {
			query(node * 2 + 1, mid + 1, end, idx - tree[node * 2]);
		}

	}
}