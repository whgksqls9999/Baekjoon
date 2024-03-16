import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static List<int[]>[] nodes;
	static int[] route;
	static int[] distArr;
	static List<Integer> stack = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(br.readLine());

		nodes = new ArrayList[N + 1];
		route = new int[N + 1];
		for (int i = 1; i < nodes.length; i++) {
			nodes[i] = new ArrayList<>();
		}
		distArr = new int[N + 1];
		Arrays.fill(distArr, 100000 * 1000);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			nodes[s].add(new int[] { e, d, 0 });
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		int answer = dijkstra(start, end);

		StringBuilder res = new StringBuilder();
		res.append(answer).append("\n");
		res.append(stack.size()).append("\n");
		for (int i = stack.size() - 1; i >= 0; i--) {
			res.append(stack.get(i)).append(" ");
		}
		System.out.print(res);
	}

	public static int dijkstra(int start, int end) {
		Queue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				if (o1[1] != o2[1]) {
					return o1[1] - o2[1];
				} else {
					return o1[0] - o2[0];
				}
			}
		});

		pq.add(new int[] { start, 0 });

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			if (cur[0] == end)
				break;

			for (int[] i : nodes[cur[0]]) {
				int next = i[0];
				int dist = i[1];

				if (cur[1] + dist < distArr[next]) {
					pq.add(new int[] { next, cur[1] + dist });
					distArr[next] = cur[1] + dist;
					route[next] = cur[0];
				}
			}
		}
		int now = end;
		stack.add(now);

		while (now != start) {
			stack.add(route[now]);

			now = route[now];
		}

		return distArr[end];
	}
}
