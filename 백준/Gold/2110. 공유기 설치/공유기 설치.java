import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int MAX = -1_000_000_000, MIN = 1_000_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(arr);

		int lo = 0, hi = 1_000_000_001;

		while (lo < hi) {
			int cur = arr[0];
			int mid = lo + (hi - lo) / 2;
			int diffCal = 1_000_000_000;

			int cnt = 1;
			for (int i = 1; i < arr.length; i++) {
				if (arr[i] >= cur + mid) {
					cnt++;
					cur = arr[i];
				}
			}

			if (cnt < C) {
				hi = mid;
			} else if (cnt >= C) {
				lo = mid + 1;
			}
		}

		System.out.println(lo - 1);
	}
}