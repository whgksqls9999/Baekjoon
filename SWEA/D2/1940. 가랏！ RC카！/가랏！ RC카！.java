import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");
			int velocity = 0; // 속도
			int dist = 0; // 거리
			int N = Integer.parseInt(br.readLine()); // 명령 수 N
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				switch (a) {
				case 1:
					velocity += Integer.parseInt(st.nextToken());
					break;
				case 2:
					velocity -= Integer.parseInt(st.nextToken());
					if (velocity < 0) {
						velocity = 0;
					}
					break;
				case 0:
					break;
				}
				dist += velocity;
			}
			sb.append(dist + "\n");
		} // tc
		System.out.println(sb);
	} // main
}
