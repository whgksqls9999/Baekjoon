import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static Map<Integer, Integer> comp;
	static List<int[]> arr;
	static int[] tree;
	static int INF = 1_000_000_001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken(" "));

		for (int tc = 1; tc <= T; ++tc) {
			N = Integer.parseInt(br.readLine());

			comp = new HashMap<>();
			arr = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken(" "));
				int y = Integer.parseInt(st.nextToken(" "));

				arr.add(new int[] { x, y });
			}

			// y축 기준 정렬
			Collections.sort(arr, new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					return o2[1] - o1[1];
				}
			});

			// y축 좌표 압축
			int idx = -1;
			for (int i = 0; i < N; i++) {
				if (!comp.containsKey(arr.get(i)[1])) {
					comp.put(arr.get(i)[1], ++idx);
				}

				arr.get(i)[1] = comp.get(arr.get(i)[1]);
			}

			// x축 기준 정렬
			Collections.sort(arr, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[0] - o2[0];
				}
			});

			// 트리 만들기
			int h = (int) Math.ceil(Math.log(N) / Math.log(2));
			int size = 1 << h + 1;

			tree = new int[size];

			long res = 0;
			for (int i = 0; i < N; i++) {

				// y좌표를 기준으로 0~y좌표 내 합을 구하기
				res += query(1, 0, N - 1, 0, arr.get(i)[1]);

				// 구한 다음에 값 업데이트 해주기
				update(1, 0, N - 1, arr.get(i)[1]);
			}

			sb.append(res).append("\n");
		}
		System.out.println(sb);
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

	static void update(int node, int start, int end, int idx) {
		if (idx < start || end < idx) {
			return;
		}

		if (start == end) {
			tree[node] += 1;
			return;
		}

		int mid = (start + end) / 2;
		update(node * 2, start, mid, idx);
		update(node * 2 + 1, mid + 1, end, idx);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}
}
