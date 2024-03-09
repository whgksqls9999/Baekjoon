import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String A = br.readLine().trim();
		String B = br.readLine().trim();

		int[][] checkArr = new int[A.length() + 1][B.length() + 1];

		int max = 0;
		for (int i = 1; i < checkArr.length; i++) {
			for (int j = 1; j < checkArr[i].length; j++) {
				if (A.charAt(i - 1) == B.charAt(j - 1)) {
					checkArr[i][j] = checkArr[i - 1][j - 1] + 1;
					max = Math.max(checkArr[i][j], max);
				}
			}
		}

		System.out.println(max);
	}
}
