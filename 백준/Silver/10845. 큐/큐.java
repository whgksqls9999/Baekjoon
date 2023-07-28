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
		int num = 0;
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			switch (st.nextToken()) {
			case "push":
				num = Integer.parseInt(st.nextToken());
				queue.add(num);
				break;
			case "pop":
				buffer.append(queue.isEmpty() ? -1+"\n" : queue.poll()+"\n");
				break;
			case "size":
				buffer.append(queue.size()+"\n");
				break;
			case "empty":
				buffer.append(queue.isEmpty() ? 1+"\n" : 0+"\n");
				break;
			case "front":
				buffer.append(queue.isEmpty() ? -1+"\n" : queue.peek()+"\n");
				break;
			case "back":
				buffer.append(queue.isEmpty() ? -1+"\n" : num+"\n");
				break;

			}
		}
		System.out.println(buffer);
	}
}
