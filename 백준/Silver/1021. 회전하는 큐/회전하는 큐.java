import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 1. 첫번째 원소를 뽑는다 => 찾는 값과 일치할 때. peekFirst() == 찾는값
		// 222. N - peekFirst() N - peekLast() 절대값 비교, 더 작은 방향으로
		// 2. 첫번째 원소를 뽑아 다시 뒤쪽으로 넣는다. => N-peekFirst() 절대값이 더 작을때
		// 3. 뒤쪽 원소를 뽑아 앞쪽으로 넣는다. => N-peekLast() 절대값이 더 작을때

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());

		List<Integer> list = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			list.add(i);
		}
		int tCnt = 0;
		for (int i = 0; i < M; i++) {
			int F = Integer.parseInt(st.nextToken());
			if (list.get(0) == F) {
				list.remove(0);
			} else if (Math.round((double) list.size() / 2) >= list.indexOf(F) + 1) {
				while (list.get(0) != F) {
					int n = list.remove(0);
					list.add(n);
					tCnt++;
				}
				list.remove(0);
			} else if (Math.round((double) list.size() / 2) < list.indexOf(F) + 1) {
				while (list.get(0) != F) {
					int n = list.remove(list.size() - 1);
					list.add(0, n);
					tCnt++;
				}
				list.remove(0);
			}
		}
		System.out.println(tCnt);
	}
}
