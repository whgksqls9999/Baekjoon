import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;

	static class Edge {
		int start, dest;
		long dist;

		public Edge(int start, int dest, long dist) {
			super();
			this.start = start;
			this.dest = dest;
			this.dist = dist;
		}

	}

	static int[] nodes;

	static Queue<Edge> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
//		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken()); // 건물 개수
		M = Integer.parseInt(st.nextToken()); // 도로 개수

		nodes = new int[N + 1];
		pq = new PriorityQueue<>(new Comparator<Edge>() {

			@Override
			public int compare(Main.Edge o1, Main.Edge o2) {
				return Double.compare(o1.dist, o2.dist);
			}
		});

		// 대표 노드 배열 초기화
		nodes = new int[N + 1];
		for (int i = 1; i < nodes.length; i++) {
			nodes[i] = i;
		}

		long maxDist = 0;

		// M 개의 도로를 pq에 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 시작지
			int b = Integer.parseInt(st.nextToken()); // 목표지점
			long c = Long.parseLong(st.nextToken()); // 가중치
			pq.add(new Edge(a, b, c));
			maxDist += c;
		}

		// 최소 비용의 간선들부터 꺼내서 모두 연결짓기
		long minDist = 0;
		while (!pq.isEmpty()) {

			// 간선을 꺼낸다
			Edge now = pq.poll();
			// 이미 연결되었는지 체크하기
			int start = find(now.start);
			int end = find(now.dest);

			// 이미 연결되었으면 넘기기
			if (start == end)
				continue;

			union(start, end);
			minDist += now.dist;
		}

		int rep = find(1);

		for (int i = 1; i < nodes.length; i++) {
			find(i);
			if (rep != nodes[i]) {
				System.out.println(-1);
				return;
			}
		}

		System.out.println(maxDist - minDist);

	} // main

	static void union(int a, int b) {
		if (a != b) {
			nodes[b] = a;
		}
	}

	static int find(int a) {
		if (nodes[a] == a) {
			return a;
		}
		return nodes[a] = find(nodes[a]);
	}
}