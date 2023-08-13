import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// 각 사람들의 대기 시간을 입력받아 배열에 저장한다.
		// 대기 시간이 적은 사람부터 오름차순으로 정렬시킨다.
		// 배열을 누적합으로 만든다.
		// 배열의 합을 출력한다.

		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		} // 배열에 대기시간 저장

		Arrays.sort(arr); // 대기시간 오름차순 정렬

		int sum = arr[0];
		for (int i = 1; i < N; i++) {
			arr[i] += arr[i - 1];
			sum += arr[i];
		} // 누적합으로 만들며, sum에 더해준다

		System.out.println(sum); // 대기시간 누적합 배열의 마지막 인덱스 값 출력
	} // main
}