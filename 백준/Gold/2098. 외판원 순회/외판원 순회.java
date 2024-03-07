import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int INF = 1_000_000 * 16;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		// dp 배열 생성
		int[][] dp = new int[N][(1 << N) - 1];
		for (int[] i : dp) {
			Arrays.fill(i, -1);
		}

		// 인접 리스트 생성
		List<int[]>[] nodes = new ArrayList[N];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new ArrayList<>();
		}

		// 인접 리스트 만들기
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				int dist = Integer.parseInt(st.nextToken());

				// 도달할 수 없는 도시라면 넘기기
				if (dist == 0)
					continue;

				nodes[i].add(new int[] { j, dist });
			}

		}

		System.out.println(travel(nodes, dp, N, 0, 1));

	}

	public static int travel(List<int[]>[] nodes, int[][] dp, int N, int cur, int visited) {
		if (visited == (1 << N) - 1) {
			if (nodes[cur].get(0)[0] != 0)
				return INF;
			return nodes[cur].get(0)[1];
		}

		if (dp[cur][visited] != -1)
			return dp[cur][visited];
		dp[cur][visited] = INF;

		for (int i = 0; i < nodes[cur].size(); i++) {
			if ((visited & (1 << nodes[cur].get(i)[0])) == 0) {

				dp[cur][visited] = Math.min(dp[cur][visited],
						travel(nodes, dp, N, nodes[cur].get(i)[0], visited | (1 << nodes[cur].get(i)[0]))
								+ nodes[cur].get(i)[1]);
			}
		}

		return dp[cur][visited];
	}
}