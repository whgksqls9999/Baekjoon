import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static long[] arr, tree, lazy;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken(" ")); // 숫자의 개수
		M = Integer.parseInt(st.nextToken(" ")); // 변경 수
		K = Integer.parseInt(st.nextToken(" ")); // 조회 수

		// 초기 입력 숫자 저장
		arr = new long[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}

		// 트리의 크기를 구학고 선언
		int h = (int) Math.ceil(Math.log(N) / Math.log(2));
		int size = (1 << (h + 1));
		tree = new long[size];
		lazy = new long[size];

		init(1, 0, N - 1);

		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken(" "));
			int b = Integer.parseInt(st.nextToken(" "));
			int c = Integer.parseInt(st.nextToken(" "));

			if (type == 1) {
				long diff = Long.parseLong(st.nextToken(" "));
				update(1, 0, N - 1, b - 1, c - 1, diff);
			} else {
				sb.append(query(1, 0, N - 1, b - 1, c - 1)).append("\n");
			}
		}
		System.out.println(sb);
	} // main

	static void init(int node, int start, int end) {
		if (start == end) {
			tree[node] = arr[start];
			return;
		}

		init(node * 2, start, (start + end) / 2);
		init(node * 2 + 1, (start + end) / 2 + 1, end);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

	static void update(int node, int start, int end, int left, int right, long diff) {
		updateLazy(node, start, end);

		if (left > end || right < start) {
			return;
		}

		if (left <= start && end <= right) {
			tree[node] += diff * (end - start + 1);

			if (start != end) {
				lazy[node * 2] += diff;
				lazy[node * 2 + 1] += diff;
			}
			return;
		}

		update(node * 2, start, (start + end) / 2, left, right, diff);
		update(node * 2 + 1, (start + end) / 2 + 1, end, left, right, diff);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

	static long query(int node, int start, int end, int left, int right) {
		updateLazy(node, start, end);

		if (left > end || right < start) {
			return 0;
		}

		if (left <= start && end <= right) {
			return tree[node];
		}

		long lSum = query(node * 2, start, (start + end) / 2, left, right);
		long rSum = query(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
		return lSum + rSum;
	}

	static void updateLazy(int node, int start, int end) {
		if (lazy[node] != 0) {
			tree[node] += lazy[node] * (end - start + 1);

			// 리프 노드가 아니라면
			if (start != end) {
				lazy[node * 2] += lazy[node];
				lazy[node * 2 + 1] += lazy[node];
			}

			lazy[node] = 0;
		}
	}
}