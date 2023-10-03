import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, K, idx;
	static int[] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken(" "));
		K = Integer.parseInt(st.nextToken(" "));
		int diff = K;

		int h = (int) Math.ceil(Math.log(N) / Math.log(2));
		int size = 1 << h + 1;

		tree = new int[size];

		init(1, 1, N);

		idx = 0;
		sb.append("<");
		for (int i = 1; i <= N; i++) {
			getK(1, N, K);

			sb.append(idx);
			if (i < N) {
				sb.append(", ");
			}
			if (i < N) {
				K = (K + diff - 1) % (N - i);
			}
			if (K == 0) {
				K = N - i;
			}
			deleteK(1, 1, N, idx);
		}
		sb.append(">");
		System.out.println(sb);

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

	static void deleteK(int node, int start, int end, int idx) {
		if (idx < start || end < idx) {
			return;
		}

		if (start == end) {
			tree[node] -= 1;
			return;
		}

		int mid = (start + end) / 2;
		deleteK(node * 2, start, mid, idx);
		deleteK(node * 2 + 1, mid + 1, end, idx);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

	static int query(int node, int start, int end, int left, int right) {
		if (end < left || right < start) {
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

	static void getK(int start, int end, int k) {
		int mid = (start + end) / 2;
		int length = end - start + 1;

		int left = query(1, 1, N, 1, mid);
		int right = query(1, 1, N, 1, end);

//		System.out.println(K + " : " + left + " : " + right);

		if (start == end) {
			idx = start;
			return;
		}

		if (left >= K) {
			getK(start, mid, k);
		} else {
			getK(mid + 1, end, k - length);
		}

	}
}