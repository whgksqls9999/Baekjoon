import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static boolean[] visited;
	static ArrayList<int[]>[] nodes;
	static int cnt;
	static int n;
	static int maxl;
	static int maxr;
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		visited = new boolean[n + 1];
		nodes = new ArrayList[n + 1];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new ArrayList<>();
		}

		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int[] b = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
			nodes[a].add(b);
			nodes[b[0]].add(new int[] { a, b[1] });
		}
		for (int i = 1; i <= n; i++) {
			DFS(i, 0);
			visited = new boolean[n + 1];
		}
		System.out.println(max);

	}// main

	static void DFS(int num, int cnt) {
		if (visited[num]) {
			return;
		}
		max = Math.max(cnt, max);
		visited[num] = true;
		for (int[] i : nodes[num]) {
			DFS(i[0], cnt + i[1]);
		}
	} // DFS
}