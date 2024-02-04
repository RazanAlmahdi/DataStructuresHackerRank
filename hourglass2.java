import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'hourglassSum' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY arr as parameter.
     */

    public static int hourglassSum(List<List<Integer>> arr)
    {
        // from arr[i][j] until arr[i+2][j+2] but not arr[i+1][j] and arr[i+1][j+2]
        // i is the row
        // j is the column
        // each row has 4 hourglssses edges
        // each column has 4 hourglasses heads
        // first sum is from arr[i][j] to arr[i][j+2]
        // second sum is from arr[i][j+1] to arr[i][j+3]
        // third sum is from arr[i][j+2] to arr[i][j+4]
        // fourth sum is from arr[i][j+3] to arr[i][j+5]
        int[][] s = new int[arr.size()-2][arr.size()-2];
        for (int i=0; i<arr.size()-2; i++)
            for (int j=0; j<arr.size()-2; j++)
                s[i][j] = 0;
        int sum = 0;
        
        for (int i=0; i<arr.size()-2; i++)
        {
            for (int j=0; j<arr.size()-2; j++)
            {
                for (int r=0; r<3; r++)
                {
                    for(int c=0; c<3; c++)
                    {
                        if ( r%2 == 0 || (r%2 != 0 && r==c))
                        {
                            sum = s[i][j] + arr.get(i+r).get(j+c);
                            s[i][j] = sum;
                        }
                    }
                }
            }
        }
                
        int max = s[0][0];
        for (int i=0; i<arr.size()-2; i++)
        {
            for (int j=0; j<arr.size()-2; j++)
            {
                System.out.print(s[i][j] + " ");
                if (s[i][j] > max)
                    max = s[i][j];
            }
            System.out.println();
        }
        return max;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        List<List<Integer>> arr = new ArrayList<>();

        IntStream.range(0, 6).forEach(i -> {
            try {
                arr.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.hourglassSum(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
