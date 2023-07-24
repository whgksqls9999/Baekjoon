import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] a = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		int start = 0;
		int end = 0;
		int sum = 0;
		int cnt = 0;
		
		while (start < N) {
			if (sum > M || end == N) {
				sum -= a[start++];
			} else {
				sum += a[end++];
			}
			if (sum==M) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
