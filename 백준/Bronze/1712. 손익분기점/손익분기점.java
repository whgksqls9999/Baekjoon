import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long A = Integer.parseInt(st.nextToken());
		long B = Integer.parseInt(st.nextToken());
		long C = Integer.parseInt(st.nextToken());
		long i = A/C;
		if(B >= C) {
			bw.write(""+-1);
			bw.flush();
			return;
		}

		
		while(true) {

			if (sell(C,i) > use(A,B,i)) {
				bw.write(""+i);
				bw.flush();
				return;
			}
			i++;
		}
	}
	static long use(long A, long B, long i) {
		return A + B * i;
	}
	static long sell(long C, long i) {
		return C * i;
	}
}
