import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// 1. 첫줄을 WBWBW로 시작할지 BWBW로 시작할지에 따른 칠 횟수 구하기
		// 2. 최소값 찾기

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] arr = new char[N][M];
		int[] times = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		int cnt1 = 0;
		int cnt2 = 0;
		int cntmin = 99999;
		int min = 99999;
		for (int k = 0; k <= N - 8; k++) {
			for (int l = 0; l <= M - 8; l++) {
				cnt1 = 0;
				cnt2 = 0;
				for (int i = k; i < k + 8; i++) {
					for (int j = l; j < l + 8; j++) {
						if (i % 2 == 0) {
							if (j % 2 == 0) {
								if (arr[i][j] != 'W') {
									// 1. WBWB 시작
									cnt1++;
								} else if (arr[i][j] == 'W') {
									// 2. BWBW 시작
									cnt2++;
								}
							} else {
								if (arr[i][j] != 'B') {
									cnt1++;
								} else if (arr[i][j] == 'B') {
									cnt2++;
								}
							}
						} else {
							if (j % 2 == 0) {
								if (arr[i][j] != 'B') {
									cnt1++;
								} else if (arr[i][j] == 'B') {
									cnt2++;
								}
							} else {
								if (arr[i][j] != 'W') {
									cnt1++;
								} else if (arr[i][j] == 'W') {
									cnt2++;
								}
							}

						}
					}
				}
				cntmin = Math.min(cnt1, cnt2);
				min = Math.min(cntmin, min);
			}
		}
		System.out.println(min);
	} // main
}
