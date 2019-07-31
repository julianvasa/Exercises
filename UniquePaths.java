import java.util.*;
import java.util.stream.IntStream;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * How many possible unique paths are there?
 * Example 1
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation:
 * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down
 * 2. Right -> Down -> Right
 * 3. Down -> Right -> Right
 *
 * Example 2
 * Input: m = 7, n = 3
 * Output: 28
 *
 * @author Julian Vasa
 */
public class UniquePaths {
    public static void main(String[] args) {
        uniquePaths(3 ,7);
    }
    public static int uniquePaths(int m, int n) {
        int paths = 0;
        int total = IntStream.rangeClosed(1, (m+n-2))
                .reduce(1, (int x, int y) -> x * y);
        int repM = IntStream.rangeClosed(1, m-1)
                .reduce(1, (int x, int y) -> x * y);
        int repN = IntStream.rangeClosed(1, n-1)
                .reduce(1, (int x, int y) -> x * y);
        paths = total / (repM * repN);
        return paths;
    }


}
