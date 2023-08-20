import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 1*1부터 n*n개의 직사각형
		// a*b = b*a
		int N = Integer.parseInt(st.nextToken());

		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = i; i * j <= N; j++) {
				cnt++;
			}
		}
		System.out.println(cnt);

	}// main

}
// class