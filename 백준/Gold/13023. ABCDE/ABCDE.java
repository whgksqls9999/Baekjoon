import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static boolean[] used;
	static List<Integer>[] nodes;
	static boolean check;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		nodes = new ArrayList[N];

		for (int i = 0; i < N; i++) {
			nodes[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			nodes[a].add(b);
			nodes[b].add(a);
		}
		for (int i = 0; i < N; i++) {
			used = new boolean[N];
			used[i] = true;
			DFS(i, 1);
			if (check)
				break;
		}
		if (!check) {
			System.out.println(0);
		} else {
			System.out.println(1);
		}
	} // main

	static void DFS(int idx, int depth) {
		if (depth == 5) {
			check = true;
			return;
		}
		for (int i = 0; i < nodes[idx].size(); i++) {
			if (used[nodes[idx].get(i)])
				continue;
			used[nodes[idx].get(i)] = true;
			DFS(nodes[idx].get(i), depth + 1);
			used[nodes[idx].get(i)] = false;
		}
	}
}
