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
	static List<int[]> arr;
	static long[] tree;
	static int MOD = 1_000_000_007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken(" "));
		arr = new ArrayList<>();
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken(" "));
			int y = Integer.parseInt(st.nextToken(" "));

			arr.add(new int[] { x, y });
		}

		// x축 정렬 - x축 좌표압축
		Collections.sort(arr, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		int max = 0;
		int idx = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.size(); i++) {
			int[] now = arr.get(i);
			if (!map.containsKey(now[0])) {
				map.put(now[0], ++idx);
			}
			now[0] = map.get(now[0]);
			max = Math.max(now[0], max);
		}

		Collections.sort(arr, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[1] - o1[1];
			}
		});

		int h = (int) Math.ceil(Math.log(N) / Math.log(2));
		int size = 1 << h + 1;

		tree = new long[size];

		int left = 0;
		int pre = arr.get(0)[1];
		int preX = arr.get(0)[0];
		int same = arr.get(0)[0];
		long res = 0;
		for (int i = 0; i < N; i++) {
			if (pre != arr.get(i)[1]) {
				left = 0;
				same = 0;
			}

			long l = query(1, 1, max, 1, arr.get(i)[0] - 1);

			long r = query(1, 1, max, arr.get(i)[0] + 1, max);

			update(1, 1, max, arr.get(i)[0]);

			if (preX < arr.get(i)[0] && pre == arr.get(i)[1]) {
				left += same;
				++left;
			} else if (preX == arr.get(i)[0] && pre == arr.get(i)[1]) {
//				left += same;
				++same;
			}

//			if (preX != arr.get(i)[0]) {
//				l = (l - left - same);
//			} else {
//				l = (l - left);
//			}
			l = (l - left);

			res = (res + l * r) % MOD;
//			System.out.println(l + " : " + r + " : " + res + " : " + left + " : " + same);
			pre = arr.get(i)[1];
			preX = arr.get(i)[0];
		}
		System.out.println(res);
	}

	static long query(int node, int start, int end, int left, int right) {
		if (end < left || right < start) {
			return 0;
		}

		if (left <= start && end <= right) {
			return tree[node];
		}

		int mid = (start + end) / 2;
		long l = query(node * 2, start, mid, left, right);
		long r = query(node * 2 + 1, mid + 1, end, left, right);
		return l + r;
	}

	static void update(int node, int start, int end, int idx) {
		if (idx < start || end < idx) {
			return;
		}

		if (start == end) {
			tree[node] = tree[node] + 1;
			return;
		}

		int mid = (start + end) / 2;
		update(node * 2, start, mid, idx);
		update(node * 2 + 1, mid + 1, end, idx);
		tree[node] = (tree[node * 2] + tree[node * 2 + 1]);
	}
}
