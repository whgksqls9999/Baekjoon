import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		long sum;
		int min;

		public Node(long sum, int min) {
			super();
			this.sum = sum;
			this.min = min;
		}

	}

	static int N;
	static long[] arr;
	static Node[] tree;

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

		tree = new Node[size];

		init(1, 0, N - 1);

		System.out.println(getMaxScore(0, N - 1));
	} // main

	static void init(int node, int start, int end) {
		if (start == end) {
			tree[node] = new Node(arr[start], start);
			return;
		}

		init(node * 2, start, (start + end) / 2);
		init(node * 2 + 1, (start + end) / 2 + 1, end);
		long sum = tree[node * 2].sum + tree[node * 2 + 1].sum;
		int min = 0;
		if (arr[tree[node * 2].min] < arr[tree[node * 2 + 1].min]) {
			min = tree[node * 2].min;
		} else {
			min = tree[node * 2 + 1].min;
		}
		tree[node] = new Node(sum, min);
	}

	static long getMaxScore(int start, int end) {
		long max = 0;
		int idx = getMinIdx(1, 0, N - 1, start, end);
		long sum = getSum(1, 0, N - 1, start, end);

		long curScore = arr[idx] * sum;
		max = Math.max(curScore, max);

		if (start < idx) {
			long nextScore = getMaxScore(start, idx - 1);
			if (nextScore > max) {
				max = nextScore;
			}
		}

		if (idx < end) {
			long nextScore = getMaxScore(idx + 1, end);
			if (nextScore > max) {
				max = nextScore;
			}
		}

		return max;

	}

	static int getMinIdx(int node, int start, int end, int left, int right) {
		if (start > right || end < left) {
			return 10000001;
		}

		if (left <= start && end <= right) {
			return tree[node].min;
		}

		int leftIdx = getMinIdx(node * 2, start, (start + end) / 2, left, right);
		int rightIdx = getMinIdx(node * 2 + 1, (start + end) / 2 + 1, end, left, right);

		if (leftIdx == 10000001) {
			return rightIdx;
		} else if (rightIdx == 10000001) {
			return leftIdx;
		} else if (arr[leftIdx] < arr[rightIdx]) {
			return leftIdx;
		} else {
			return rightIdx;
		}
	}

	static long getSum(int node, int start, int end, int left, int right) {
		if (start > right || end < left) {
			return 0;
		}

		if (left <= start && end <= right) {
			return tree[node].sum;
		}

		long lSum = getSum(node * 2, start, (start + end) / 2, left, right);
		long rSum = getSum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
		return lSum + rSum;
	}
}