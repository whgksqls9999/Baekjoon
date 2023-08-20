import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		boolean find = false;
		int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
		int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };
		// 오목판 배열을 만든다.
		// 오목판 배열에 돌을 입력한다.
		// 델타를 이용해 8방 탐색
		// 연속된 돌의 개수를 센다.
		// 6 이상이면
		int[][] arr = new int[19][19];

		for (int i = 0; i < 19; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 19; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int cnt = 0;
		outer: for (int j = 0; j < arr.length; j++) {
			for (int i = 0; i < arr.length; i++) {
				// 1일때, 2일때 로직은 같으나 분리
				if (arr[i][j] == 1) {
					// 델타를 이용해 연속된 돌을 찾는다
					for (int k = 0; k < dr.length; k++) {
						cnt = 1;
						int nr = i + dr[k];
						int nc = j + dc[k];
						while (nr >= 0 && nc >= 0 && nr < arr.length && nc < arr.length && arr[nr][nc] == 1) {
							// 해당 방향에서 연속된 돌을 찾았으면, 해당 방향으로 쭉 탐색해본다.
							cnt++;
							nr += dr[k];
							nc += dc[k];
						}
						// 반대 방향으로도 찾아준다.
						nr = i - dr[k];
						nc = j - dc[k];
						while (nr >= 0 && nc >= 0 && nr < arr.length && nc < arr.length && arr[nr][nc] == 1) {
							// 반대 방향으로도 연속된 돌을 찾는다.
							cnt++;
							nr -= dr[k];
							nc -= dc[k];
						}
						if (cnt == 5) {
							System.out.println(1);
							System.out.printf("%d %d\n", i + 1, j + 1);
							find = true;
							break outer;
						}
					}
				} else if (arr[i][j] == 2) {
					cnt++;
					// 델타를 이용해 연속된 돌을 찾는다
					for (int k = 0; k < dr.length; k++) {
						cnt = 1;
						int nr = i + dr[k];
						int nc = j + dc[k];
						while (nr >= 0 && nc >= 0 && nr < arr.length && nc < arr.length && arr[nr][nc] == 2) {
							// 해당 방향에서 연속된 돌을 찾았으면, 해당 방향으로 쭉 탐색해본다.
							cnt++;
							nr += dr[k];
							nc += dc[k];
						}
						// 반대 방향으로도 찾아준다.
						nr = i - dr[k];
						nc = j - dc[k];
						while (nr >= 0 && nc >= 0 && nr < arr.length && nc < arr.length && arr[nr][nc] == 2) {
							// 반대 방향으로도 연속된 돌을 찾는다.
							cnt++;
							nr -= dr[k];
							nc -= dc[k];
						}
						if (cnt == 5) {
							System.out.println(2);
							System.out.printf("%d %d\n", i + 1, j + 1);
							find = true;
							break outer;
						}
					}
				}
			}
		} // outer
		if (!find) {
			System.out.println(0);
		}

	}// main
}
