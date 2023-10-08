import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Area {
	int people, parent, idx;
	List<Integer> connectTo;

	public Area(int people, int parent, int idx) {
		super();
		this.people = people;
		this.parent = parent;
		this.idx = idx;
		this.connectTo = new ArrayList<>();
	}

}

public class Main {
	static int N;
	static Area[] nodes;
	static boolean[] visited;
	static boolean[] check;
	static int min = Integer.MAX_VALUE;
	static boolean finalCheck;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());

		nodes = new Area[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < nodes.length; i++) {
			nodes[i] = new Area(Integer.parseInt(st.nextToken()), i, i);
		} // 도시들을 담아놓은 객체 배열

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			while (st.hasMoreTokens()) {
				int tmp = Integer.parseInt(st.nextToken());
				nodes[i].connectTo.add(tmp);
			}
		} // 도시간 상호 연결

		for (int i = 1; i <= Math.round((double) N / 2); i++) {
			visited = new boolean[N + 1];
			select(0, i);
		}

		if (finalCheck) {
			System.out.println(min);
		} else {
			System.out.println(-1);
		}
	} // main

	static void select(int depth, int limit) {
		if (depth == limit) {
			int cnt = 0;
			// BFS를 돌려, 집단이 단 두개인지 확인하자....
			check = new boolean[N + 1];
			for (int i = 1; i < visited.length; i++) {
				if (!check[i] && visited[i]) {
					cnt++;
					BFS(i, true);
				} else if (!check[i] && !visited[i]) {
					cnt++;
					BFS(i, false);
				}
			}

			if (cnt == 2) {
				int sum1 = 0;
				int sum2 = 0;
				for (int i = 1; i < visited.length; i++) {
					if (visited[i]) {
						sum1 += nodes[i].people;
					} else {
						sum2 += nodes[i].people;
					}
				}
				if (Math.abs(sum1 - sum2) < min) {
					min = Math.abs(sum1 - sum2);
					finalCheck = true;
				}
			}
		}

		for (int i = depth + 1; i <= N; i++) {
			// 1. for문 i 시작조건을 통해 이미 고른 도시는 거른다 (중복 x) 1-3 연결과 3-1 연결은 강음
			if (visited[i])
				continue;
			visited[i] = true;
			select(depth + 1, limit);
			visited[i] = false;
		}
	}

	static void BFS(int num, boolean ttt) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(num);
		check[num] = true;
		while (!queue.isEmpty()) {
			int now = queue.poll();
			for (int i = 0; i < nodes[now].connectTo.size(); i++) {
				int tmp = nodes[now].connectTo.get(i);
				if (ttt && !check[tmp] && visited[tmp]) {
					queue.add(tmp);
					check[tmp] = true;
				} else if (!ttt && !check[tmp] && !visited[tmp]) {
					queue.add(tmp);
					check[tmp] = true;
				}
			}
		}
	}
}
