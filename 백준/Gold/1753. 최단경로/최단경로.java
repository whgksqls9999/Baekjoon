import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int V, E, K;
	static boolean[] visited;
	static int[] dist;
	static ArrayList<int[]>[] nodes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		V = Integer.parseInt(st.nextToken()); // 정점
		E = Integer.parseInt(st.nextToken()); // 간선

		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken()); // 시작 정점

		visited = new boolean[V + 1];
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
			nodes[a].add(new int[] { b, c });
		}

		for (int i = 1; i < dist.length; i++) {
			dist[i] = Integer.MAX_VALUE;
		}

		dist[K] = 0; // 시작 정점은 최소값으로 하기
		int minNode = 0;

		for (int i = 0; i < V; i++) { // 노드 수 만큼 반복
			int minVal = Integer.MAX_VALUE;
			for (int j = 1; j < V + 1; j++) {
				if (minVal > dist[j] && !visited[j]) {
					minVal = dist[j];
					minNode = j;
				}
			} // 최소 노드 찾기
			visited[minNode] = true;
			for (int[] j : nodes[minNode]) {
				if (dist[j[0]] > dist[minNode] + j[1]) {
					dist[j[0]] = dist[minNode] + j[1];
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