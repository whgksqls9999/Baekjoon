import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println(solution());
	}

	public static int solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());

		Map<Integer, List<int[]>> nodes = new HashMap<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());

			initMap(start, new int[] { end, dist }, nodes);
		}

		return BFS(0, D, nodes);
	}

	public static void initMap(int key, int[] value, Map<Integer, List<int[]>> map) {
		if (map.get(key) == null) {
			List<int[]> tmp = new ArrayList<>();
			tmp.add(value);
			map.put(key, tmp);
			return;
		}

		map.get(key).add(value);
	}

	public static int BFS(int start, int end, Map<Integer, List<int[]>> map) {
		Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}

		});

		queue.add(new int[] { 0, 0 });

		int dist = Integer.MAX_VALUE;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			if (cur[0] == end) {
				dist = Math.min(cur[1], dist);
				continue;
			}

			if (map.get(cur[0]) != null) {
				for (int[] i : map.get(cur[0])) {
					if (i[0] <= end) {
						queue.add(new int[] { i[0], cur[1] + i[1] });
					}
				}
			}

			queue.add(new int[] { cur[0] + 1, cur[1] + 1 });
		}

		return dist;
	}
}
