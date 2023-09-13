import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int parent, depth;

	public Node() {
		depth = 1;
	}

	public Node(int parent, int depth) {
		super();
		this.parent = parent;
		this.depth = depth;
	}

	@Override
	public String toString() {
		return "Node [parent=" + parent + ", depth=" + depth + "]";
	}

}

public class Main {
	static Node[] arr;
	static List<Integer>[] nodes;
	static boolean[] visited;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		arr = new Node[N + 1];
		nodes = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			nodes[i] = new ArrayList<>();
			arr[i] = new Node();
		}
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			nodes[a].add(b);
			nodes[b].add(a);
		}
		BFS(1);

		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(find(a, b)).append("\n");
		}
		System.out.println(sb);
	} // main

	static int find(int a, int b) {
		if (a == b)
			return a; // 찾는 두 노드가 같아지면 해당 노드 반환
		if (arr[a].depth > arr[b].depth) {
			return find(arr[a].parent, b);
		} else if (arr[a].depth < arr[b].depth) {
			return find(a, arr[b].parent);
		} else {
			return find(arr[a].parent, arr[b].parent);
		}
	}

	static void BFS(int a) {
		Queue<Integer> queue = new LinkedList<>();
		visited[a] = true;
		queue.add(a);
		while (!queue.isEmpty()) {
			int now = queue.poll();
			for (int i = 0; i < nodes[now].size(); i++) {
				if (!visited[nodes[now].get(i)]) {
					queue.add(nodes[now].get(i));
					visited[nodes[now].get(i)] = true;
					arr[nodes[now].get(i)].depth = arr[now].depth + 1;
					arr[nodes[now].get(i)].parent = now;
				}
			}
		}
	}
}
