import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken()); // 스위치 개수 입력
		int[] arr = new int[N]; // 0 ~ N-1번 인덱스 활용 가능한 스위치 배열
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		} // 스위치 배열 값 할당하는 for문

		int P = Integer.parseInt(br.readLine()); // 사람 수 입력
		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 성별 입력. 1=남자 / 2=여자
			int b = Integer.parseInt(st.nextToken()); // 인덱스 + 1 의 값 입력
			arr = setArr(arr, a, b - 1);
		}
		for (int i = 0; i < arr.length; i++) {
			if (i % 20 == 0 && i != 0) {
				sb.append("\n");
			}
			sb.append(arr[i] % 2).append(" ");

		}
		System.out.println(sb);
	}// main

	// 성별에 따른 스위치 작동
	static int[] setArr(int[] arr, int a, int b) {
		if (a == 1) { // 남자일 경우
			int c = b + 1;
			do {
				++arr[b];
				b += c;
			} while (b < arr.length);
		} else { // 여자일 경우
			int i = 0;
			do {
				if (i == 0) {
					++arr[b];
					++i;
					continue;
				}
				if (arr[b - i] % 2 == arr[b + i] % 2) {
					++arr[b - i];
					++arr[b + i];
					++i;
				} else {
					break;
				}

			} while (b + i < arr.length && b - i >= 0);
		}
		return arr;
	}
}
