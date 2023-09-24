import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		int[] dp = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int max = 0;
		for (int i = 0; i < N; ++i) {
			dp[i] = 1;

			for (int j = 0; j < i; ++j) {

				// 나보다 작은 숫자가 앞에 존재하고
				// 해당 숫자를 최대값으로 하는 수열의 길이에 나를 포함시켰을 때
				// 그 수열의 길이가 지금 당장 내 수열의 길이보다 크다면
				if (arr[j] < arr[i] && dp[i] < dp[j] + 1) {

					// 나를 최대값으로 하는 수열로 들어간다
					dp[i] = dp[j] + 1;
				}
			}
			if (dp[i] > max) {
				max = dp[i];
			}
		}

		System.out.println(max);

		// 나보다 작은 값이라고 무조건 해당 수열 뒤쪽에 붙으면 안되고
		// 나보다 작으면서 해당 수열의 길이가 내 수열 길이보다 클 때 붙어야한다

		/*
		 * 
		 * 1 6 7 2 3 4 5 9
		 * 
		 * 
		 */
	} // main
}