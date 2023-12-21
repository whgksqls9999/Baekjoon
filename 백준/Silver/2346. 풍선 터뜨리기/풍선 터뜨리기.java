import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		Deque<int[]> deque = new ArrayDeque<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			deque.addLast(new int[] {i+1, Integer.parseInt(st.nextToken())});
		}

		int dir = 1;
		int cnt = 0;
		while (true) {
			int[] cur;
			++cnt;
			if (dir > 0) {
				cur = deque.pollFirst();
			} else {
				cur = deque.pollLast();
			}
			
			sb.append(cur[0]).append(" ");
			if(cnt == N) {
				break;
			}

			if (cur[1] > 0) {
				dir = 1;
				for (int i = 1; i < cur[1]; ++i) {
					deque.addLast(deque.pollFirst());
				}
			} else {
				dir = -1;
				for (int i = 1; i < -cur[1]; ++i) {
					deque.addFirst(deque.pollLast());
				}
			}
		}
		System.out.println(sb);
	}
}