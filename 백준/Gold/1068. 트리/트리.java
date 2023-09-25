import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		Node parent;
		List<Node> lower;

		public Node() {
			super();
			lower = new ArrayList<>();
		}

	}

	static int cnt;
	static List<Integer> roots;
	static Node[] nodes;
	static int K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken(" "));

		nodes = new Node[N];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new Node();
		}

		roots = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int a = Integer.parseInt(st.nextToken(" "));
			if (a != -1) {
				nodes[i].parent = nodes[a];
				nodes[a].lower.add(nodes[i]);
			} else {
				roots.add(i);
			}
		}

		K = Integer.parseInt(br.readLine());
//		if (K == root) {
//			System.out.println(cnt);
//			return;
//		}

		for (int i = 0; i < roots.size(); i++) {
			if (nodes[roots.get(i)] != null) {
				DFS(nodes[roots.get(i)]);
			}
		}

		System.out.println(cnt);
	} // main

	static void DFS(Node node) {
		if (node == nodes[K]) {
			return;
		}
		if (node.lower.isEmpty() || (node.lower.size() == 1 && node.lower.get(0) == nodes[K])) {
			++cnt;
			return;
		}

		for (int i = 0; i < node.lower.size(); i++) {
			DFS(node.lower.get(i));
		}
	}

}