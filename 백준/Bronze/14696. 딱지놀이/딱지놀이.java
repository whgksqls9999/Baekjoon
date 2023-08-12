import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= N; i++) {
			int[] A = new int[5];
			int[] B = new int[5];
			for (int j = 0; j < 2; j++) {
				st = new StringTokenizer(br.readLine());
				int n = Integer.parseInt(st.nextToken());
				for (int k = 0; k < n; k++) {
					if (j == 0) {
						A[Integer.parseInt(st.nextToken())]++;
					} else {
						B[Integer.parseInt(st.nextToken())]++;
					}
				}
			} // 한 판 승부를 위해 각 배열 채우기
			for (int k = 0; k < 4; k++) {
//				System.out.println(A[A.length - 1 - k] + " : " + B[B.length - 1 - k]);
				if (A[A.length - 1 - k] > B[B.length - 1 - k]) {
					System.out.println("A");
					break;
				} else if (A[A.length - 1 - k] < B[B.length - 1 - k]) {
					System.out.println("B");
					break;
				}
				if (k == 3) {
					System.out.println("D");
				}
			}
		}
	}
}
