import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Edge {
		int start, end;
		long cost;

		public Edge(int start, int end, long cost) {
			super();
			this.start = start;
			this.end = end;
			this.cost = cost;
		}

	}

	static PriorityQueue<Edge> pq;
	static int[] nodes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		nodes = new int[N];
		for (int i = 1; i < N; i++) {
			nodes[i] = i;
		}

		int[][] adjArr = new int[N][N];
		pq = new PriorityQueue<>(new Comparator<Edge>() {

			@Override
			public int compare(Edge o1, Edge o2) {
				return Long.compare(o1.cost, o2.cost);
			}
		});

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				adjArr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j)
					continue;
				pq.add(new Edge(i, j, adjArr[i][j]));
			}
		}

		int connect = 0;
		long minCost = 0;
		while (connect != N - 1) {
			Edge now = pq.poll();
			int start = find(now.start);
			int end = find(now.end);

			if (start == end)
				continue;

			long cost = now.cost;

			union(start, end);
			connect++;
			minCost += cost;
		}
		System.out.println(minCost);

	} // main

	static void union(int a, int b) {
		nodes[b] = a;
	}

	static int find(int a) {
		if (nodes[a] == a) {
			return a;
		}
		return nodes[a] = find(nodes[a]);
	}

}