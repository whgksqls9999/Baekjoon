import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long N = Long.parseLong(st.nextToken());

		long sum = 0;
		int cnt = 0;
		for (long i = 1; sum != N; i++) {
			sum += i;
			cnt++;

			if (sum > N) {
				sum -= i - 1;
				cnt--;
			}
		}
		System.out.println(cnt);
	}
}