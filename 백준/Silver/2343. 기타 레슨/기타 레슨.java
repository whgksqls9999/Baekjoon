import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];

		int maxTime = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			maxTime = Math.max(arr[i], maxTime);
		}

		int lo = maxTime, hi = 10_000 * 100_000;

		while (lo < hi) {
			int mid = lo + (hi - lo) / 2;

			int cnt = 1;
			int sum = 0;
			for (int i = 0; i < arr.length; i++) {
				if (sum + arr[i] <= mid) {
					sum += arr[i];
					continue;
				}
				sum = arr[i];
				cnt++;
			}

			if (cnt > M) {
				lo = mid + 1;
			} else {
				hi = mid;
			}
		}
		System.out.println(hi);
	}
}