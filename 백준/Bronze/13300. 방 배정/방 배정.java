import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 2차원 배열로 학년, 성별로 나눠 저장
		// 탐색하면서 나누기로 방 개수 구하기

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] arr = new int[2][7];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a][b]++;
		} // 학년, 성별로 나누기
		int sum = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 1; j < 7; j++) {
				if (arr[i][j] != 0) {
					if (arr[i][j] < K || arr[i][j] > K) {
						sum += arr[i][j] / K + 1;
					} else {
						sum += arr[i][j] / K;
					}
				}
			}
		}
		System.out.println(sum);
	}
}
