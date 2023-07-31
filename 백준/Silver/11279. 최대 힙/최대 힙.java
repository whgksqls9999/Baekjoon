import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			if (num != 0) {
				pq.add(num);
			} else if (num == 0) {
				if (pq.isEmpty()) {
					sb.append(0+"\n");
				} else {
					sb.append(pq.poll()+"\n");
				}
			}
		}
		System.out.println(sb);
	}
}
