import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Integer>[] nodes;
	static boolean[] visited;
	static int[] parent;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());

		nodes = new ArrayList[N + 1];
		parent = new int[N + 1];
		visited = new boolean[N + 1];

		for (int i = 1; i < nodes.length; i++) {
			nodes[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			nodes[a].add(b);
			nodes[b].add(a);
		}
		BFS(1);
		for (int i = 2; i < parent.length; i++) {
			sb.append(parent[i]).append("\n");
		}
		System.out.println(sb);
	} // main

	static void BFS(int num) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(num);
		visited[num] = true;
		while (!queue.isEmpty()) {
			int now = queue.poll();
			for (int i : nodes[now]) {
				if (!visited[i]) {
					queue.add(i);
					visited[i] = true;
					parent[i] = now;
				}
			}
		}
	}

}