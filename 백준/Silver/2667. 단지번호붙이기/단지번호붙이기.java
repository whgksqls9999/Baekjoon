import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int[][] map;
	static boolean[][] visited;
	static int N;
	static int cnt;
	static int idx;
	static ArrayList<Integer> idxList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());

		// 배열 초기화
		map = new int[N][N];
		visited = new boolean[N][N];
		idxList = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String a = st.nextToken();
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(a.substring(j, j + 1));
			}
		} // 맵 설정

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && map[i][j] != 0) {
					cnt++;
					idx = 0;
					DFS(i, j);
					idxList.add(idx);
				}
			}
		}
		idxList.sort(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		sb.append(cnt).append("\n");
		for (int i = 0; i < idxList.size(); i++) {
			sb.append(idxList.get(i)).append("\n");
		}
		System.out.println(sb);
	}

	static void DFS(int x, int y) {
		if (visited[x][y]) {
			return;
		}
		idx++;
		visited[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
				if (map[nx][ny] != 0 && !visited[nx][ny]) {
					DFS(nx, ny);
				}
			}
		}

	}
}
