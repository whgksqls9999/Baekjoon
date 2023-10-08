import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Turtle {
		int dir; // 0 1 2 3 북 동 남 서
		int r, c;

		public Turtle() {
			super();
			this.dir = 0;
			this.r = 0;
			this.c = 0;
		}

	}

	static int mR;
	static int mC;
	static int pR;
	static int pC;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken(" "));

		for (int tc = 1; tc <= T; tc++) {
			mR = mC = pR = pC = 0;
			Turtle turtle = new Turtle();

			char[] input = br.readLine().toCharArray();

			for (char c : input) {
				if (c > 70) {
					rotate(c, turtle);
				} else {
					move(c, turtle);
				}
			}
			sb.append((mR + pR) * (pC + mC)).append("\n");
		}
		System.out.println(sb);

	}

	static void move(char d, Turtle turtle) {
		int direction = turtle.dir;
		if (d == 'F') {
			switch (direction) {
			case 0:
				++turtle.r;
				break;
			case 1:
				++turtle.c;
				break;
			case 2:
				--turtle.r;
				break;
			case 3:
				--turtle.c;
				break;
			}
		} else {
			switch (direction) {
			case 0:
				--turtle.r;
				break;
			case 1:
				--turtle.c;
				break;
			case 2:
				++turtle.r;
				break;
			case 3:
				++turtle.c;
				break;
			}

		}
		if (turtle.r < 0) {
			mR = Math.max(mR, -turtle.r);
		} else if (turtle.r > 0) {
			pR = Math.max(pR, turtle.r);
		}

		if (turtle.c < 0) {
			mC = Math.max(mC, -turtle.c);
		} else {
			pC = Math.max(pC, turtle.c);
		}
//		System.out.println(turtle.r + " : " + turtle.c);
	}

	static void rotate(char r, Turtle turtle) {
		if (r == 'L') {
			--turtle.dir;
			if (turtle.dir < 0) {
				turtle.dir += 4;
			}
		} else {
			turtle.dir = (turtle.dir + 1) % 4;
		}
	}
}