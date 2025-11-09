import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader((new InputStreamReader(System.in)));
        int[] array = new int[42];

        for (int i = 0; i < 10; ++i){
            int input = Integer.parseInt(br.readLine());
            ++array[input % 42];
        }

        int result = 0;
        for (int i : array){
            if (i >= 1){
                ++result;
            }
        }

        System.out.print(result);
    }
}