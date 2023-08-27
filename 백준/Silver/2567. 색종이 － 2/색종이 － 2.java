import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, 1, 0, -1 };

		Queue<int[]> queue = new LinkedList<>();
		int N = Integer.parseInt(st.nextToken());

		// x축 배열 / y축 배열 따로 만들기
		// 입력받은 x, y값에서 10씩 더한 값까지 해서 배열에 1로 체크
		// 각 배열에서 1인 값들을 다 더하고, 2를 곱하면 겉 둘레가 나옴....
		// 안쪽 둘레는 ?
		// 안쪽 둘레는 언제 생기지?
		// 40 * 색종이의 갯수에서 겹치는 부분을 빼준다.
		// 2차원 배열 생성
		int[][] arr = new int[100][100];
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			for (int j = a; j < a + 10; j++) {
				for (int k = b; k < b + 10; k++) {
					if (arr[j][k] == 1) { // 겹치는 부분이라면
						continue;
					}
					arr[j][k] = 1;
					queue.add(new int[] { j, k });
				}
			}
		} // 색종이 배열에 추가

		// 큐에서 1이었던 부분을 꺼내서, 사방탐색, 0의 숫자만큼 or 배열의 끝(테두리) 만큼 cnt ++
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			for (int i = 0; i < dr.length; i++) {
				int nr = now[0] + dr[i];
				int nc = now[1] + dc[i];
				if (nr >= 0 && nc >= 0 && nr < 100 && nc < 100) {
					if (arr[nr][nc] == 0) {
						cnt++;
					}
				} else if (nr == 0 || nc == 0 || nr == 100 || nc == 100) {
					cnt++;
				}

			}
		} // 색종이칸 주변을 탐색, 둘레를 결정하는 while문
		System.out.println(cnt);
	} // main
}
