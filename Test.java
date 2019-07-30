import java.util.*;
/**
 * You are given a string S consisting of N characters and an integer K. You can modify string S by removing any substring of it. A substring is defined as a contiguous segment of a string.
 * The goal is to find the shortest substring of S that, after removal, leaves S containing exactly K different characters.
 *
 * Write a function:
 * class Solution { public int solution(String S, int K); }
 *
 * that, given a non-empty string S consisting of N characters and an integer K, returns the length of the shortest substring that can be removed. If there is no such substring, your function should return −1.
 *
 * Examples:
 *
 * 1. Given S = "abaacbca" and K = 2, your function should return 3. After removing substring "cbc", string S will contain exactly two different characters: a and b.
 * 2. Given S = "aabcabc" and K = 1, your function should return 5. After removing "bcabc", string S will contain exactly one character: a.
 * 3. Given S = "zaaaa" and K = 1, your function should return 1. You can remove only one letter: z.
 * 4. Given S = "aaaa" and K = 2, your function should return −1. There is no such substring of S that, after removal, leaves S containing exactly 2 different characters.
 *
 * Write an efficient algorithm for the following assumptions:
 * N is an integer within the range [1..1,000,000];
 * string S consists only of lowercase letters (a−z);
 * K is an integer within the range [0..26].
 * @author Julian Vasa
 */
class Test {
    public static void main(String[] args) {
        solution("abaacbca", 26 );
    }
    public static int solution(String S, int K) {
        List<String> finalStrings = new ArrayList<>();
        long countChars = S.chars().distinct().count();
        if(K<countChars) {
            for (int i = 0; i < S.length(); i++) {
                for (int j = i + 1; j <= S.length(); j++) {
                    String tmp = S;
                    tmp = tmp.substring(0, i) + tmp.substring(j);
                    if (!tmp.isEmpty()) {
                        countChars = tmp.chars().distinct().count();
                        if (countChars == K) {
                            finalStrings.add(S.substring(i, j));
                        }
                        if (K == 0) {
                            finalStrings.add(S);
                        }
                    }
                }
            }

            String finalStr = "";
            try {
                finalStr = finalStrings.stream().min(
                        Comparator.comparing(
                                word -> word.length()))
                        .get();
            } catch (NoSuchElementException e) {
                return -1;
            }
            System.out.println(finalStr);
            return finalStr.length();
        }
        return -1;
    }
}
