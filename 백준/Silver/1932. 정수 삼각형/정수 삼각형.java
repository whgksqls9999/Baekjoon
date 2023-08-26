import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j <= i; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (j == 0 && i - 1 >= 0) {
					arr[i][j] += arr[i - 1][j];
				}
			}
		}

		if (n == 1) {
			System.out.println(arr[0][0]);
			return;
		}

		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j <= i; j++) {
				arr[i][j] += Math.max(arr[i - 1][j - 1], arr[i - 1][j]);
			}
		}

		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(max, arr[n - 1][i]);
		}
		System.out.println(max);
	} // main
}
