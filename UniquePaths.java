import java.math.BigInteger;

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
public class Solution {
   
     public static BigInteger factorial(BigInteger number) {
        BigInteger result = BigInteger.valueOf(1);

        for (long factor = 2; factor <= number.longValue(); factor++) {
            result = result.multiply(BigInteger.valueOf(factor));
        }

        return result;
    }

    public static int uniquePaths(int m, int n) {
        BigInteger total = factorial(BigInteger.valueOf(m+n-2));
        BigInteger repM = factorial(BigInteger.valueOf(m-1));
        BigInteger repN = factorial(BigInteger.valueOf(n-1));
        if(repM.multiply(repN).equals(0)) return 0;
        BigInteger paths = total.divide(repM.multiply(repN));
        return paths.intValue();
    }
}
