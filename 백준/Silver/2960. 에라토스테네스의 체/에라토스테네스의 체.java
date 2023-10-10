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

		int N = Integer.parseInt(st.nextToken(" "));
		int K = Integer.parseInt(st.nextToken(" "));

		int[] arr = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			arr[i] = i;
		}

		int cnt = 0;
		for (int i = 2; i <= N; ++i) {
//		for (int i = 2; i <= (int) Math.round((Math.sqrt(N + 1))); ++i) {
			if (arr[i] == i) {
				for (int j = i; j <= N; j += i) {
					if (arr[j] == 0) continue;
					++cnt;
					if (cnt == K) {
						System.out.println(arr[j]);
						return;
					}
					arr[j] = 0;
				}
			}
		}
	}
}
