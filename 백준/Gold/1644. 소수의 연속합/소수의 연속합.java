import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
//		for (int k = 1; k <= 2000; k++) {
//			int N = k;
		if (N == 1) {
			System.out.println(0);
			return;
		}
			int cnt = 0;
			int[] arr = new int[N + 1];
			for (int i = 0; i <= N; i++) {
				arr[i] = i;
			}
			arr[1] = 0;
			for (int i = 2; arr[i] <= Math.sqrt(N); i++) {
				if (arr[i] == i) {
					for (int j = i + i; j <= N; j += i) {
						arr[j] = 0;
					}
				}
			}
			int start = 0;
			int end = 0;

			int aCnt = 0;
			for (int i : arr) {
				if (i != 0) {
					aCnt++;
				}
			}
			int idx = 0;
			int[] nArr = new int[aCnt];
			for (int i : arr) {
				if (i != 0) {
					nArr[idx++] = i;
				}
			}
			int sum = nArr[0];
			while (start < aCnt) {
				if (sum == N) {
					cnt++;
				}
				if (sum > N || end == aCnt - 1) {
					sum -= nArr[start];
					start++;
				} else {
					end++;
					if (end < aCnt) {
						sum += nArr[end];
					}
				}

			}

			System.out.println(cnt);
//		}
	}
}
