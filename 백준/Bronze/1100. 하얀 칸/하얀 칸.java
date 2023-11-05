import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = 8;

		char[][] arr = new char[N][N];

		int cnt = 0;
		for (int i = 0; i < N; ++i) {
			String input = br.readLine();
			for (int j = 0; j < N; ++j) {
				if (((i + j) & 1) > 0)
					continue;
				if (input.charAt(j) == 'F') {
					++cnt;
				}
			}
		}
		System.out.println(cnt);
	}
}