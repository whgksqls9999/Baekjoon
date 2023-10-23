import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, Q;
	static List<Integer>[] area;
	static int[] need;
	static Queue<Integer>[] mid;
	static int[][] queries;
	static int[] lo, hi;
	static long[] tree;
	static boolean[] check;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		area = new ArrayList[N + 1];
		for (int i = 1; i <= N; ++i) {
			area[i] = new ArrayList<>();
		}
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= M; ++i) {
			int nation = Integer.parseInt(st.nextToken());
			area[nation].add(i);
		}

		need = new int[N + 1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; ++i) {
			need[i] = Integer.parseInt(st.nextToken());
		}

		Q = Integer.parseInt(br.readLine());
		queries = new int[Q + 1][3];
		for (int i = 1; i <= Q; ++i) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			queries[i] = new int[] { a, b, c };
		}

		lo = new int[N + 1];
		hi = new int[N + 1];

		Arrays.fill(lo, 1); // 1일부터 시작
		Arrays.fill(hi, Q + 1); // 최대 Q일, Q+1 그대로면 도달 못한것

		mid = new LinkedList[Q + 1];
		for (int i = 1; i <= Q; ++i) {
			mid[i] = new LinkedList<>();
		}

		check = new boolean[N + 1];

		while (true) {
			boolean finished = true;

			// 1. 트리 초기화
			tree = new long[M + 1];
//			for (int i = 1; i <= N; ++i) {
//				System.out.println(lo[i] + " : " + hi[i]);
//			}
//			System.out.println();
			// 2. 종료 조건
			for (int i = 1; i <= N; ++i) {
				if (lo[i] < hi[i]) {
					finished = false;
					mid[(lo[i] + hi[i]) / 2].add(i);
				}
			}

			if (finished)
				break;

			// 3. 트리 업데이트
			for (int i = 1; i <= Q; ++i) {
				int[] now = queries[i];
				int start = now[0];
				int end = now[1];
				int val = now[2];
				if (start > end) {
					rangeUpdate(start, M, val);
					rangeUpdate(1, end, val);
				} else {
					rangeUpdate(start, end, val);
				}

				while (!mid[i].isEmpty()) {
					int next = mid[i].poll();
					long sum = 0;
					for (int j : area[next]) {
						sum += query(j);
						if (sum >= need[next]) {
							break;
						}
					}

					if (sum >= need[next]) {
						hi[next] = i;
						check[next] = true;
					} else {
						lo[next] = i + 1;
					}
				}
			}
		}

		for (int i = 1; i <= N; ++i) {
			if (!check[i]) {
				sb.append("NIE").append("\n");
			} else {
				sb.append(hi[i]).append("\n");
			}
		}
		System.out.println(sb);
	}

	static long query(int point) {
		long res = 0;
		while (point > 0) {
			res += tree[point];
			point -= (point & -point);
		}
		return res;
	}

	static void rangeUpdate(int start, int end, long val) {
		update(start, val);
		update(end + 1, -val);
	}

	static void update(int start, long val) {
		while (start <= M) {
			tree[start] += val;
			start += (start & -start);
		}
	}
}
