import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static long[] arr;
	static int[] tree;
	static int[] length;
	static int N;
	static long max;
	static Queue<Integer> depth;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		while (true) {
			depth = new LinkedList<>();

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			if (N == 0)
				break;

			max = 0;

			arr = new long[N];

			for (int i = 0; st.hasMoreTokens(); i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			int h = (int) Math.ceil(Math.log(N) / Math.log(2));
			int size = 1 << (h + 1);

			tree = new int[size];
			length = new int[size];

			init(1, 0, N - 1, tree, length);

			sb.append(divide(0, N - 1)).append("\n");
		}
		System.out.println(sb);

	} // main

	// 해당 구간에서의 최소 높이를 가진 인덱스를 저장하게함
	static int init(int node, int start, int end, int[] tmp, int[] lengthArr) {
		lengthArr[node] = (end - start + 1);

		if (start == end) {

			// 최소 높이를 가진 인덱스를 저장; 인덱스 -> 분할정복을 위해 저장한다
			return tmp[node] = start;
		}

		int left = init(node * 2, start, (start + end) / 2, tmp, lengthArr);
		int right = init(node * 2 + 1, (start + end) / 2 + 1, end, tmp, lengthArr);
		if (arr[left] < arr[right]) {
			tmp[node] = left;
		} else {
			tmp[node] = right;
		}
		return tmp[node];

	}

//	static void update(int node, int start, int end, int idx, long val) {
//		if (idx < start || idx > end)
//			return;
//
//		if (start == end) {
//			arr[idx] = val;
//			tree[node] = arr[idx];
//			return;
//		}
//
//		update(node * 2, start, (start + end) / 2, idx, val);
//		update(node * 2 + 1, (start + end) / 2 + 1, end, idx, val);
//		tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
//	}

	// 지정 구간에서 최소 높이를 가지는 인덱스 반환
	static int query(int node, int start, int end, int left, int right) {
		if (left > end || right < start)
			return 1000000001;

		if (left <= start && end <= right) {
			return tree[node];
		}

		int leftSide = query(node * 2, start, (start + end) / 2, left, right);
		int rightSide = query(node * 2 + 1, (start + end) / 2 + 1, end, left, right);

		if (leftSide == 1000000001) {
			return rightSide;
		} else if (rightSide == 1000000001) {
			return leftSide;
		} else if (arr[leftSide] < arr[rightSide]) {
			return leftSide;
		} else {
			return rightSide;
		}
	}

	// 지정 구간에서의 최대 넓이 구하긴
	static long divide(int start, int end) {
		int idx = query(1, 0, N - 1, start, end);

		max = Math.max(max, arr[idx] * (end - start + 1));

		if (start < idx) {
			long now = divide(start, idx - 1);
			if (now > max) {
				max = now;
			}
		}

		if (end > idx) {
			long now = divide(idx + 1, end);
			if (now > max) {
				max = now;
			}
		}

		return max;
	}
}
