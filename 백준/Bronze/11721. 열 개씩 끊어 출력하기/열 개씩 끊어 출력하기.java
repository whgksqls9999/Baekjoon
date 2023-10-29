import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String input = br.readLine();

		for (int i = 1; true; ++i) {
			if (input.length() < 10 * i) {
				sb.append(input.substring(0 + 10 * (i - 1)));
				break;
			} else {
				sb.append(input.subSequence(0 + 10 * (i - 1), 0 + 10 * i)).append("\n");
			}
		}
		System.out.print(sb);
	}
}