import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.print(solution());
	}

	public static int solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		char[] type = new char[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			type[i] = st.nextToken().charAt(0);
		}

		List<int[]>[] nodes = new ArrayList[N + 1];
		for (int i = 1; i < nodes.length; i++) {
			nodes[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());

			nodes[start].add(new int[] { end, dist });
			nodes[end].add(new int[] { start, dist });
		}

		boolean[] visited = new boolean[N + 1];

		return prim(nodes, visited, type, N);

	}

	public static int prim(List<int[]>[] nodes, boolean[] visited, char[] type, int N) {
		Queue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});

		pq.add(new int[] { 1, 0 });

		int dist = 0;
		int connected = 0;
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			char curType = type[cur[0]];

			if (visited[cur[0]])
				continue;

			dist += cur[1];
			connected++;
			visited[cur[0]] = true;

			for (int[] i : nodes[cur[0]]) {
				if (!visited[i[0]] && curType != type[i[0]]) {
					pq.add(i);
				}
			}
		}

		if (connected != N) {
			dist = -1;
		}

		return dist;
	}
}