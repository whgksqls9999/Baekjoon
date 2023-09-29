import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] arr, idx, tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken(" "));

		Map<Integer, Integer> index = new HashMap<>();

		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			index.put(arr[i], N - 1 - i);
		}

		idx = arr.clone();
		Arrays.sort(idx);

		int h = (int) Math.ceil(Math.log(N) / Math.log(2));
		int size = 1 << (h + 1);

		tree = new int[size];

		int[] ans = new int[N];
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			int pos = index.get(idx[i]);
			cnt = query(1, 0, N - 1, pos + 1, N - 1);
			ans[pos] = N - pos - cnt;
			update(1, 0, N - 1, pos, 1);
		}
		for (int i = N - 1; i >= 0; i--) {
			sb.append(ans[i]).append("\n");
		}

		System.out.print(sb);
	} // main

	static void update(int node, int start, int end, int idx, int val) {
		if (idx < start || idx > end) {
			return;
		}

		if (start == end) {
			tree[node] = val;
			return;
		}

		update(node * 2, start, (start + end) / 2, idx, val);
		update(node * 2 + 1, (start + end) / 2 + 1, end, idx, val);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

	static int query(int node, int start, int end, int left, int right) {
		if (end < left || start > right) {
			return 0;
		}

		if (left <= start && end <= right) {
			return tree[node];
		}

		int lSum = query(node * 2, start, (start + end) / 2, left, right);
		int rSum = query(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
		return lSum + rSum;
	}
}