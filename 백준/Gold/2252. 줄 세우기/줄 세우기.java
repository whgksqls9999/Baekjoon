import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int idx, val;

	public Node(int idx, int val) {
		super();
		this.idx = idx;
		this.val = val;
	}

}

public class Main {
	static int N, M;
	static List<Integer>[] nodes;
	static Node[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		Queue<Node> queue = new LinkedList<>();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		nodes = new ArrayList[N + 1];
		list = new Node[N + 1];

		for (int i = 1; i < nodes.length; i++) {
			nodes[i] = new ArrayList<>();
		}
		for (int i = 1; i < list.length; i++) {
			list[i] = new Node(i, 0);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			nodes[a].add(b);
		}

		for (int i = 1; i < nodes.length; i++) {
			for (int j : nodes[i]) {
				list[j].val++;
			}
		}

		for (int i = 1; i < nodes.length; i++) {
			if (list[i].val == 0) {
				queue.add(list[i]);
			}
		}

		while (!queue.isEmpty()) {
			Node now = queue.poll();
			for (int i = 0; i < nodes[now.idx].size(); i++) {
				Node tmp = list[nodes[now.idx].get(i)];
				tmp.val--;
				if (tmp.val == 0) {
					queue.add(tmp);
				}
			}
			sb.append(now.idx + " ");
		}

		System.out.println(sb);

	} // main

}