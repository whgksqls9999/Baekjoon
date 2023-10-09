import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] treeMin, treeMax, arr;
	static int N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// 테스트 케이스 입력받기
		int T = Integer.parseInt(st.nextToken(" "));

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			// N : DVD들의 수
			N = Integer.parseInt(st.nextToken(" "));

			// K : 사건(확인 및 값 변경)의 수
			K = Integer.parseInt(st.nextToken(" "));

			// h : 트리의 높이
			int h = (int) Math.ceil(Math.log(N) / Math.log(2));

			// size : 트리의 사이즈
			int size = 1 << h + 1;

			// 동일한 사이즈로 최소값을 담는 트리, 최대값을 담는 트리를 작성
			treeMin = new int[size];
			treeMax = new int[size];

			// arr : DVD들의 번호를 담아 줄 배열
			arr = new int[N];
			for (int i = 1; i < N; i++) {
				arr[i] = i;
			}

			// 트리 초기화
			init(1, 0, N - 1);

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int type = Integer.parseInt(st.nextToken(" "));
				int a = Integer.parseInt(st.nextToken(" "));
				int b = Integer.parseInt(st.nextToken(" "));

				if (type == 1) {

					// 최소값, 최대값을 가져온다.
					int min = queryMin(1, 0, N - 1, a, b);
					int max = queryMax(1, 0, N - 1, a, b);

					// a번 인덱스 ~ b번 인덱스의 최소, 최대값을 확인하여
					// 최소값 = a, 최대값 = b 라면 YES 출력
					if (min == a && max == b) {
						sb.append("YES").append("\n");
					} else {
						sb.append("NO").append("\n");
					}
				} else {
					// a번 인덱스의 값을 b번 인덱스의 값으로 변경
					update(1, 0, N - 1, a, b);

					// b번 인덱스의 값을 a번 인덱스의 값으로 변경
					update(1, 0, N - 1, b, a);

					// arr에 저장된 값들의 자리도 변경
					int tmp = arr[a];
					arr[a] = arr[b];
					arr[b] = tmp;
				}
			}
		}
		System.out.println(sb);
	}

	// 트리 초기화
	static void init(int node, int start, int end) {
		if (start == end) {
			treeMin[node] = treeMax[node] = start;
			return;
		}

		int mid = (start + end) / 2;
		init(node * 2, start, mid);
		init(node * 2 + 1, mid + 1, end);
		treeMin[node] = Math.min(treeMin[node * 2], treeMin[node * 2 + 1]);
		treeMax[node] = Math.max(treeMax[node * 2], treeMax[node * 2 + 1]);
	}

	// 구간의 최소값을 반환하는 메서드
	static int queryMin(int node, int start, int end, int left, int right) {
		if (end < left || right < start) {
			return Integer.MAX_VALUE;
		}

		if (left <= start && end <= right) {
			return treeMin[node];
		}

		int mid = (start + end) / 2;
		int l = queryMin(node * 2, start, mid, left, right);
		int r = queryMin(node * 2 + 1, mid + 1, end, left, right);

		if (l == Integer.MAX_VALUE) {
			return r;
		} else if (r == Integer.MAX_VALUE) {
			return l;
		} else {
			return Math.min(l, r);
		}
	}

	// 구간의 최대값을 반환하는 메서드
	static int queryMax(int node, int start, int end, int left, int right) {
		if (end < left || right < start) {
			return Integer.MIN_VALUE;
		}

		if (left <= start && end <= right) {
			return treeMax[node];
		}

		int mid = (start + end) / 2;
		int l = queryMax(node * 2, start, mid, left, right);
		int r = queryMax(node * 2 + 1, mid + 1, end, left, right);

		if (l == Integer.MIN_VALUE) {
			return r;
		} else if (r == Integer.MIN_VALUE) {
			return l;
		} else {
			return Math.max(l, r);
		}
	}

	// 두 자리의 값을 변경하는 메서드
	static void update(int node, int start, int end, int idx, int idx2) {
		if (idx < start || end < idx) {
			return;
		}

		if (start == end) {
			treeMin[node] = arr[idx2];
			treeMax[node] = arr[idx2];
			return;
		}
		int mid = (start + end) / 2;

		update(node * 2, start, mid, idx, idx2);
		update(node * 2 + 1, mid + 1, end, idx, idx2);
		treeMin[node] = Math.min(treeMin[node * 2], treeMin[node * 2 + 1]);
		treeMax[node] = Math.max(treeMax[node * 2], treeMax[node * 2 + 1]);
	}

}
