import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int[][] map = new int[1001][1001];

		int N = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine()); // 두 좌표 입력받기
			int r1 = Integer.parseInt(st.nextToken());
			int c1 = Integer.parseInt(st.nextToken());
			int dr = Integer.parseInt(st.nextToken());
			int dc = Integer.parseInt(st.nextToken());
			for (int j = r1; j < r1 + dr; j++) {
				for (int k = c1; k < c1 + dc; k++) {
					map[j][k] = i;
				}
			} // 두 좌표 내 사각형을 칠한다.
		}
		for (int t = 1; t <= N; t++) {
			int sum = 0;
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					if (map[i][j] == t) {
						sum++;
					}
				}
			}
			System.out.println(sum);
		}
	}
}
