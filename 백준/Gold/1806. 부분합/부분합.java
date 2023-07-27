import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int[] a = new int[N];	
		int S = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < N; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0;
		int end = 0;
		int sum = a[0];
		int len = 100000;

		while (end != N && start != N) { // end가 S가 되면, 더 이상 sum을 증가시킬 수 없으니, 종료돼도 무방
//			System.out.println(start + " " + end + " " + sum);
			if (sum >= S && end < N) {
//				len = Integer.min(len, end - start + 1);
				len = len > (end-start+1)? end-start+1: len;
				sum -= a[start++];
			} else if (sum < S) {
				end++;
				if (end == N) break;
				sum += a[end];
			}
		}
		if (len == 100000) {
			System.out.println(0);
		} else {
			System.out.println(len);
		}
	}
}
