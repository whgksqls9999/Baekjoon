import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		} // 배열 초기화

		st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		int[] arrT = new int[T];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < T; i++) {
			arrT[i] = Integer.parseInt(st.nextToken());
		} // 찾아야 하는 값들을 배열로 저장

		Arrays.sort(arr);
		for (int f : arrT) {
			int srt = 0;
			int ed = N - 1;
			int mid = (srt + ed) / 2;
			while (srt <= ed) {
				if (arr[mid] == f) {
					sb.append(1).append("\n");
					break;
				} // 해당 값을 찾았다면, 1을 출력하고 다음 수를 찾으러 간다
				if (arr[mid] < f) {
					srt = mid + 1;
				} else if (arr[mid] > f) {
					ed = mid - 1;
				}
				mid = (srt + ed) / 2;
			} // while문
			if (arr[mid] != f) {
				sb.append(0).append("\n");
			}
		} // 찾아야 하는 값이 저장된 배열을 순회하는 for문
		System.out.println(sb);

	}
}