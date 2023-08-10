import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int idx;
	static int[] visited;
	static int F; // 건물의 총 층수
	static int S; // 현재 층수
	static int G; // 목적지 층수
	static int U; // 위로 U층 업
	static int D; // 아래로 D층 다운

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		visited = new int[F + 1];
		BFS(S, G);

	}

	static void BFS(int start, int end) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		visited[start] = 1;
		int now = 0;
		while (!queue.isEmpty() && now != end) {
			now = queue.poll();
			if (now + U <= F && (visited[now + U] == 0)) {
				visited[now + U] = visited[now] + 1;
				queue.add(now + U);
			} // 엘리베이터 UP 버튼. 방문한 적이 없고, 최고층보다 작거나 같다면 들어간다.
			if (now - D >= 1 && (visited[now - D] == 0)) {
				visited[now - D] = visited[now] + 1;
				queue.add(now - D);
			} // 엘리베이터 DOWN 버튼. 방문한 적이 없고, 최하층보다 크거나 같다면 들어간다.
		}
		if (visited[end] - 1 == -1) { // 목적지에 도착하지 못했을 때
			System.out.println("use the stairs");
		} else { // 목적지에 도착했을 때
			System.out.println(visited[end] - 1); // 처음 시작할때 가지고 있던 1은 빼준다.
		}

	}
}