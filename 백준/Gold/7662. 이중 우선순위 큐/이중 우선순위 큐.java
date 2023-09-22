import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			Queue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());
			Queue<Integer> minQueue = new PriorityQueue<>();
			Map<Integer, Integer> map = new HashMap<>();
			int N = Integer.parseInt(br.readLine());

			// 1. 삽입
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				String type = st.nextToken();
				int input = Integer.parseInt(st.nextToken());

				if (type.equals("I")) {
					// 해당 값이 저장된 횟수 + 1로 맵에 저장
					map.put(input, map.getOrDefault(input, 0) + 1);

					// 큐에 넣어주기
					maxQueue.add(input);
					minQueue.add(input);
				} else {
					// map이 비었다면 넘기기
					if (map.size() == 0)
						continue;

					Queue<Integer> tmp;

					// 1일 경우 최대값 빼기
					if (input == 1) {
						tmp = maxQueue;
					} else {
						tmp = minQueue;
					}

					removeNum(tmp, map);
				}

			}
			if (map.size() == 0) {
				sb.append("EMPTY").append("\n");
			} else {
				int max = removeNum(maxQueue, map);
				int min = map.size() == 0 ? max : removeNum(minQueue, map);
				sb.append(max).append(" ").append(min).append("\n");
			}
		} // tc
		System.out.println(sb);

	}// main

	static int removeNum(Queue<Integer> tmp, Map<Integer, Integer> tMap) {
		int num = 0;

		while (true) {

			// num : 큐에서 꺼내 삭제할 숫자
			num = tmp.poll();

			// 해당 숫자가 몇번 삽입되었는지 세어줄 cnt
			int cnt = tMap.getOrDefault(num, 0);

			// 큐에서 빼낸 숫자가 삭제할 숫자와 다를 경우
			// 큐에서 빼낸 숫자가 최대값 빼기로 maxQueue에는 더 이상 없는 숫자이나
			// minQueue에는 남아있는 경우
			if (cnt == 0)
				continue;

			// 해당 숫자가 1번만 삽입되었던 경우
			if (cnt == 1) {

				// 해당 숫자의 삽입 횟수를 삭제한다
				tMap.remove(num);
			} else {
				// 해당 숫자가 여러번 삽입되었던 경우
				// 하나를 차감시킨다
				tMap.put(num, cnt - 1);
			}
			break;
		}
		return num;
	}

}