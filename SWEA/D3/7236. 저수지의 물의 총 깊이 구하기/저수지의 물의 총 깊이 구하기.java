import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static String[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		Queue<int[]> queue = new LinkedList<>();

		int T = Integer.parseInt(st.nextToken());
		tc: for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");
			int N = Integer.parseInt(br.readLine());
			map = new String[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					String a = st.nextToken();
					map[i][j] = a;
					if (a.equals("W")) {
						queue.add(new int[] { i, j });
					}
				}
			} // 맵 생성 및 물 좌표 넣기
			int max = 0;
			while (!queue.isEmpty()) {
				int[] now = queue.poll();
				int cnt = 0;
				for (int i = 0; i < dr.length; i++) {
					int nr = now[0] + dr[i];
					int nc = now[1] + dc[i];
					if (nr >= 0 && nc >= 0 && nr < N && nc < N) {
						if (map[nr][nc].equals("W")) {
							cnt++;
						}
					}
				}
				if (cnt == 0) {
					cnt = 1;
				}
				max = Math.max(cnt, max);
			}
			sb.append(max + "\n");
		} // tc
		System.out.println(sb);
	} // main
}
