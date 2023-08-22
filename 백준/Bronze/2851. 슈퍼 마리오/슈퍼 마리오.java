import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int[] arr = new int[10];
		boolean check = false;

		for (int i = 0; i < 10; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			if (i > 0) {
				arr[i] += arr[i - 1];
			}
		}

		for (int i = 0; i < 9; i++) {
			if (Math.abs(arr[i] - 100) < Math.abs(arr[i + 1] - 100)) {
				System.out.println(arr[i]);
				check = true;
				break;
			} else if (Math.abs(arr[i] - 100) == Math.abs(arr[i+1]-100)){
				System.out.println(arr[i+1]);
				check = true;
				break;
			}
		}
		if (!check) {
			System.out.println(arr[9]);
		}
	} // main
}