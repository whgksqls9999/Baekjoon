import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Queue<Integer> queue = new LinkedList<>();
		
		int N = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= N; i++) {
			queue.add(i);
		}
		
		while (queue.size()>1) {
			queue.poll();
			int a = queue.poll();
			queue.add(a);
		}
		System.out.println(queue.peek());
	}
}
