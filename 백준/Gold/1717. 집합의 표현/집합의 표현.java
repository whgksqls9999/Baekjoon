import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n; // 집합의 수 = n+1
	static int m; // 연산의 수
	static int[] nodes;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		nodes = new int[n + 1];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = i;
		} // 노드 배열 초기화

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 연산의 종류
			int start = Integer.parseInt(st.nextToken()); // 첫 노드
			int end = Integer.parseInt(st.nextToken()); // 두번째 노드
			if (a == 0) { // union 연산
				union(start, end);
			} else { // find 연산
				if (find(start) == find(end)) {
					sb.append("YES\n");
				} else {
					sb.append("NO\n");
				}
			}

		}
		System.out.println(sb);
	}// main

	static void union(int node1, int node2) {
		// 대표 노드 찾기
		node1 = find(node1);
		node2 = find(node2);
		if (node1 != node2) {
			nodes[node2] = node1;
		} // 두 노드의 대표노드가 다를 때만 연결시키기

	} // 입력 노드의 대표 노드를 찾아 연결시키는 union 연산

	static int find(int node) {
		if (nodes[node] == node) {
			return node;
		} else {
			return nodes[node] = find(nodes[node]);
		}
	} // 대표 노드를 찾는 find 연산

}