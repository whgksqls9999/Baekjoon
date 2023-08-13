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
		Object[][] arr = new Object[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			String b = st.nextToken();
			arr[i][0] = a;
			arr[i][1] = b;
		} // 입력 값 배열로 저장

		int max = 0;
		for (int i = 0; i < N; i++) {
			max = Math.max((Integer) arr[i][0], max);
		} // 카운팅 정렬을 위해 최대값 찾기

		int[] times = new int[max + 1]; // 빈도수 저장할 배열 선언

		for (int i = 0; i < N; i++) {
			times[(Integer) arr[i][0]]++;
		} // 빈도수 기록

		for (int i = 1; i < times.length; i++) {
			times[i] += times[i - 1];
		} // 빈도수를 누적합으로

		Object[][] arr2 = new Object[N][2];
		for (int i = 0; i < N; i++) {
			arr2[--times[(Integer) arr[arr.length - 1 - i][0]]] = arr[arr.length - 1 - i];
		}
		for (Object[] i : arr2) {
			sb.append(i[0] + " " + i[1] + "\n");
		}
		System.out.println(sb);
	} // main
}