import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int res = 0;

		while (N-- > 0) {
			String input = br.readLine();
			int due = Integer.parseInt(input.substring(2, input.length()));
			if (due <= 90) {
				++res;
			}
		}

		System.out.println(res);
	}
}