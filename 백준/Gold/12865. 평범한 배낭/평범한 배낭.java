import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static class Props {
		int weight, value;

		public Props(int weight, int value) {
			super();
			this.weight = weight;
			this.value = value;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken()); // 물품의 수
		int K = Integer.parseInt(st.nextToken()); // 준서가 버틸 수 있는 무게

		Props[] props = new Props[N];

		int maxWeight = 0; // dp 배열 만들기 위한 변수 maxWeight

		// 객체 배열에 물건들 담아주기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			props[i] = new Props(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			if (props[i].weight > maxWeight) {
				maxWeight = props[i].weight;
			}
		}

		// dp 배열 생성
		int[][] dp = new int[N + 1][K + 1];
		int max = 0;

		for (int i = 1; i < dp.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				if (j < props[i - 1].weight) {
					dp[i][j] = dp[i - 1][j];
					if (dp[i][j] > max) {
						max = dp[i][j];
					}
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - props[i - 1].weight] + props[i - 1].value);
					if (dp[i][j] > max) {
						max = dp[i][j];
					}
				}
			}
		}

//		for (int i = 0; i < dp.length; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}

		System.out.println(max);
	}
}
