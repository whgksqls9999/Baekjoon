import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	// 최단 경로 찾기
	static int N, M; // 장소의 수 N, 도로의 수 M
	static int S, D; // 시작점 S, 도착점 D
	static List<int[]>[] nodes; // 간선 정보 기록할 배열
	static List<Integer>[] nodesReverse;
	static int[] minDist; // 최단거리 기록할 배열

	// 최단 경로 간선 찾기
	static boolean[] used;
	static Stack<int[]> stack = new Stack<>();
	static List<int[]> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 1. 최단 경로를 구해본다
		// 1-1. 최단 경로가 없다면, -1 출력
		// 2. 최단 경로까지의 경로를 기억한 후 해당 간선을 제거한다
		// 3. 간선 제거 후 최단 경로를 구한다.
		// 3-1. 최단 경로가 없다면, -1 출력
		// 3-2. 최단 경로가 있다면, 해당 최단거리 출력
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			if (N == 0 && M == 0)
				break;

			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());

			nodes = new ArrayList[N];
			nodesReverse = new ArrayList[N];
			minDist = new int[N];
			used = new boolean[N];

			for (int i = 0; i < nodes.length; i++) {
				nodes[i] = new ArrayList<>();
				nodesReverse[i] = new ArrayList<>();
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()); // 시작
				int b = Integer.parseInt(st.nextToken()); // 도착
				int c = Integer.parseInt(st.nextToken()); // 가중치
				nodes[a].add(new int[] { b, c });
			}

			Dijkstra(S, 1);

			if (minDist[D] == 10000001) {
				sb.append(-1).append("\n");
				continue;
			}

			delete(D);

			Dijkstra(S, 0);

			if (minDist[D] == 10000001) {
				sb.append(-1).append("\n");
				continue;
			}
			sb.append(minDist[D]).append("\n");

		} // while
		System.out.println(sb);
	} // main

	static void Dijkstra(int num, int a) {
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return minDist[o1] - minDist[o2];
			}
		});
		pq.add(num);

		for (int i = 0; i < minDist.length; i++) {
			if (i != num) {
				minDist[i] = 10000001;
			}
		}

		while (!pq.isEmpty()) {
			int now = pq.poll();
			for (int i = 0; i < nodes[now].size(); i++) {
				int tmp = nodes[now].get(i)[0];

				int tmpDist = nodes[now].get(i)[1];
				if (minDist[tmp] > minDist[now] + tmpDist) {
					minDist[tmp] = minDist[now] + tmpDist;
					pq.add(tmp);
					if (a == 1) {
						nodesReverse[tmp].clear();
						nodesReverse[tmp].add(now);
					}
				} else if (a == 1 && minDist[tmp] == minDist[now] + tmpDist) {
					nodesReverse[tmp].add(now);
				}
			}
		}
	}

	static void delete(int node) {
		for (int i = 0; i < nodesReverse[node].size(); i++) {
			int tmp = nodesReverse[node].get(i);
			if (!used[tmp]) {
				used[tmp] = true;
				delete(tmp);
			}
			for (int j = 0; j < nodes[tmp].size(); j++) {
				if (nodes[tmp].get(j)[0] == node) {
					nodes[tmp].remove(j);
				}

			}
		}
	}
}
