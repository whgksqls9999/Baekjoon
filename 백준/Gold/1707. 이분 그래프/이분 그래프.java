import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static ArrayList<Integer>[] list;
	static int[] visited;
	static int idx;
	static boolean check;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) { // 테스트 케이스 받기
			check = true; // 이분 그래프인지 판별하는 boolean 초기화
			idx = 1; // 두 집합을 나눌 idx
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken()); // 정점 수
			int E = Integer.parseInt(st.nextToken()); // 엣지 수

			list = new ArrayList[V + 1]; // 1번 ~ V번 인덱스 활용 위해 V+1로 선언
			visited = new int[V + 1];

			for (int i = 1; i <= V; i++) {
				list[i] = new ArrayList<>();
			} // list 초기화

			for (int i = 0; i < visited.length; i++) {
				visited[i] = -1;
			} // visited 초기화

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				list[a].add(b);
				list[b].add(a);
			} // 인접 리스트 값 넣어주기

			for (int i = 1; i <= V; i++) {
				if (visited[i] == -1) {
					visited[i] = 1;
					DFS(i);
				}
				if (check == false)
					break;
			} // 모든 노드에 DFS 실행

			if (check) {
				sb.append("YES\n");
			} else {
				sb.append("NO\n");
			} // 판별 결과에 따른 출력

//			System.out.println(Arrays.toString(visited));
			// DFS 돌린다.
			// 싸이클이 만들어지지 않는다(=들렀던 곳을 방문하지 않는다.) => 이분 그래프
			// 싸이클이 만들어진다
			// 들릴 곳이 지금의 나와 같은 집합이다 => 이분그래프 X
			// 들릴 곳이 지금의 나와 같은 집합이 아니다 => 이분 그래프 O

		} // 테케
		System.out.println(sb);
	}// main

	static void DFS(int start) {

//		visited[start] = idx++ % 2;
		if (!list[start].isEmpty()) {
			for (int i : list[start]) {
				if (visited[i] == -1) {
					visited[i] = (visited[start] + 1) % 2;
					DFS(i);
				} else {
					if (visited[i] == visited[start]) {
						check = false;
					}
				}
			}
		}

	}

}