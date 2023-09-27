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
		M = Integer.parseInt(st.nextToken(" "));

		arr = new long[N];

		int h = (int) Math.ceil(Math.log(N) / Math.log(2));
		int size = 1 << (h + 1);

		tree = new long[size];

		init(1, 0, N - 1);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (type == 0) {
				// type이 0일 경우 Sum
				int c = Integer.parseInt(st.nextToken());
				if (b < c) {
					sb.append(Sum(1, 0, N - 1, b - 1, c - 1)).append("\n");
				} else {
					sb.append(Sum(1, 0, N - 1, c - 1, b - 1)).append("\n");
				}
//				System.out.println(Sum(1, 0, N - 1, b - 1, c - 1));
			} else {
				// type이 1일 경우 Modify
				long c = Long.parseLong(st.nextToken());
				Modify(1, 0, N - 1, b - 1, c);
			}
		} // for

		System.out.println(sb);
	} // main

	// 트리 초기 값 설정
	static void init(int node, int start, int end) {

		// 리프노드에 도달했다면
		if (start == end) {

			// 트리 노드에 기본 입력 값 입력
			tree[node] = arr[start];
			return;
		}

		init(node * 2, start, (start + end) / 2);
		init(node * 2 + 1, (start + end) / 2 + 1, end);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

	// 구간 합 구하는 메서드
	static long Sum(int node, int start, int end, int left, int right) {
		if (left > end || right < start) {
			return 0;
		}

		if (left <= start && end <= right) {
			return tree[node];
		}

		return Sum(node * 2, start, (start + end) / 2, left, right)
				+ Sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);

//		return tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

//	static long Sum(int node, int start, int end, int left, int right) {
//		if (left > end || right < start)
//			return 0;
//
//		if (left <= start && end <= right) {
//			return tree[node];
//		}
//
//		return Sum(node * 2, start, (start + end) / 2, left, right)
//				+ Sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
//	}

	// 값 수정하는 메서드
	static void Modify(int node, int start, int end, int idx, long val) {
		// 범위를 벗어났을 때
		if (idx < start || idx > end) {
			return;
		}

		// 리프 노드에 도착했을 때
		if (start == end) {
			arr[idx] = val;
			tree[node] = val;
			return;
		}

		Modify(node * 2, start, (start + end) / 2, idx, val);
		Modify(node * 2 + 1, (start + end) / 2 + 1, end, idx, val);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}
}
