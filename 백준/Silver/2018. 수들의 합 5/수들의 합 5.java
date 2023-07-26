import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		int start = 1;
		int end = 1;
		int sum = 1;
		int cnt = 1;
		
		while (end != N){
			if (sum == N) {
				cnt++;
				sum += ++end;
			} else if (sum > N) {
				sum -= start++;
			} else {
				sum += ++end;
			}
		}


		System.out.println(cnt);
	}
}
