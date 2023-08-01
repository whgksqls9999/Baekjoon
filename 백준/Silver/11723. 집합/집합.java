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
		StringTokenizer st;
		Set<Integer> set = new HashSet<>();
		
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i ++) {
			st = new StringTokenizer(br.readLine());
			switch(st.nextToken()) {
			case "add":
				set.add(Integer.parseInt(st.nextToken()));
				break;
			case "check":
				if(set.contains(Integer.parseInt(st.nextToken()))) {
					sb.append(1+"\n");
				} else {
					sb.append(0+"\n");
				}
				break;
			case "remove":
				set.remove(Integer.parseInt(st.nextToken()));
				break;
			case "toggle":
				int a = Integer.parseInt(st.nextToken());
				if(set.contains(a)) {
					set.remove(a);
				} else {
					set.add(a);
				}
				break;
			case "all":
				for (int j = 1; j <= 20; j++) {
					if (set.contains(j))continue;
					set.add(j);
				}
				break;
			case "empty":
				set.clear();
				break;			
				
			}
		}
		System.out.println(sb);
	}
}
