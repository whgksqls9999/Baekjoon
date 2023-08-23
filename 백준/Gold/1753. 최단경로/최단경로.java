import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
	int idx, cost;

	public Node(int idx, int cost) {
		super();
		this.idx = idx;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		return cost - o.cost;
	}

}

public class Main {
	static int V, E, K;
	static boolean[] visited;
	static int[] dist;
	static ArrayList<Node>[] nodes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		V = Integer.parseInt(st.nextToken()); // 정점
		E = Integer.parseInt(st.nextToken()); // 간선

		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken()); // 시작 정점

//		visited = new boolean[V + 1];
		nodes = new ArrayList[V + 1];
		dist = new int[V + 1];
		for (int i = 1; i < nodes.length; i++) {
			nodes[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			nodes[a].add(new Node(b, c));
		}

		for (int i = 1; i < dist.length; i++) {
			dist[i] = Integer.MAX_VALUE;
		}

		dist[K] = 0; // 시작 정점은 최소값으로 하기
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(K, 0));

		while (!pq.isEmpty()) {
			Node now = pq.poll();
			if (dist[now.idx] < now.cost) {
				continue;
			}

			for (int i = 0; i < nodes[now.idx].size(); i++) {
				Node tmp = nodes[now.idx].get(i);
				if (dist[tmp.idx] > now.cost + tmp.cost) {
					dist[tmp.idx] = now.cost + tmp.cost;
					pq.offer(new Node(tmp.idx, dist[tmp.idx]));
				}
			}

		}

		for (int i = 1; i < dist.length; i++) {
			if (dist[i] != Integer.MAX_VALUE) {
				sb.append(dist[i] + "\n");
			} else {
				sb.append("INF\n");
			}
		}
		System.out.println(sb);
	} // main
}