import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static boolean[] visited;
	static ArrayList<Integer>[] node;
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// DFS 방식을 따라, 새로운 노드에 도착할 때 마다 count++
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());

		visited = new boolean[N + 1]; // 방문 배열
		node = new ArrayList[N + 1]; // 노드 배열

		for (int i = 1; i <= N; i++) {
			node[i] = new ArrayList<>();
		} // 노드 배열 초기화

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			node[start].add(end);
			node[end].add(start);
		} // 인접 노드 기입

		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				DFS(i);
			} // 방문한적 없는 노드라면, DFS 실행
			break; // 한번에 연결된 부분까지만 체크하고, 연결되지 않은 부분은 체크하지 않도록
		}

		System.out.println(count - 1); // count에 1번 PC도 포함되어, 하나를 빼준다.
	}

	static void DFS(int nd) {
		if (visited[nd]) {
			return;
		} // 기저조건. 방문한 적 있다면 빠져나가기
		visited[nd] = true;
		count++;
		for (int i : node[nd]) {
			if (!visited[i]) {
				DFS(i); // 해당 인접노드로 들어간다
			} // 인접 노드에 방문하지 않은 노드가 있다면
		}
	}
}
