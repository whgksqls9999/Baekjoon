import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int[] sArr = new int[7];
		int[] arr = new int[9];
		int idx = 0;
		// 둘을 제외하고 나머지의 합을 구한다.
		for (int i = 0; i < 9; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		outer: for (int i = 0; i < 9; i++) {
			for (int j = i + 1; j < 9; j++) {
				int sum = 0;
				if (j == i)
					continue;
				for (int k = 0; k < 9; k++) {
					if (k == i || k == j)
						continue;
					sum += arr[k];

				}
				if (sum == 100) {
//					System.out.println(i + " " + j);
					for (int k = 0; k < 9; k++) {
						if (k != i && k != j) {
							sArr[idx++] = arr[k];
						}
					}
					break outer;
				}
			}
		}
		for (int i = 0; i < 7 - 1; i++) {
			int minIdx = i;
			for (int j = i + 1; j < 7; j++) {
				if (sArr[j] < sArr[minIdx]) {
					minIdx = j;
				}
			}
			int tmp = sArr[i];
			sArr[i] = sArr[minIdx];
			sArr[minIdx] = tmp;
		}
		for (int i : sArr) {
			sb.append(i + "\n");
		}
		System.out.println(sb);
	}
}
