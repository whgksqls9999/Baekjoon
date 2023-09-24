import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] arr;
	final static int INF = 10000001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		// 최단경로 담아줄 2차원 배열 선언
		arr = new int[N + 1][N + 1];

		// 인접 행렬 값 넣기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			// 최단거리 배열에도 a -> b에 c의 가중치 입력해두기
			if (arr[a][b] == 0) {
				arr[a][b] = c;
			} else {
				arr[a][b] = Math.min(arr[a][b], c);
			}

		}

		// 빈 공간은 모두 큰 값으로 넣어두기
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j || arr[i][j] != 0)
					continue;
				arr[i][j] = INF;
			}
		}

		// i -> K -> j 와 i -> j 의 거리를 비교해서 작은값으로 교체하기
		for (int K = 1; K <= N; ++K) {
			for (int i = 1; i <= N; ++i) {
				for (int j = 1; j <= N; ++j) {
					if (i == j)
						continue;
//					if (arr[i][j] > arr[i][K] + arr[K][j]) {
//						arr[i][j] = arr[i][K] + arr[K][j];
//					}
					arr[i][j] = Math.min(arr[i][j], arr[i][K] + arr[K][j]);
				}
			}
		}

		for (int i = 1; i < arr.length; ++i) {
			for (int j = 1; j < arr[i].length; ++j) {
				if (arr[i][j] == INF) {
					sb.append(0).append(" ");
					continue;
				}
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);

	} // main
}