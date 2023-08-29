import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static StringBuilder sb = new StringBuilder();
	static char[][] map;
	static boolean[] visited;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
//		System.out.println(Character.getNumericValue('A')); 10 
//		System.out.println(Character.getNumericValue('Z')); 35
		map = new char[N][M];
		visited = new boolean[26];

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		DFS(0, 0, 1);
		System.out.println(max);
	} // main

	static void DFS(int r, int c, int idx) {
		int now = Character.getNumericValue(map[r][c]) - 10;
		if (visited[now]) {
			return;
		}
		if (idx > max) {
			max = idx;
		}
		visited[now] = true;
		for (int i = 0; i < dr.length; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr >= 0 && nc >= 0 && nr < N && nc < M) {
				if (!visited[Character.getNumericValue(map[nr][nc]) - 10]) {
					DFS(nr, nc, idx + 1);
					visited[Character.getNumericValue(map[nr][nc]) - 10] = false;
				}
			}
		}

	}
}
