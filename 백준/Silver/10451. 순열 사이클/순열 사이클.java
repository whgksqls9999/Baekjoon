import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		StringBuilder answer = new StringBuilder();
		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());

			List<Integer>[] nodes = new ArrayList[N + 1];
			for (int i = 1; i < nodes.length; i++) {
				nodes[i] = new ArrayList<>();
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int node = Integer.parseInt(st.nextToken());
				nodes[i + 1].add(node);
			}

			boolean[] visited = new boolean[N + 1];

			int cnt = 0;
			for (int i = 1; i < nodes.length; i++) {
				if (!visited[i]) {
					cnt++;
					BFS(nodes, visited, i);
				}
			}

			answer.append(cnt).append("\n");
		}
		System.out.print(answer);
	}

	public static void BFS(List<Integer>[] nodes, boolean[] visited, int node) {
		Queue<Integer> queue = new ArrayDeque<>();

		visited[node] = true;
		queue.add(node);

		while (!queue.isEmpty()) {
			int cur = queue.poll();

			for (int i : nodes[cur]) {
				if (!visited[i]) {
					queue.add(i);
					visited[i] = true;
				}
			}
		}
	}
}