import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] visited; // 방문 배열
	static List<Integer> numArr = new ArrayList<>();
	static int N;
	static int K;
	static int idx = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// 스택에 현재 자리(num)를 넣고
		// 뺄 때, 다음 연결된 순서들을 (num-1, num *2, num+1)로 이어지게 한다.

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visited = new int[100001];

		BFS(N);
	}

	static void BFS(int num) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(num);
		visited[num] = 1;
		int now = 0;
		while (!queue.isEmpty()) {
			now = queue.poll();

			// 갈 수 있는 3개의 선택지 중 방문한 적 없는 선택지들을 큐에 넣는다
			if (now - 1 >= 0 && now - 1 < visited.length && visited[now - 1] == 0) {
				queue.add(now - 1);
				visited[now - 1] = visited[now] + 1;
			}
			if (now * 2 < visited.length && visited[now * 2] == 0) {
				queue.add(now * 2);
				visited[now * 2] = visited[now] + 1;
			}
			if (now + 1 < visited.length && visited[now + 1] == 0) {
				queue.add(now + 1);
				visited[now + 1] = visited[now] + 1;
			}

		}
		System.out.println(visited[K] - 1);

	}
}