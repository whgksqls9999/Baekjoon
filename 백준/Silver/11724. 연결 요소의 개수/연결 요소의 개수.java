import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static boolean[] visited;
	static ArrayList<Integer>[] node;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 정점의 개수
		int M = Integer.parseInt(st.nextToken()); // 간선의 개수

		visited = new boolean[N + 1]; // 방문 여부를 담을 배열 생성
		node = new ArrayList[N + 1]; // 인접 노드를 담아줄 노드 배열 생성

		for (int i = 1; i <= N; i++) {
			node[i] = new ArrayList<>();
		} // 노드 배열에 인접 노드를 담을 어레이리스트로 초기화

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			node[start].add(end);
			node[end].add(start);
		} // 각 노드의 어레이리스트에 인접 노드 담기
		int count = 0; // 연결 요소의 개수를 셀 카운트
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				count++;
				DFS(i);
			}
		}
		System.out.println(count);
	}

	static void DFS(int nd) {
		if (visited[nd]) {
			return;
		} // 만약 해당 노드에 방문한 적이 있다면, 메서드를 종료시킨다.
		visited[nd] = true;// 방문배열에 방문했음을 기록한다
		for (int i : node[nd]) {
			if (!visited[i]) {
				DFS(i); // DFS를 실행한다.
			} // 만약 방문하지 않은 인접노드가 있다면,
		} // 노드 nd와 인접한 노드들을 찾는 for
	}
}
