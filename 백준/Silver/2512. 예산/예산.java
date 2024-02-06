import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		int[] arr = new int[N + 1];
		int sum = 0;
		int max = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum += arr[i];
			max = Math.max(max, arr[i]);
		}

		int M = Integer.parseInt(br.readLine());

		int l = 1;
		int r = M;

//		if (sum <= M) {
//			System.out.println(max);
//			return;
//		}

		while (l <= r) {
			int mid = (l + r) / 2;

			int cal = 0;

			for (int i : arr) {
				cal += Math.min(i, mid);
			}

			if (cal <= M) {
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		System.out.println(Math.min(max, r));
	}
}