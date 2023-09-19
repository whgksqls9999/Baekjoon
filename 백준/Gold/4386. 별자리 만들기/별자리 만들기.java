import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

class Node {
	double x, y;
	int pre;

	public Node(double x, double y, int pre) {
		super();
		this.x = x;
		this.y = y;
		this.pre = pre;
	}

}

class Edge implements Comparable<Edge> {
	int start, end;
	double weight;

	public Edge(int start, int end, double weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		return (int) (this.weight * 100 - o.weight * 100);
	}

	@Override
	public String toString() {
		return Double.toString(weight);
	}

}

public class Main {
	static int N;
	static List<Edge> edges;
	static Node[] nodes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		nodes = new Node[N];
		edges = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			double a = Double.parseDouble(st.nextToken());
			double b = Double.parseDouble(st.nextToken());
			nodes[i] = new Node(a, b, i);
		}
		int idx = 0;
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				double dist = Math.sqrt((Math.pow(Math.abs(nodes[i].x - nodes[j].x), 2)
						+ Math.pow(Math.abs(nodes[i].y - nodes[j].y), 2)));
				edges.add(new Edge(i, j, dist));
			}
		}

		edges.sort(new Comparator<Edge>() {

			@Override
			public int compare(Edge o1, Edge o2) {
				return (int) (o1.weight * 100 - o2.weight * 100);
			}
		});

		double MINdist = 0;
		for (int i = 0; i < edges.size(); i++) {
			int start = edges.get(i).start;
			int end = edges.get(i).end;
			if (find(start) == find(end))
				continue;
			union(start, end);
			MINdist += edges.get(i).weight;
		}
		System.out.println(Math.round(MINdist * 100) / 100.0);
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a != b) {
			nodes[a].pre = b;
		}
	}

	static int find(int a) {
		if (nodes[a].pre == a) {
			return a;
		}
		return nodes[a].pre = find(nodes[a].pre);
	}
}