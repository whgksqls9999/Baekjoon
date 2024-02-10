import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		String[] output = new String[5];
		output[0] = "       _.-;;-._";
		output[1] = "'-..-'|   ||   |";
		output[2] = "'-..-'|_.-;;-._|";
		output[3] = "'-..-'|   ||   |";
		output[4] = "'-..-'|_.-''-._|";

		for (String s : output) {
			System.out.println(s);
		}
	}
}