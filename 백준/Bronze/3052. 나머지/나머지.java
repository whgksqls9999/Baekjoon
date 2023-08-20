import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[] arr = new int[42];
		for (int i = 0; i < 10; i++) {
			++arr[Integer.parseInt(br.readLine()) % 42];
		}
		int cnt = 0;
		for (int i : arr) {
			if (i >= 1) {
				++cnt;
			}
		}
		System.out.println(cnt);

	}// main

}
// class