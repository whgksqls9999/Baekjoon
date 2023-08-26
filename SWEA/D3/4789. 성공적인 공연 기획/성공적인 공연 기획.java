import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());
		tc: for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");
			// 기립박수 하는 사람을 누적합으로 표현
			// 누적합 배열의 모든 인덱스에서, N-1 인덱스에서의 값은 N보다 크거나 같아야함
			String[] input = br.readLine().split("");
			int[] arr = new int[input.length];
			arr[0] = Integer.parseInt(input[0]);
			for (int i = 1; i < arr.length; i++) {
				arr[i] = arr[i - 1] + Integer.parseInt(input[i]);
			} // 누적합 배열
			if (input.length == 1) {
				sb.append(0 + "\n");
				continue;
			}
			int cnt = 0;
			for (int i = 1; i < arr.length; i++) {
				arr[i - 1] += cnt;
				if (arr[i - 1] < i) {
					cnt += i - arr[i - 1];
				}
			}
			sb.append(cnt + "\n");

		} // tc
		System.out.println(sb);
	} // main
}