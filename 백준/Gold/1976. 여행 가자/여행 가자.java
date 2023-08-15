import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] nodes;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());

		nodes = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			nodes[i] = i;
		} // 배열 초기화

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int a = Integer.parseInt(st.nextToken());
				if (a == 1) {
					union(i, j);
					union(j, i);
				}
			}

		} // 입력받은 값을 토대로 union 연산 하기
		st = new StringTokenizer(br.readLine());
		int[] cities = new int[M + 1];
		for (int i = 1; i < cities.length; i++) {
			cities[i] = Integer.parseInt(st.nextToken());
		}
		boolean check = true;

		for (int i = 1; i < cities.length - 1; i++) {
			if (find(cities[i]) != find(cities[i + 1])) {
				check = false;
				break;
			}
		}
		if (check) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
//		System.out.println(Arrays.toString(cities));
	}// main

	static void union(int nodeA, int nodeB) {
		// 대표 노드 찾기
		nodeA = find(nodeA);
		nodeB = find(nodeB);
		if (nodeA != nodeB) {
			nodes[nodeA] = nodeB;
		} // 대표 노드가 다를 때만 연결시켜주기
	}

	static int find(int node) {
		if (nodes[node] == node) {
			return node;
		} else {
			return nodes[node] = find(nodes[node]);
		}
	}
} // class