import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		br.readLine();
		boolean[][] check = new boolean[2][26];

		for (char c : br.readLine().toCharArray()) {
			if (c >= 'a' && c <= 'z') {
				check[0][c - 'a'] = true;
			} else if (c >= 'A' && c <= 'Z') {
				check[1][c - 'A'] = true;
			}
		}

		String RAINBOW = "ROYGBIV";
		String rainbow = "roygbiv";

		int uCnt = 0;
		int lCnt = 0;

		for (int i = 0; i < rainbow.length(); ++i) {
			if (check[0][rainbow.charAt(i) - 'a']) {
				++lCnt;
			}
			if (check[1][RAINBOW.charAt(i) - 'A']) {
				++uCnt;
			}
		}

		if (lCnt == 7 && uCnt == 7) {
			System.out.print("YeS");
		} else if (lCnt == 7) {
			System.out.print("yes");
		} else if (uCnt == 7) {
			System.out.print("YES");
		} else {
			System.out.print("NO!");
		}
	}
}