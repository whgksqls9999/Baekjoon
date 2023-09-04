import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static char[][] map;
	static int R, C, N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		// 맵 생성
		map = new char[R][];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		// 아스키 코드 연산 이용, 숫자 캐릭터가 저장된 부분에서 1씩 더해가며
		// 특정수가 되면 사방 폭탄을 제거
		for (int i = 1; i < N; i++) { // 시간의 흐름
			for (int j = 0; j < R; j++) {
				for (int k = 0; k < C; k++) {
					if (map[j][k] != '.') { // 해당 자리가 폭탄이라면
						map[j][k] += 1;
					} else {
						map[j][k] = 'N'; // 폭탄 설치
					}
				}
			}
			for (int j = 0; j < R; j++) {
				for (int k = 0; k < C; k++) {
					if (map[j][k] == 'Q') { // 해당 자리에 터질 폭탄이 존재한다면
						map[j][k] = '.'; // 해당 위치 폭탄을 터트림
						for (int l = 0; l < 4; l++) {
							int nr = j + dr[l];
							int nc = k + dc[l];
							if (nr >= 0 && nc >= 0 && nr < R && nc < C) {
								if (map[nr][nc] != '.' && map[nr][nc] != 'Q') { // 주위에 다른 폭탄이 있다면
									map[nr][nc] = '.'; // 그 폭탄도 터트림
								}
							}
						}
					}
				}
			}
		} // 시간 흐름
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] != '.') {
					System.out.print('O');
				} else {
					System.out.print('.');
				}
			}
			System.out.println();
		}
	} // main

}
