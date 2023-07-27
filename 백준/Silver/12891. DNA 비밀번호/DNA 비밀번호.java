import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		char[] a = st.nextToken().toCharArray();
		
		int[] b = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			b[i] = Integer.parseInt(st.nextToken());
		}

		int start = 0;
		int end = 0;
		int[] c = new int[4];
		int cnt = 0;
		
		c[check(a[0])]++;

		while (end < S) {
			int f = 0;
			for (int i = 0; i < 4; i++) {
				if(c[i] >= b[i] && end-start+1==P) {
					f++;
				}
			}
			if (f==4) {
				cnt++;
			}
			if (end - start + 1 < P) {
				end++;
				c[check(a[end])]++;
			} else {
				c[check(a[start])]--;
				start++; end++;
				if (end == S) break;
				c[check(a[end])]++;
			}
			
		}
		System.out.println(cnt);
	}

	public static int check(char a) {
		int c = 0;
		switch (a) {
		case 'A':
			c = 0;
			break;
		case 'C':
			c = 1;
			break;
		case 'G':
			c = 2;
			break;
		case 'T':
			c = 3;
			break;
		}
		return c;
	}
}
