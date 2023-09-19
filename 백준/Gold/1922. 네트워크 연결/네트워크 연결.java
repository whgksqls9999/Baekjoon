import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	int start, end, weight;

	public Edge(int start, int end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		return this.weight - o.weight;
	}

	@Override
	public String toString() {
		return "Edge [weight=" + weight + "]";
	}

}

public class Main {
	static int N, M;
	static Edge[] edges;
	static int[] nodes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(br.readLine());

		edges = new Edge[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(a, b, c);
		}

		nodes = new int[N + 1];
		for (int i = 1; i < nodes.length; i++) {
			nodes[i] = i;
		}

		Arrays.sort(edges);
//		System.out.println(Arrays.toString(edges));

		int MSTdist = 0;
		for (int i = 0; i < M; i++) {
			int start = edges[i].start;
			int end = edges[i].end;
			if (find(start) == find(end))
				continue;
			union(start, end);
			MSTdist += edges[i].weight;
		}
		System.out.println(MSTdist);
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a != b) {
			nodes[a] = b;
		}
	}

	static int find(int a) {
		if (nodes[a] == a) {
			return a;
		}
		return nodes[a] = find(nodes[a]);
	}
}