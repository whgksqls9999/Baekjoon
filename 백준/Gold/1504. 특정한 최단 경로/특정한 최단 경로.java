import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M; // 정점, 간선 개수
	static int[] minDist1; // 최소 거리 담을 1차원 배열
	static int[] minDist2;
	static List<int[]>[] nodes; // 간선, 가중치 정보 담을 배열
	static int mid1, mid2; // 거쳐가야 하는 중간지점

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		minDist1 = new int[N + 1];
		minDist2 = new int[N + 1];
		nodes = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			nodes[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			nodes[a].add(new int[] { b, c });
			nodes[b].add(new int[] { a, c });
		}

		st = new StringTokenizer(br.readLine());
		mid1 = Integer.parseInt(st.nextToken());
		mid2 = Integer.parseInt(st.nextToken());
		for (int i = 1; i < minDist1.length; i++) {
			minDist1[i] = 200000001;
			minDist2[i] = 200000001;
		}
		minDist1[mid1] = 0;
		minDist2[mid2] = 0;
//		System.out.println(Arrays.toString(minDist1));
//		System.out.println(Arrays.toString(minDist2));

		Dijkstra(mid1, minDist1);
		Dijkstra(mid2, minDist2);
//		System.out.println(Arrays.toString(minDist1));
//		System.out.println(Arrays.toString(minDist2));

		// 목적지에 도달하지 못하는 경우
		// 1. 1번 노드가 연결되지 않았을 때
		// 2. N번 노드와 연결되지 않았을 때
		// 3. 주어진 두 정점을 통과하지 못할 때
		if ((minDist1[1] == 200000001 || minDist2[1] == 200000001)
				|| (minDist1[N] == 200000001 || minDist2[N] == 200000001)) {
			System.out.println(-1);
		} else {
			int min1 = minDist1[1] + minDist1[mid2] + minDist2[N];
			int min2 = minDist2[1] + minDist2[mid1] + minDist1[N];
			System.out.println(Math.min(min1, min2));
		}
	} // main

	static void Dijkstra(int num, int[] arr) {
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return arr[o1] - arr[o2];
			}
		});
		pq.add(num);
		while (!pq.isEmpty()) {
			int now = pq.poll();
			for (int i = 0; i < nodes[now].size(); i++) {
				int tmp = nodes[now].get(i)[0]; // 다음 노드
				int tmpDist = nodes[now].get(i)[1]; // 다음 노드까지의 거리
				// 현재 기록된 거리보다 새로 계산한 거리가 짧다면 갱신
				if (arr[tmp] > arr[now] + tmpDist) {
					arr[tmp] = arr[now] + tmpDist;
					pq.add(tmp);
				}
			}
		}
	}
}
