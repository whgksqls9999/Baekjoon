import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String A = br.readLine().trim();
		String B = br.readLine().trim();

		int max = 0;

		int[][] checkArr = new int[A.length() + 1][B.length() + 1];

		for (int i = 0; i < A.length(); i++) {
			for (int j = 0; j < B.length(); j++) {
				if (A.charAt(i) == B.charAt(j)) {
					checkArr[i + 1][j + 1] = checkArr[i][j] + 1;
				} else {
					checkArr[i + 1][j + 1] = Math.max(checkArr[i + 1][j], checkArr[i][j + 1]);
				}
				max = Math.max(max, checkArr[i + 1][j + 1]);
			}
		}

		System.out.print(max);
	}
}
