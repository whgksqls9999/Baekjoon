import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static long[] arr;
	static long[] tree;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken(" "));

		arr = new long[N];

		// 원본 배열에 수 입력받기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			arr[i] = Integer.parseInt(st.nextToken(" "));
		}

		int h = (int) Math.ceil(Math.log(N) / Math.log(2));
		int size = 1 << (h + 1);

		tree = new long[size];

		init(1, 0, N - 1);

		M = Integer.parseInt(br.readLine());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int type = Integer.parseInt(st.nextToken(" "));
			int b = Integer.parseInt(st.nextToken(" "));

			if (type == 1) {

				// 1일 경우 수를 바꾼다
				long c = Long.parseLong(st.nextToken(" "));
				update(1, 0, N - 1, b - 1, c);
			} else {

				// 2일 경우 크기가 가장 작은 값을 출력한다
				int c = Integer.parseInt(st.nextToken(" "));
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
		tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
	}

	static void update(int node, int start, int end, int idx, long val) {
		if (idx < start || idx > end)
			return;

		if (start == end) {
			arr[idx] = val;
			tree[node] = arr[idx];
			return;
		}

		update(node * 2, start, (start + end) / 2, idx, val);
		update(node * 2 + 1, (start + end) / 2 + 1, end, idx, val);
		tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
	}

	static long query(int node, int start, int end, int left, int right) {

		if (left > end || right < start)
			return Integer.MAX_VALUE;

		if (left <= start && end <= right) {
			return tree[node];
		}

		return Math.min(query(node * 2, start, (start + end) / 2, left, right),
				query(node * 2 + 1, (start + end) / 2 + 1, end, left, right));
	}
}
