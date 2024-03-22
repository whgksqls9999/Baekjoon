import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.print(solution());
	}

	public static int solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int E = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int e = 1;
		int s = 1;
		int m = 1;
		int year = 1;
		while (true) {
			if (e == E && s == S && m == M) {
				return year;
			}

			e = e + 1 == 16 ? 1 : e + 1;
			s = s + 1 == 29 ? 1 : s + 1;
			m = m + 1 == 20 ? 1 : m + 1;
			year++;
		}
	}
}