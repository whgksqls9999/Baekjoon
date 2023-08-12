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
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// 2차원 배열 map을 생성
		// 좌표를 입력받는다
		// for문을 통해 좌표 1에서 좌표 2까지 탐색해 map배열 내 해당 좌표의 인덱스를 1로 초기화
		// 배열의 모든 1을 더한다
		int[][] map = new int[101][101];

		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine()); // 두 좌표 입력받기
			int r1 = Integer.parseInt(st.nextToken());
			int c1 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			for (int j = r1; j < r2; j++) {
				for (int k = c1; k < c2; k++) {
					map[j][k] = 1;
				}
			} // 두 좌표 내 사각형을 칠한다. r1 이상 r2 미만
		}
		int sum = 0;
		for (int i = 1; i < map.length; i++) {
			for (int j = 1; j < map[i].length; j++) {
				sum += map[i][j];
			}
		} // 칠해진 곳의 값만 더한다.

		System.out.println(sum);
	}
}
