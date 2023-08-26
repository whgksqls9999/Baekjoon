import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		String input = st.nextToken();
		int cnt = 0;
		for (int i = 0; i < input.length(); i++) {
			char a = input.charAt(i);
			switch (a) {
			case 'c':
				if (i + 1 < input.length() && input.charAt(i + 1) == '=') {
					i += 1;
				} else if (i + 1 < input.length() && input.charAt(i + 1) == '-') {
					i += 1;
				}
				cnt++;
				break;
			case 'd':
				if (i + 2 < input.length() && input.charAt(i + 1) == 'z' && input.charAt(i + 2) == '=') {
					i += 2;
				} else if (i + 1 < input.length() && input.charAt(i + 1) == '-') {
					i += 1;
				}
				cnt++;
				break;
			case 'l':
				if (i + 1 < input.length() && input.charAt(i + 1) == 'j') {
					i += 1;
				}
				cnt++;
				break;
			case 'n':
				if (i + 1 < input.length() && input.charAt(i + 1) == 'j') {
					i += 1;
				}
				cnt++;
				break;
			case 's':
				if (i + 1 < input.length() && input.charAt(i + 1) == '=') {
					i += 1;
				}
				cnt++;
				break;
			case 'z':
				if (i + 1 < input.length() && input.charAt(i + 1) == '=') {
					i += 1;
				}
				cnt++;
				break;
			default:
				cnt++;
				break;
			} // switch
		} // for
		System.out.println(cnt);
	} // main
}
