import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N, M;
	public static int[] arr;
	public static int[][] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		int h = (int) Math.ceil(Math.log(N) / Math.log(2));
		int size = 1 << h + 1;
		tree = new int[size][2];

		init(1, 1, N);

		StringBuilder answer = new StringBuilder();
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int[] res = query(1, 1, N, a, b);

			answer.append(res[1] - res[0]).append("\n");
		}

		System.out.print(answer);

	}

	public static void init(int node, int start, int end) {
		if (start == end) {
			tree[node][0] = arr[start];
			tree[node][1] = arr[start];
			return;
		}

		int mid = (start + end) >> 1;
		init(node * 2, start, mid);
		init(node * 2 + 1, mid + 1, end);
		tree[node][0] = Math.min(tree[node * 2][0], tree[node * 2 + 1][0]);
		tree[node][1] = Math.max(tree[node * 2][1], tree[node * 2 + 1][1]);
	}

	public static int[] query(int node, int start, int end, int left, int right) {
		if (right < start || end < left) {
			return new int[] { Integer.MAX_VALUE, 0 };
		}

		if (left <= start && end <= right) {
			return tree[node];
		}

		int mid = (start + end) >> 1;
		int[] l = query(node * 2, start, mid, left, right);
		int[] r = query(node * 2 + 1, mid + 1, end, left, right);
		return new int[] { Math.min(l[0], r[0]), Math.max(l[1], r[1]) };
	}
}