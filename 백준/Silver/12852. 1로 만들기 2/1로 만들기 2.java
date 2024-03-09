import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		List<Integer> list = new ArrayList<>();

		BFS(N, list);
	}

	public static void BFS(int N, List<Integer> list) {
		Queue<Integer> queue = new ArrayDeque<>();

		int[][] dp = new int[N + 1][2];
		for (int[] i : dp) {
			i[1] = 1_000_000;
		}
		dp[N] = new int[] { N, 0 };

		queue.add(N);
//		list.add(N);

		while (!queue.isEmpty()) {
			int cur = queue.poll();

			if (cur == 1)
				continue;

			if (cur % 3 == 0) {
				if (dp[cur / 3][1] > dp[cur][1] + 1) {
					dp[cur / 3] = new int[] { cur, dp[cur][1] + 1 };
					queue.add(cur / 3);
				}
			}
			if (cur % 2 == 0) {
				if (dp[cur / 2][1] > dp[cur][1] + 1) {
					dp[cur / 2] = new int[] { cur, dp[cur][1] + 1 };
					queue.add(cur / 2);
				}
			}

			if (dp[cur - 1][1] > dp[cur][1] + 1) {
				dp[cur - 1] = new int[] { cur, dp[cur][1] + 1 };
				queue.add(cur - 1);
			}
		}

		StringBuilder answer = new StringBuilder();
		answer.append(dp[1][1]).append("\n");

		Stack<Integer> stack = new Stack<>();
		int next = 1;
		stack.add(next);

		while (next != dp[next][0]) {
			next = dp[next][0];
			stack.add(next);
		}

		while (!stack.isEmpty()) {
			answer.append(stack.pop()).append(" ");
		}
		System.out.println(answer);
	}
}
