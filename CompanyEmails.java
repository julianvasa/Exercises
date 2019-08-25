import java.util.*;

public class Solution {
    public static void main(String[] args) {

        // Given the following string with names (optional middle name) and surname and
        // a company name, returns the email composed as followsç
        // first char of first name + "_" + last name + "@" + company name + ".com"
        // if there are duplicates add an increment number after surname
        solution("John Doe; Peter Parker; Mary Jane Watson-Parker; James Doe; John Elvis Doe; Jane Doe; Penny Parker",
                "Example");
    }

    public static String solution(String S, String C) {
        String toReturn = "";
        String company = C.toLowerCase() + ".com";
        String[] arr = S.split("; ");
        for (String elem : arr) {
            String[] splitName = elem.toLowerCase().split(" ");
            String first = String.valueOf(splitName[0].charAt(0));
            String last = "";
            if (splitName.length == 2) {
                last = splitName[1].replace("-", "");
            } else {
                last = splitName[2].replace("-", "");
            }
            String toAdd = first + "_" + last + "@" + company;
            if (!toReturn.contains(toAdd)) {
                if (toReturn.equals(""))
                    toReturn = toAdd;
                else
                    toReturn += "; " + toAdd;
            } else {
                for (int i = 2; i < 1000; i++) {
                    String tmpToAdd = first + "_" + last + i + "@" + company;
                    if (!toReturn.contains(tmpToAdd)) {
                        toReturn += "; " + tmpToAdd;
                        break;
                    }
                }
            }
        }
        return toReturn;
    }
}
