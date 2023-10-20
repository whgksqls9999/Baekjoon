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
	static long[][] tree;
	static List<int[]> arr;
	static Map<Integer, Integer> comp;
	static int[] compOrigin;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			// 좌표 입려
			arr = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x1 = Integer.parseInt(st.nextToken());
				int y1 = Integer.parseInt(st.nextToken());
				int x2 = Integer.parseInt(st.nextToken());
				int y2 = Integer.parseInt(st.nextToken());

				arr.add(new int[] { x1, y1, y2 });
				arr.add(new int[] { x2, y2, y1 });
			}

			Collections.sort(arr, new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[1] - o2[1];
				}
			});

			comp = new HashMap<>();
			compOrigin = new int[arr.size()];

			int idx = -1;
			for (int i = 0; i < arr.size(); i++) {
				int[] now = arr.get(i);

				if (!comp.containsKey(now[1])) {
					comp.put(now[1], ++idx);
				}

				compOrigin[idx] = now[1];

				now[1] = comp.get(now[1]);
			}

			Collections.sort(arr, new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[0] - o2[0];
				}
			});

			int h = (int) Math.ceil(Math.log(N * 2) / Math.log(2));
			int size = 1 << h + 1;
			tree = new long[size][2];

			long res = 0;

			int[] pre = arr.get(0);

			for (int i = 0; i < arr.size(); i++) {
				int[] now = arr.get(i);

				int y1 = now[1];
				int y2 = comp.get(now[2]);

				res += tree[1][0] * (now[0] - pre[0]);

				if (y1 > y2) {
					update(1, 0, 2 * N - 1, y2, y1 - 1, -1);
				} else {
					update(1, 0, 2 * N - 1, y1, y2 - 1, 1);
				}

				pre = now;
			}
			sb.append(res).append("\n");
		}
		System.out.println(sb);
	}

	static void update(int node, int start, int end, int left, int right, int val) {
		if (end < left || right < start) {
			return;
		}

		int mid = (start + end) / 2;

		if (left <= start && end <= right) {
			tree[node][1] += val;
		} else {
			update(node * 2, start, mid, left, right, val);
			update(node * 2 + 1, mid + 1, end, left, right, val);
		}

		if (tree[node][1] != 0) {
			tree[node][0] = compOrigin[end + 1] - compOrigin[start];
		} else {
			if (start == end) {
				tree[node][0] = 0;
			} else {
				tree[node][0] = tree[node * 2][0] + tree[node * 2 + 1][0];
			}
		}
	}

}