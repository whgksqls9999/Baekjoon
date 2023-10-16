import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] arr, tree;
	static long max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken(" "));

		if (N == 0) {
			System.out.println(0);
			return;
		}

		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken(" "));
		}

		tree = new int[4 * N];

		init(1, 0, N - 1);

		findMin(1, 0, N - 1);

		System.out.println(max);
	}

	static void init(int node, int start, int end) {
		if (start == end) {
			tree[node] = start;
			return;
		}

		int mid = (start + end) / 2;
		init(node * 2, start, mid);
		init(node * 2 + 1, mid + 1, end);

		if (arr[tree[node * 2]] < arr[tree[node * 2 + 1]]) {
			tree[node] = tree[node * 2];
		} else {
			tree[node] = tree[node * 2 + 1];
		}
	}

	static void findMin(int node, int start, int end) {
		if (start == end)
			return;

		int length = (end - start + 1);
		int idx = query(1, 0, N - 1, start, end);

		long val = length * arr[idx];
		if (val > max) {
			max = val;
		}

		if (start < idx) {
			findMin(node * 2, start, idx - 1);
		}
		if (end > idx) {
			findMin(node * 2 + 1, idx + 1, end);
		}
	}

	static int query(int node, int start, int end, int left, int right) {
		if (right < start || end < left) {
			return 1000001;
		}

		if (left <= start && end <= right) {
			return tree[node];
		}

		int mid = (start + end) / 2;
		int l = query(node * 2, start, mid, left, right);
		int r = query(node * 2 + 1, mid + 1, end, left, right);

		if (l == 1000001) {
			return r;
		} else if (r == 1000001) {
			return l;
		} else if (arr[l] < arr[r]) {
			return l;
		} else {
			return r;
		}
	}
}
