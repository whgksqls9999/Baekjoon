import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int N, M;
	public static int[] indegree;
	public static ArrayList<Integer>[] nodes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		nodes = new ArrayList[N + 1];
		for (int i = 1; i <= N; ++i) {
			nodes[i] = new ArrayList<>();
		}

		indegree = new int[N + 1];
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());

			int cur = Integer.parseInt(st.nextToken());
			for (int j = 0; j < cnt - 1; ++j) {
				int next = Integer.parseInt(st.nextToken());
				++indegree[next];
				nodes[cur].add(next);
				cur = next;
			}
		}

		System.out.print(solution());
	}

	public static String solution() {
		StringBuilder answer = new StringBuilder();
		Queue<Integer> queue = new ArrayDeque<>();
		int cntCheck = 0;

		for (int i = 1; i < indegree.length; ++i) {
			if (indegree[i] == 0) {
				queue.add(i);
				answer.append(i).append("\n");
			}
		}

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			++cntCheck;

			for (int i : nodes[cur]) {
				--indegree[i];
				if (indegree[i] == 0) {
					queue.add(i);
					answer.append(i).append("\n");
				}
			}
		}

		return cntCheck == N ? answer.toString() : "0";
	}
}
