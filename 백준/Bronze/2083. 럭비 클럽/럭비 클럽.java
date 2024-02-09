import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder answer = new StringBuilder();

		while (true) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int age = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			if (age == 0) {
				break;
			}

			String part = "Junior";
			if (age > 17 || weight >= 80) {
				part = "Senior";
			}
			answer.append(name).append(" ").append(part).append("\n");
		}
		System.out.print(answer);
	}
}