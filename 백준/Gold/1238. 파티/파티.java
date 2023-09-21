import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, X;
	static List<int[]>[] toParty;
	static List<int[]>[] fromParty;
	static int[] nodes1;
	static int[] nodes2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		toParty = new ArrayList[N + 1];
		fromParty = new ArrayList[N + 1];

		for (int i = 1; i < N + 1; i++) {
			toParty[i] = new ArrayList<>();
			fromParty[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			toParty[a].add(new int[] { b, c });
			fromParty[b].add(new int[] { a, c });
		}

		nodes1 = new int[N + 1];
		nodes2 = new int[N + 1];
		for (int i = 1; i < nodes1.length; i++) {
			nodes1[i] = nodes2[i] = 10000001;
		}
		Dijkstra(X, true);
		Dijkstra(X, false);
		int[] sum = new int[N + 1];
		int max = 0;
		for (int i = 1; i < sum.length; i++) {
			sum[i] = nodes1[i] + nodes2[i];
			if (sum[i] > max) {
				max = sum[i];
			}
		}
		System.out.println(max);

	}// main

	static void Dijkstra(int a, boolean b) {
		Queue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
		pq.add(new int[] { a, 0 });

		nodes1[a] = 0;
		nodes2[a] = 0;

		while (!pq.isEmpty()) {
			int[] now = pq.poll();
			if (b) {
				for (int i = 0; i < toParty[now[0]].size(); i++) {
					int next = toParty[now[0]].get(i)[0];
					int dist = toParty[now[0]].get(i)[1];
					if (nodes2[now[0]] + dist < nodes2[next]) {
						pq.add(new int[] { next, dist });
						nodes2[next] = nodes2[now[0]] + dist;
					}
				}
			} else {
				for (int i = 0; i < fromParty[now[0]].size(); i++) {
					int next = fromParty[now[0]].get(i)[0];
					int dist = fromParty[now[0]].get(i)[1];
					if (nodes1[now[0]] + dist < nodes1[next]) {
						pq.add(new int[] { next, dist });
//						System.out.println(nodes2[now[0]] + " : " + dist + " : " + nodes2[next]);
						nodes1[next] = nodes1[now[0]] + dist;
					}
				}

			}
		}
	}

}