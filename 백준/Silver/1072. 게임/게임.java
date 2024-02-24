import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long X = Long.parseLong(st.nextToken());
		long Y = Long.parseLong(st.nextToken());

		long rate = 100 * Y / X;

		if (rate >= 99) {
			System.out.println(-1);
			return;
		}

		long lo = 0, hi = 2_000_000_000;
		while (lo < hi) {
			long mid = lo + (hi - lo) / 2;

			long newRate = 100 * (Y + mid) / (X + mid);

			if (newRate <= rate) {
				lo = mid + 1;
			} else {
				hi = mid;
			}

		}
		System.out.println(hi);
	}
}