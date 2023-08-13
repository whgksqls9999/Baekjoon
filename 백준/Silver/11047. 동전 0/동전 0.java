import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		for (int i = N - 1; i >= 0; i--) {
			arr[i] = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		} // 배열에 동전의 가치 할당

		int cnt = 0; // 동전의 개수
//		while (K != 0) {
		for (int i : arr) {
			if (K >= i) {
				cnt += K / i;
				K %= i;
			}
		}
//		}
		System.out.println(cnt);

	} // main
}