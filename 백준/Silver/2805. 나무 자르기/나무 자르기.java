import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken(" "));
		M = Integer.parseInt(st.nextToken(" "));

		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		parametricSearch(1, 2000000000);

	}

	static void parametricSearch(long start, long end) {
		long mid = (start + end) / 2;

		long get = 0;
		for (int i = 0; i < N; i++) {
			if (arr[i] > mid) {
				get += arr[i] - mid;
			}
		}

		if (start > end) {
			System.out.println(mid);
			System.exit(0);
		}

		if (get < M)
			parametricSearch(start, mid - 1);
		if (get >= M)
			parametricSearch(mid + 1, end);
	}
}