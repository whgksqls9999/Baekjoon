import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int V, E;
	static boolean[] finished;
	static int[] order;
	static int idx, parent;
	static List<Integer>[] nodes;
	static Queue<List<Integer>> SCC;
	static Stack<Integer> stack = new Stack<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		V = Integer.parseInt(st.nextToken(" "));
		E = Integer.parseInt(st.nextToken(" "));

		nodes = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			nodes[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken(" "));
			int end = Integer.parseInt(st.nextToken(" "));

			nodes[start].add(end);
		}

		SCC = new PriorityQueue<>(new Comparator<List<Integer>>() {

			@Override
			public int compare(List<Integer> o1, List<Integer> o2) {
				return o1.get(0) - o2.get(0);
			}
		});

		order = new int[V + 1];
		finished = new boolean[V + 1];

		for (int i = 1; i <= V; i++) {
			if (order[i] == 0) {
				DFS(i);
			}
		}

		sb.append(SCC.size()).append("\n");
		while (!SCC.isEmpty()) {
			List now = SCC.poll();

			for (int i = 0; i < now.size(); i++) {
				sb.append(now.get(i));

				if (i < now.size() - 1) {
					sb.append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static int DFS(int node) {
		// 스택에 넣기
		stack.push(node);

		// 방문 순서
		order[node] = ++idx;

		// 부모 = 자신으로
		parent = order[node];
		for (int i : nodes[node]) {
			if (order[i] == 0) {
				parent = Math.min(parent, DFS(i));
			} else if (!finished[i]) {
				parent = Math.min(parent, order[i]);
			}
		}

		if (parent == order[node]) {
			List<Integer> tmp = new ArrayList<>();

			int now = -1;
			while (true) {
				now = stack.pop();
				finished[now] = true;
				tmp.add(now);

				if (now == node)
					break;
			}

			tmp.sort((o1, o2) -> o1 - o2);

			tmp.add(-1);

			SCC.add(tmp);
		}

		return parent;
	}
}