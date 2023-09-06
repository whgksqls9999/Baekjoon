import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				if (o2.length() == o1.length()) { // 길이가 같을 때
					return o1.compareTo(o2);
				} else { // 길이가 다를 때
					return o1.length() - o2.length();
				}
			}
		});

		int N = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			pq.add(br.readLine().trim());
		}
		for (int i = 0; i < N; i++) {
			String a = pq.poll();
			if (!pq.isEmpty() && a.equals(pq.peek())) {
				continue;
			}
			if (a != null) {
				System.out.println(a);
			}
		}

	} // main
}
