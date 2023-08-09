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
	static boolean[] visited;
	static ArrayList<Integer>[] nodes;
	static int[] visitArr;
	static int idx;
	static int N, M, R;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

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
					return o2 - o1;
				}
			});
		}
		BFS(R);
		for (int i = 1; i < visitArr.length; i++) {
			sb.append(visitArr[i] + "\n");
		}
		System.out.println(sb);
	}

	static void BFS(int i) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(i);
		visited[i] = true;
		visitArr[i] = ++idx;
		while (!queue.isEmpty()) {
			int now = queue.poll();
			for (int j : nodes[now]) {
				if (!visited[j]) {
					queue.add(j);
					visited[j] = true;
					visitArr[j] = ++idx;
				}
			}
		}
	}
}
