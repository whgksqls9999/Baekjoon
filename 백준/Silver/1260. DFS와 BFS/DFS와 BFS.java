import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static boolean[] visited;
	static ArrayList<Integer>[] nodes;
	static int[] visitArr;
	static int idx;
	static int N, M, R;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		visitArr = new int[N + 1];
		visited = new boolean[N + 1];
		nodes = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			nodes[i] = new ArrayList<>();
		} // 배열 초기화

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			nodes[start].add(end);
			nodes[end].add(start);
		} // 간선 추가

		for (int i = 1; i <= N; i++) {
			nodes[i].sort(new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					return o1 - o2;
				}
			});
		}

		DFS(R);
		sb.append("\n");
		visited = new boolean[N + 1];
		idx = 0;
		BFS(R);
		System.out.println(sb);
	}

	static void DFS(int i) {
		if (visited[i]) {
			return;
		}
		sb.append(i).append(" ");
		visited[i] = true;
		for (int j : nodes[i]) {
			if (!visited[j]) {
				DFS(j);
			}
		}

	}

	static void BFS(int i) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(i);
		visited[i] = true;
		while (!queue.isEmpty()) {
			int now = queue.poll();
			sb.append(now).append(" ");
			for (int j : nodes[now]) {
				if (!visited[j]) {
					queue.add(j);
					visited[j] = true;
				}
			}
		}
	}
}
