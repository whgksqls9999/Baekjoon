import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();

		// 입력값에 0이 있는지 체크
		if (!zeroCheck(input) || !threeCheck(input)) {
			System.out.println(-1);
			return;
		}

		char[] charArr = input.toCharArray();
		Arrays.sort(charArr);

		StringBuilder answer = new StringBuilder();

		for (int i = 0; i < charArr.length; i++) {
			answer.append(charArr[charArr.length - 1 - i] - '0');
		}

		System.out.println(answer.toString());
	}

	public static boolean zeroCheck(String string) {
		boolean result = false;

		for (char c : string.toCharArray()) {
			if (c == '0') {
				result = true;
				break;
			}
		}

		return result;
	}

	public static boolean threeCheck(String string) {
		boolean result = false;

		int sum = 0;

		for (char c : string.toCharArray()) {
			sum += c - '0';
		}

		if (sum % 3 == 0) {
			result = true;
		}

		return result;
	}
}