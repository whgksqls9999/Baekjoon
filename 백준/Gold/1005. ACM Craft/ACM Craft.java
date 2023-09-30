import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Building {
		int idx;
		int time;
		int sum;
		int degree;
		List<Building> next;

		public Building(int idx, int time) {
			super();
			this.idx = idx;
			this.time = time;
			this.sum = time;
			next = new ArrayList<>();
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken(" ")); // 건물 수
			int K = Integer.parseInt(st.nextToken(" ")); // 규칙 개수

			Building[] buildings = new Building[N + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				buildings[i] = new Building(i, Integer.parseInt(st.nextToken(" ")));
			}

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken(" "));
				int end = Integer.parseInt(st.nextToken(" "));
				buildings[start].next.add(buildings[end]);
				++buildings[end].degree;
			}

			Queue<Building> queue = new LinkedList<>();

			for (int i = 1; i <= N; i++) {
				if (buildings[i].degree == 0) {
					queue.add(buildings[i]);
				}
			}

			Building[] order = new Building[N + 1];

			int idx = 1;
			while (!queue.isEmpty()) {
				Building now = queue.poll();
				for (Building b : now.next) {
					--b.degree;
					if (b.degree == 0) {
						queue.add(b);
					}
				}
				order[idx++] = now;
			}

			int W = Integer.parseInt(br.readLine());

			for (int i = 1; i < order.length; i++) {
				Building now = order[i];

				if (now.idx == W)
					break;
				for (Building b : now.next) {
					if (b.sum < now.sum + b.time) {
						b.sum = now.sum + b.time;
					}
				}
			}

			sb.append(buildings[W].sum).append("\n");
		}
		System.out.println(sb);
	}
}