import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int p = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());

		int a = (p + t) / w; // 수평 왕복횟수
		int b = (q + t) / h; // 수직 왕복횟수

		if (a % 2 == 0) { // 짝수번 => 왕복수
			a = (p + t) % w;
		} else { // 홀수번 => 단방향 이동이 있음
			a = w - (p + t) % w;
		}
		if (b % 2 == 0) { // 짝수번 => 왕복수
			b = (q + t) % h;
		} else { // 홀수번 => 단방향 이동이 있음
			b = h - (q + t) % h;
		}

		System.out.println(a + " " + b);

	}// main
}
