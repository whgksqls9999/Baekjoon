import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		// sqrt(N) 까지 탐색해서 R*C = N인 R,C 고르기. R을 최대화

		// 동일 행에서 열로 진행하며 글씨 쓰기

		// 글씨 읽을땐 열 고정 행 순회하며 글씨 읽기

		String input = st.nextToken();

		int N = input.length();
		int R = 0;
		int C = 0;
		for (int i = 1; i <= Math.sqrt(N); i++) {
			if (N % i == 0) {
				R = i;
				C = N / i;
			}
		}

		char[][] arr = new char[R][C];

		for (int j = 0; j < C; j++) {
			for (int i = 0; i < R; i++) {
				arr[i][j] = input.charAt(i + j * R);
			}
		}

		for (int j = 0; j < R; j++) {
			for (int i = 0; i < C; i++) {
				sb.append(arr[j][i]);
			}
		}
		System.out.println(sb);
	} // main
}