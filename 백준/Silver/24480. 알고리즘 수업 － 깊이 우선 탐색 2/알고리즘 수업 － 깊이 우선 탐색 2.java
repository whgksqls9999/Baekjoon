import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static int[] visited;
	static ArrayList<Integer>[] nodes;
	static int idx = 1;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		nodes = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			nodes[i] = new ArrayList<>();
		} // 노드 배열 초기화

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			nodes[start].add(end);
			nodes[end].add(start);
		} //
		visited = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			nodes[i].sort(new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					return o2 - o1;
				}

			});
		} // 인접 노드 오름차순 정렬
//		for (int i = 1; i <= N; i++) {
//			System.out.println((nodes[i].toString()));
//		}

		DFS(R, 1);
		for (int i = 1; i < visited.length; i++) {
			sb.append(visited[i]).append("\n");
		}
		System.out.println(sb);
	}

	static void DFS(int nd, int cnt) {
		if (visited[nd] != 0) {
			return;
		}
		visited[nd] = idx++;
		for (int i : nodes[nd]) {
			if (visited[i] == 0) {
				DFS(i, cnt + 1);
			}
		}
	}

}