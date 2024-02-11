import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();

		List<Integer> plus = new ArrayList<>();
		List<Integer> minus = new ArrayList<>();

		int start = 0;
		List<Integer> target = plus;
		for (int i = 1; i < input.length(); i++) {
			if (input.charAt(i) == '+') {
				target.add(Integer.parseInt(input.substring(start, i)));

				start = i + 1;
			} else if (input.charAt(i) == '-') {
				target.add(Integer.parseInt(input.substring(start, i)));

				start = i + 1;
				if (target == plus) {
					target = minus;
				}
			}
		}
		target.add(Integer.parseInt(input.substring(start, input.length())));

		int answer = 0;
		for (int i : plus) {
			answer += i;
		}
		for (int i : minus) {
			answer -= i;
		}

		System.out.print(answer);

	}
}