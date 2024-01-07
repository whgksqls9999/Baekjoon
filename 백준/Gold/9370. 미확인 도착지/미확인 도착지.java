import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static int n, m, t;
	public static int s, g, h;
	public static ArrayList<int[]>[] nodes;
	public final static int MAX_DIST = 5_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			// n, m, t : 교차로/도로/목적지 후보 개수
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());

			// s, g, h : 출발지, g/h : 지나간 교차로
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			nodes = new ArrayList[n + 1];
			for (int i = 1; i <= n; ++i) {
				nodes[i] = new ArrayList<int[]>();
			}

			for (int i = 0; i < m; ++i) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int dist = Integer.parseInt(st.nextToken());

				nodes[from].add(new int[] { to, dist });
				nodes[to].add(new int[] { from, dist });
			}

			// 다익스트라로 최단 경로 배열 뽑아내기
			int[][] distArr = new int[][] { dijkstra(s), dijkstra(g), dijkstra(h) };

			// 두 경유지 중 시작 지점과 가까운 경유지를 고르기
			int[] near, far;
			if (distArr[1][s] < distArr[2][s]) {
				near = new int[] { 1, g };
				far = new int[] { 2, h };
			} else {
				near = new int[] { 2, h };
				far = new int[] { 1, g };
			}

			// 1. start -> near -> far -> dest
			// 2. start -> dest -> near -> far
			ArrayList<int[]> answer = new ArrayList<>();
			for (int i = 0; i < t; ++i) {
				int cur = Integer.parseInt(br.readLine());
				// 1.
				int dist1 = distArr[0][near[1]] + distArr[near[0]][far[1]] + distArr[far[0]][cur];
				int dist2 = distArr[0][cur];
				if (dist1 <= dist2) {
					answer.add(new int[] { cur, dist1 });
				} else if (distArr[0][cur] < distArr[0][near[1]] && distArr[near[0]][s] > distArr[near[0]][cur]) {
					answer.add(new int[] { cur, distArr[0][cur] });
				}
			}
			Collections.sort(answer, (a, b) -> a[0] - b[0]);
			for (int[] i : answer) {
				sb.append(i[0]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

	public static int[] dijkstra(int num) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		int[] distArr = new int[n + 1];
		Arrays.fill(distArr, MAX_DIST);
		distArr[num] = 0;

		pq.add(new int[] { num, 0 });
		while (!pq.isEmpty()) {
			int[] now = pq.poll();

			for (int[] i : nodes[now[0]]) {
				int newDist = now[1] + i[1];
				if (newDist < distArr[i[0]]) {
					distArr[i[0]] = newDist;
					pq.add(new int[] { i[0], newDist });
				}
			}
		}

		return distArr;
	}

}
