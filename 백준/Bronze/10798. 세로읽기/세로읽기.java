import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	

		char[][] strSet = new char[5][];
		for (int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			strSet[i] = st.nextToken().toCharArray();
		}

		// 2차원 배열 중 최대 길이 찾기
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < 5; i++) {
			if (strSet[i].length > max) {
				max = strSet[i].length;
			}
		}
		String result = "";
		// 반복
		for (int i = 0; i < max; i ++) {
			for (int j = 0; j < 5; j++) {
				if (i >= strSet[j].length) {
					continue;
				}
				result += "" + strSet[j][i];
			}
		}
		System.out.println(result);
	}
}
