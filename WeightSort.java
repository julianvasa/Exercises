import java.util.*;

import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;
import java.util.stream.Collectors;

public class WeightSort {

    /**
     * Given a string of numbers divided by space 1. Calculate the sum of the digits
     * of each number 2. Sort by sum (asc) 3. Return a list of the initial numbers
     * (in the string) after sorting by sum of digits
     */
    public static String orderWeight(String strng) {
        String finalStr = "";
        Map<String, Integer> listOfSums = new HashMap<String, Integer>();
        String[] arr = strng.split(" ");
        for (int i = 0; i < arr.length; i++) {
            char[] digits = arr[i].toCharArray();
            int charSum = 0;
            for (int j = 0; j < digits.length; j++) {
                charSum += Character.getNumericValue(digits[j]);
            }
            /** concat with i to allow duplicates, will remove the postfix on return */
            listOfSums.put(arr[i] + "-" + i, charSum);
        }
        /**
         * Compare by Value then compare by Key in case of different numbers with same
         * sum of digits
         */
        listOfSums = listOfSums.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().thenComparing(Map.Entry::getKey))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        /** remove duplicates postfix - and create a string by concatenating keys */
        finalStr = listOfSums.keySet().stream().map(elem -> elem.substring(0, elem.indexOf("-")))
                .collect(Collectors.joining(" "));

        return finalStr;
    }
}