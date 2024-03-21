import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.print(solution());
	}

	public static int solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		int[] arr = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int[][] dist = new int[N + 1][N + 1];
		for (int[] i : dist) {
			Arrays.fill(i, 15 * 100);
		}
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());

			dist[start][end] = distance;
			dist[end][start] = distance;
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				for (int k = 1; k <= N; k++) {
					if (j == k)
						continue;
					if (dist[j][i] + dist[i][k] < dist[j][k]) {
						dist[j][k] = dist[j][i] + dist[i][k];
					}
				}
			}
		}

		int result = 0;
		for (int i = 1; i <= N; i++) {
			int cnt = 0;
			for (int j = 1; j <= N; j++) {
				if (i == j) {
					dist[i][j] = 0;
				}

				if (dist[i][j] <= M) {
					cnt += arr[j];
				}
			}
			result = Math.max(cnt, result);
		}

		return result;
	}
}