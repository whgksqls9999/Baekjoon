import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int N, Q;
	public static int[] arr;
	public static List<Integer>[] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		arr = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int h = (int) Math.ceil(Math.log(N) / Math.log(2));
		int size = 1 << (h + 1);
		tree = new ArrayList[size];
		for (int i = 1; i < size; i++) {
			tree[i] = new ArrayList<>();
		}

		init(1, 1, N, arr);

		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			int left = -1_000_000_000;
			int right = 1_000_000_000;

			while (left <= right) {
				int mid = (left + right) / 2;

				int res = query(1, 1, N, r, j, mid);

				if (res < k) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}

			answer.append(right).append("\n");
		}
		System.out.print(answer);
	}

	public static void init(int node, int start, int end, int[] arr) {
		if (start == end) {
			tree[node].add(arr[start]);
			return;
		}

		int mid = (int) Math.floor((start + end) / 2);
		init(node * 2, start, mid, arr);
		init(node * 2 + 1, mid + 1, end, arr);
		sort(node, tree[node * 2], tree[node * 2 + 1]);
	}

	public static void sort(int node, List<Integer> left, List<Integer> right) {
		List<Integer> tmp = new ArrayList<>();
		int l = 0, r = 0;
		int idx = 0;

		while (l < left.size() && r < right.size()) {
			if (left.get(l) > right.get(r)) {
				tmp.add(right.get(r++));
			} else if (left.get(l) <= right.get(r)) {
				tmp.add(left.get(l++));
			}
		}

		while (l < left.size()) {
			tmp.add(left.get(l++));
		}
		while (r < right.size()) {
			tmp.add(right.get(r++));
		}

		tree[node] = tmp;
	}

	public static int query(int node, int start, int end, int left, int right, int val) {
		if (end < left || right < start) {
			return 0;
		}

		if (left <= start && end <= right) {
			int l = 0;
			int r = tree[node].size();
			while (l < r) {
				int mid = (l + r) / 2;

				if (tree[node].get(mid) >= val) {
					r = mid;
				} else {
					l = mid + 1;
				}
			}
			return r;
		}

		int mid = (start + end) / 2;
		int l = query(node * 2, start, mid, left, right, val);
		int r = query(node * 2 + 1, mid + 1, end, left, right, val);
		return l + r;
	}
}