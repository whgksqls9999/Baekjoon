import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int cnt = 0;
		int N = Integer.parseInt(st.nextToken());

		while (N >= 3) {
			if (N % 5 == 0) {
				int a = N / 5;
				cnt += a;
				N %= 5;
			} else {
				cnt++;
				N -= 3;
			}
		}

		if (N == 0) {
			System.out.println(cnt);
		} else {
			System.out.println(-1);
		}
	}// main

}
// class