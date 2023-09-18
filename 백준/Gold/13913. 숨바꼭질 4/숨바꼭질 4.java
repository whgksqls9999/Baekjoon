import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static int[] visited;
	static Deque<Integer> result = new LinkedList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		visited = new int[100001];

		if (N == K) {
			System.out.println(0);
			System.out.println(N);
			return;
		}
		chase(N);
	} // main

	static void chase(int num) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(num);
		visited[num] = 1;
		while (!queue.isEmpty()) {
			int now = queue.poll();
			for (int i = -1; i <= 1; i++) {
				int nx = now + i;
				if (nx == now) {
					nx *= 2;
				}
				if (nx >= 0 && nx <= 100000) {
					if (visited[nx] == 0) {
						queue.add(nx);
						visited[nx] = visited[now] + 1;
						if (nx == K) {
							result.addLast(num);
							trace(N, 1);
							return;
						}
					}
				}
			}
		}
	} // chase

	static void trace(int num, int depth) {
		if (depth == visited[K]) {
//			System.out.println(num + " : " + depth);
			if (num == K) {
				System.out.println(visited[K] - 1);
				while (!result.isEmpty()) {
					System.out.print(result.pollFirst() + " ");
				}
				System.exit(0);
			}
			return;
		}

		for (int i = -1; i <= 1; i++) {
			int next = num + i;
			if (next == num) {
				next *= 2;
			}
			if (next >= 0 && next <= 100000) {
				if (visited[next] == visited[num] + 1) {
					result.addLast(next);
					trace(next, depth + 1);
					result.pollLast();
				}
			}
		}
	}

}
