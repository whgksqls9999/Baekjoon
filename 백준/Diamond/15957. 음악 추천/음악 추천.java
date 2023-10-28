import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static long J;
	static List<Integer>[] nodes;
	static int[][] range;
	static int idx;
	static List<Integer>[] singer;
	static int[] song_singer;
	static Queue<Integer>[] mid;
	static long[] tree;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();

		Reader s = new Reader();

		N = s.nextInt();
		K = s.nextInt();
		J = (long) s.nextInt();

		nodes = new ArrayList[N + 1];
		for (int i = 1; i <= N; ++i) {
			nodes[i] = new ArrayList<>();
		}

		for (int i = 2; i <= N; ++i) {
			nodes[s.nextInt()].add(i);
		}

		range = new int[N + 1][2];

		DFS(1);

		singer = new ArrayList[N + 1];
		for (int i = 1; i <= N; ++i) {
			singer[i] = new ArrayList<>();
		}
		song_singer = new int[N + 1];

		for (int i = 1; i <= N; ++i) {
			int singerNum = s.nextInt();
			singer[singerNum].add(i);
//			song_singer.put(i, singerNum);
			song_singer[i] = singerNum;
		}

		int[][] queries1 = new int[K + 1][3];

		for (int i = 1; i <= K; ++i) {
			queries1[i][0] = s.nextInt();
			queries1[i][1] = s.nextInt();
			queries1[i][2] = s.nextInt();
		}

		Arrays.sort(queries1, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});

		int[] lo = new int[N + 1];
		int[] hi = new int[N + 1];

		Arrays.fill(lo, 1);
		Arrays.fill(hi, K + 1);

		mid = new LinkedList[K + 1];
		for (int i = 0; i <= K; ++i) {
			mid[i] = new LinkedList<>();
		}

		long[] max = new long[N + 1];

		for (int i = 1; i <= N; ++i) {
			max[i] = J * singer[i].size();
		}

		while (true) {
			boolean finish = true;

			// 1. 초기화
			tree = new long[N + 1];

			// 2. 종료조건
			for (int i = 1; i <= N; ++i) {
				if (lo[i] < hi[i]) {
					finish = false;
					mid[(lo[i] + hi[i]) / 2].add(i);
				}
			}

			if (finish)
				break;

			// 3. 쿼리
			for (int i = 1; i <= K; ++i) {
				int[] now = queries1[i];

				int start = range[now[1]][0];
				int end = range[now[1]][1];
				int val = now[2] / (end - start + 1);

				rangeUpdate(start, end, val);

				while (!mid[i].isEmpty()) {
					// next : 노래 번호
					int next = mid[i].poll();

					// score : 해당 가수가 부른 노래의 총 점
					long score = 0;
					for (int idx : singer[next]) {
						score += query(range[idx][0]);
					}

					if (score > max[next]) {
						hi[next] = i;
					} else {
						lo[next] = i + 1;
					}
				}
			}
		}

		for (int i = 1; i <= N; ++i) {
			if (lo[song_singer[i]] != K + 1) {
//				sb.append(hi[song_singer[i]]).append("\n");
				sb.append(queries1[lo[song_singer[i]]][0]).append("\n");
			} else {
				sb.append(-1).append("\n");
			}
		}

		System.out.print(sb);
	}

	static void DFS(int node) {
		range[node][0] = ++idx;

		for (int i : nodes[node]) {
			DFS(i);
		}

		range[node][1] = idx;
	}

	static void rangeUpdate(int start, int end, int val) {
		update(start, val);
		update(end + 1, -val);
	}

	static void update(int idx, int val) {
		while (idx <= N) {
			tree[idx] += val;
			idx += idx & (-idx);
		}
	}

	static long query(int idx) {
		long res = 0;
		while (idx > 0) {
			res += tree[idx];
			idx -= idx & (-idx);
		}
		return res;
	}

	static class Reader {
		final private int BUFFER_SIZE = 1 << 16;
		private DataInputStream din;
		private byte[] buffer;
		private int bufferPointer, bytesRead;

		public Reader() {
			din = new DataInputStream(System.in);
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public Reader(String file_name) throws IOException {
			din = new DataInputStream(new FileInputStream(file_name));
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public String readLine() throws IOException {
			byte[] buf = new byte[64]; // line length
			int cnt = 0, c;
			while ((c = read()) != -1) {
				if (c == '\n')
					break;
				buf[cnt++] = (byte) c;
			}
			return new String(buf, 0, cnt);
		}

		public int nextInt() throws IOException {
			int ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (neg)
				return -ret;
			return ret;
		}

		public long nextLong() throws IOException {
			long ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -ret;
			return ret;
		}

		public double nextDouble() throws IOException {
			double ret = 0, div = 1;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();

			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (c == '.') {
				while ((c = read()) >= '0' && c <= '9') {
					ret += (c - '0') / (div *= 10);
				}
			}

			if (neg)
				return -ret;
			return ret;
		}

		private void fillBuffer() throws IOException {
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}

		private byte read() throws IOException {
			if (bufferPointer == bytesRead)
				fillBuffer();
			return buffer[bufferPointer++];
		}

		public void close() throws IOException {
			if (din == null)
				return;
			din.close();
		}
	}
}