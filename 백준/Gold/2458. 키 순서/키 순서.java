import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<Integer>[] nodes;
	static List<Integer>[] nodesReverse;
	static int N, M;
	static boolean[] visited;
	static int cnt;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		nodes = new ArrayList[N + 1];
		nodesReverse = new ArrayList[N + 1];
		for (int i = 1; i <= N; ++i) {
			nodes[i] = new ArrayList<>();
			nodesReverse[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			nodes[a].add(b);
			nodesReverse[b].add(a);
		}

		for (int i = 1; i <= N; ++i) {
			visited = new boolean[N + 1];
			cnt = 1;
			DFS(i, false);

			if (cnt == N)
				continue;
//			visited = new boolean[N + 1];
			DFS(i, true);
		}

		System.out.println(answer);
	}

	public static void DFS(int node, boolean isReverse) {
		visited[node] = true;

		if (cnt == N) {
			answer++;
			return;
		}

		if (!isReverse) {
			for (int i : nodes[node]) {
				if (visited[i])
					continue;
				cnt++;
				DFS(i, isReverse);
			}
		} else {
			for (int i : nodesReverse[node]) {
				if (visited[i])
					continue;
				cnt++;
				DFS(i, isReverse);
			}
		}
	}
}