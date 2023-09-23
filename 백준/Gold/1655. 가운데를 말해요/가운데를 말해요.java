import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 두 개의 우선순위 큐 사용 (maxQueue, minQueue)
// maxQueued에서 꺼낸 값이 항상 중간값이 되게 한다
public class Main {
	static Queue<Integer> maxQueue;

	static Queue<Integer> minQueue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		maxQueue = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		minQueue = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {

			// 숫자 입력받기
			int input = Integer.parseInt(br.readLine());

			// 1. 양쪽 큐의 사이즈를 비교한다
			// 1-1. 같으면 maxQueue에, 다르면 minQueue에 대입
			// 2. 대입 후 minQueue의 최솟값보다 input이 컸다면, minQueue maxQueue 전환

			// 1. 양쪽 큐의 사이즈가 같다면 maxQueue에 값 대입
			if (maxQueue.size() == minQueue.size()) {
				maxQueue.add(input);

				// maxQueue의 사이즈가 minQueue의 사이즈보다 크다면 minQueue에 대입
			} else {
				minQueue.add(input);
			}

			if (!maxQueue.isEmpty() && !minQueue.isEmpty()) {
				if (minQueue.peek() < maxQueue.peek()) {
					int tmp = maxQueue.poll();
					maxQueue.add(minQueue.poll());
					minQueue.add(tmp);
				}
			}

			sb.append(maxQueue.peek()).append("\n");
		} // for
		System.out.println(sb);
	}// main

}