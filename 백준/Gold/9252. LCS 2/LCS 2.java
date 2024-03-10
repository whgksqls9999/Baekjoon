import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static List<Integer> resList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String A = br.readLine().trim();
		String B = br.readLine().trim();

		int max = 0;

		int[][] checkArr = new int[A.length() + 1][B.length() + 1];
		int[] maxPos = new int[2];

		for (int i = 0; i < A.length(); i++) {
			for (int j = 0; j < B.length(); j++) {
				if (A.charAt(i) == B.charAt(j)) {
					checkArr[i + 1][j + 1] = checkArr[i][j] + 1;
				} else {
					checkArr[i + 1][j + 1] = Math.max(checkArr[i + 1][j], checkArr[i][j + 1]);
				}
				if (max < checkArr[i + 1][j + 1]) {
					max = checkArr[i + 1][j + 1];
					maxPos = new int[] { i + 1, j + 1 };
				}
			}
		}

		StringBuilder answer = new StringBuilder();
		answer.append(max).append("\n");

		StringBuilder LCS = new StringBuilder();

		DFS(maxPos[0], maxPos[1], checkArr, A, B, LCS);

		answer.append(LCS.reverse());
		System.out.print(answer);
	}

	public static void DFS(int r, int c, int[][] checkArr, String A, String B, StringBuilder LCS) {
		if (r == 0 || c == 0) {
			return;
		}

		if (checkArr[r][c] == checkArr[r - 1][c]) {
			DFS(r - 1, c, checkArr, A, B, LCS);
		} else if (checkArr[r][c] == checkArr[r][c - 1]) {
			DFS(r, c - 1, checkArr, A, B, LCS);
		} else {
			LCS.append(A.charAt(r - 1));
			DFS(r - 1, c - 1, checkArr, A, B, LCS);
		}
	}
}