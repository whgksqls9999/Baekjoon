import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K, X;
	static ArrayList<Integer>[] nodes;
	static int[] visited;
	static int cnt;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken()); // 도시의 개수
		M = Integer.parseInt(st.nextToken()); // 도로의 개수
		K = Integer.parseInt(st.nextToken()); // 최단거리
		X = Integer.parseInt(st.nextToken()); // 출발 도시 번호

		nodes = new ArrayList[N + 1];
		visited = new int[N + 1];
		for (int i = 1; i < visited.length; i++) {
			visited[i] = -1;
		}
		for (int i = 1; i < nodes.length; i++) {
			nodes[i] = new ArrayList<>();
		} // 노드 배열 초기화

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			nodes[a].add(b);
		} // 간선 정보 추가
		BFS(X);
		if (pq.isEmpty()) {
			System.out.println(-1);
		} else {
			while (!pq.isEmpty()) {
				sb.append(pq.poll() + "\n");
			}
			System.out.println(sb);
		}
	} // main

	static void BFS(int num) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(num);
		visited[num] = 0;
		int a = cnt;
		while (!queue.isEmpty()) {
			int now = queue.poll();
			if (visited[now] == K) {
				pq.add(now);
			}
			for (int i : nodes[now]) {
				if (visited[i] == -1) {
					queue.add(i);
					visited[i] = visited[now] + 1;
				}
			}
		}
	}

}