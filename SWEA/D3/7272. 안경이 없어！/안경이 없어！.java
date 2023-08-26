import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		tc: for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");
			st = new StringTokenizer(br.readLine());
			String a = st.nextToken();
			String b = st.nextToken();

			char[] A = a.toCharArray();
			char[] B = b.toCharArray();

			if (A.length != B.length) {
				sb.append("DIFF\n");
				continue;
			}

			for (int i = 0; i < A.length; i++) {
				int aa = Character.getNumericValue(A[i]);
				int bb = Character.getNumericValue(B[i]);
				if (aa == 12 || (aa >= 14 && aa <= 23) || (aa >= 28 && aa <= 35)) {
					A[i] = 'A';
				} else if (aa == 10 || aa == 13 || (aa >= 24 && aa <= 27)) {
					A[i] = 'B';
				} else {
					A[i] = 'C';
				}

				if (bb == 12 || (bb >= 14 && bb <= 23) || (bb >= 28 && bb <= 35)) {
					B[i] = 'A';
				} else if (bb == 10 || bb == 13 || (bb >= 24 && bb <= 27)) {
					B[i] = 'B';
				} else {
					B[i] = 'C';
				}
			}

			for (int i = 0; i < A.length; i++) {
				if (A[i] != B[i]) {
					sb.append("DIFF\n");
					continue tc;
				}
			}

			sb.append("SAME\n");
			// 12 ~ 23 / 28 ~ 35 => 구멍없는 수
			// 10 ~ 13 / 24 ~ 27 => 구멍 한개
			// 11 => 구멍 두개

		} // tc1
		System.out.println(sb);
	} // main
}
