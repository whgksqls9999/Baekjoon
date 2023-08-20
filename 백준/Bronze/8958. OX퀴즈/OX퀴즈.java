import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= N; tc++) {
			int cnt = 0;
			int streak = 0;
			String input = br.readLine();
			for (char i : input.toCharArray()) {
				if (i == 'O') {
					cnt += ++streak;
				} else {
					streak = 0;
				}
			}
			System.out.println(cnt);
		}
	}// main
}
