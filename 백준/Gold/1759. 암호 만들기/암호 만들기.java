import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static int N, M;
	public static Set<Character> set = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		set.add('a');
		set.add('e');
		set.add('i');
		set.add('o');
		set.add('u');

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		char[] charArr = new char[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < charArr.length; i++) {
			charArr[i] = st.nextToken().charAt(0);
		}

		Arrays.sort(charArr);

		List<String> stringList = new ArrayList<>();
		DFS(0, N, M, "", stringList, charArr);

		StringBuilder answer = new StringBuilder();
		for (String string : stringList) {
			answer.append(string).append("\n");
		}
		System.out.print(answer);
	}

	public static void DFS(int cur, int limit, int length, String password, List<String> stringList, char[] charArr) {
		if (lengthCheck(password, limit)) {
			if (conditionCheck(password)) {
				stringList.add(password);
			}
			return;
		}

		for (int i = cur; i < length; i++) {
			DFS(i + 1, limit, length, password + Character.toString(charArr[i]), stringList, charArr);
		}
	}

	public static boolean lengthCheck(String A, int B) {
		return A.length() == B;
	}

	public static boolean conditionCheck(String word) {
		int cnt = 0;

		for (int i = 0; i < word.length(); i++) {
			if (set.contains(word.charAt(i))) {
				cnt++;
			}
		}

		return word.length() >= cnt + 2 && cnt >= 1;
	}
}
