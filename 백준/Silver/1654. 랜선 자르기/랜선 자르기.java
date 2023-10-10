import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int K, N;
	static long num;

	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// K, N 입력
		K = Integer.parseInt(st.nextToken(" "));
		N = Integer.parseInt(st.nextToken(" "));

		// 랜선 길이 저장
		arr = new int[K];
		int max = 0;
		for (int i = 0; i < arr.length; ++i) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		parametricSearch(1, Integer.MAX_VALUE);

		System.out.println(num);
	}

	static void parametricSearch(long start, long end) {
		long length = (start + end) / 2;

		long cnt = 0;
		for (int i = 0; i < arr.length; i++) {
			cnt += arr[i] / length;
		}
//		System.out.println(start + " : " + end + " : " + cnt);

		if (start > end) {
			num = length;
			return;
		}

		if (cnt < N) {
			parametricSearch(start, length - 1);
		} else if (cnt >= N) {
			parametricSearch(length + 1, end);
		}
	}
}
