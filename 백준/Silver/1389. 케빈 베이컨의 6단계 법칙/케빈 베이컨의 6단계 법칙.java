import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int INF = 10000;

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] dist = new int[N + 1][N + 1];

		for (int i = 1; i < dist.length; ++i) {
			for (int j = 1; j < dist[i].length; ++j) {
				if (i == j) {
					continue;
				} else {
					dist[i][j] = INF;
				}
			}
		}

		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			dist[a][b] = 1;
			dist[b][a] = 1;
		}

		for (int i = 1; i < dist.length; ++i) {
			for (int j = 1; j < dist.length; ++j) {
				for (int k = 1; k < dist.length; ++k) {
					if (j == k)
						continue;

					if (dist[j][i] + dist[i][k] < dist[j][k]) {
						dist[j][k] = dist[j][i] + dist[i][k];
					}
				}
			}
		}
//		for (int i = 0; i < dist.length; ++i) {
//			System.out.println(Arrays.toString(dist[i]));
//		}

		int sum = INF;
		int idx = 0;
		for (int i = 1; i < dist.length; ++i) {
			int cnt = 0;
			for (int j : dist[i]) {
				cnt += j;
			}
//			System.out.println(cnt + " : " + idx + " : " + sum);
			if (cnt < sum) {
				sum = cnt;
				idx = i;
			}
		}
		System.out.println(idx);
	}
}