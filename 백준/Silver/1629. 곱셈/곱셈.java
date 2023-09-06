import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		long C = Long.parseLong(st.nextToken());
		System.out.println(sqrt(A, B, C));
	}// main

	static long sqrt(long a2, long b2, long c2) {
		if (b2 == 0) {
			return 1;
		}

		long tmp = sqrt(a2, b2 / 2, c2);
		if (b2 % 2 == 0) {
			return tmp * tmp % c2;
		} else {
			return (tmp * tmp % c2) * a2 % c2;
		}
	} // sqrt
}