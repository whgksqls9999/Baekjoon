import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		Set<String> set = new HashSet<>();
		
		int N = Integer.parseInt(st.nextToken());
		String game = st.nextToken();
		
		for (int i = 0; i < N; i++) {
			set.add(br.readLine());
		}
		switch (game) {
		case "Y":
			System.out.println(set.size());
			break;
		case "F":
			System.out.println(set.size()/2);
			break;
		case "O":
			System.out.println(set.size()/3);
			break;
		}
	}
}
