import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static int[][] arr;
	public static long[] tree;
	public static Map<Integer, Integer> idxMap;
	public static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder answer = new StringBuilder();

		N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		idxMap = new HashMap<>();
		for (int i = 0; i < N; ++i) {
			idxMap.put(Integer.parseInt(st.nextToken()), i);
		}

		int h = (int) Math.ceil(Math.log(N) / Math.log(2));
		int size = 1 << h + 1;
		tree = new long[size];

		long ans = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			int idx = idxMap.get(Integer.parseInt(st.nextToken()));
			ans += query(1, 0, N - 1, idx, N - 1);
			update(1, 0, N - 1, idx - 1);
		}

		System.out.println(ans);
	}

	public static long query(int node, int start, int end, int left, int right) {
		if (right < start || end < left) {
			return 0;
		}

		if (left <= start && end <= right) {
			return tree[node];
		}

		int mid = (start + end) >> 1;
		long l = query(node << 1, start, mid, left, right);
		long r = query((node << 1) + 1, mid + 1, end, left, right);
		return l + r;
	}

	public static void update(int node, int start, int end, int idx) {
		if (idx < start || end < idx) {
			return;
		}

		if (start == end) {
			++tree[node];
			return;
		}

		int mid = (start + end) >> 1;
		update(node << 1, start, mid, idx);
		update((node << 1) + 1, mid + 1, end, idx);
		tree[node] = tree[node << 1] + tree[(node << 1) + 1];
	}
}