import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static boolean[] visited;
	static boolean check = false;
	static ArrayList<Integer>[] node;
	static int b;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// 모든 인접 리스트 형태로 그래프를 구현한다.
		// 관계를 구해야 하는 둘 중 한명의 DFS로 시작한다
		// 방문한 적이 없는 새 노드에 들어갈 때 cnt++;
		// 더 이상 갈 노드가 없어 빽 쳐야 할때는 cnt--;

		int n = Integer.parseInt(st.nextToken()); // 모든 사람의 수

		st = new StringTokenizer(br.readLine()); // 찾아야 하는 관계의 두 사람
		int a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine()); // 연결된 간선 수
		int m = Integer.parseInt(st.nextToken());

		visited = new boolean[n + 1];
		node = new ArrayList[n + 1];

		for (int i = 1; i <= n; i++) {
			node[i] = new ArrayList<>();
		} // 배열 초기화
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			node[start].add(end);
			node[end].add(start);
		}

		DFS(a,b,0);
		if (!check)
			System.out.println(-1);
	}

	static void DFS(int start, int end, int cnt) {
//		System.out.println(start + " " + end + " " + cnt);
		if (start==end) {
			System.out.println(cnt);
			check = true;
			return;
		}
		for (int i : node[start]) {
			int cc = 0;
			if (!visited[i]) {
				break;
			} else {
				cc++;
			}
			if (cc == node[start].size()) {
				return;
			}
		} // 막다른 길이라면 리턴,,
		visited[start] = true;
		for (int i : node[start]) {
			if (!visited[i]) {
				DFS(i,end,cnt+1);
			}
		}

	}
}
