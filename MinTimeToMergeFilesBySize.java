import java.util.*;

/**
 * There are N file sizes arranged in a row
 *
 * A move consists of merging exactly 2 consecutive files into one file, and the
 * cost of this move is equal to the total number of sizes in these K files.
 *
 * Find the minimum cost to merge all files into one file. If it is impossible,
 * return -1
 *
 * Input: files = [3,5,1,2,6], numFiles = 5 Output: 25 Explanation: We start
 * with [3, 5, 1, 2, 6]. We sort the array => [1, 2, 3, 5, 6]. We merge [1, 2]
 * for a cost of 3, and we are left with [3, 5, 6]. We merge [3, 3] for a cost
 * of 6, and we are left with [5, 6]. We merge [6, 5] for a cost of 11, and we
 * are left with [6]. We merge [11, 6] for a cost of 17 The total cost was 37,
 * and this is the minimum possible.
 *
 * @author Julian Vasa
 */
public class MinTimeToMergeFilesBySize {
    static List<Integer> list = new ArrayList<>();
    static int finalSum = 0;

    public static void main(String[] args) {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(5);
        list.add(6);

        System.out.println(minimumTime(5, list));
    }

    static int minimumTime(int numOfSubFiles, List<Integer> files) {
        Collections.sort(files);
        int sum = 0;

        sum = calcSum(sum, files);
        return finalSum;
    }

    static int calcSum(int sum, List<Integer> files) {

        sum = files.get(0) + files.get(1);
        System.out.println(sum + " " + Arrays.toString(list.toArray()));
        finalSum += sum;

        if (files.size() == 2)
            return sum;
        else {
            files.set(0, sum);
            files.remove(1);
            return calcSum(sum, files);
        }
    }
}