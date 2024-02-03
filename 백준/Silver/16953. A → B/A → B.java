import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int N, M;
	public static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		if (N * 2 > M) {
			System.out.println(-1);
			return;
		}
		BFS(N, M);

	}

	public static void BFS(int n, int m) {
		Queue<Integer> queue = new ArrayDeque<>();

		int answer = 1;
		queue.add(m);

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			if (cur == N) {
				System.out.println(answer);
				return;
			}
			if (cur < N) {
				break;
			}

			if (cur % 2 == 0) {
				answer++;
				queue.add(cur / 2);
			} else if (cur % 10 == 1) {
				answer++;
				queue.add((cur - 1) / 10);
			}
		}

		System.out.println(-1);
	}
}