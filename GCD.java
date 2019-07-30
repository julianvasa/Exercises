import java.util.Arrays;

/**
 * Greatest common divisor. In mathematics, the greatest common divisor (gcd) of two or more integers, which are not all zero,
 * is the largest positive integer that divides each of the integers. For example, the gcd of 8 and 12 is 4.
 *
 * @author Julian Vasa
 *
 */
public class GCD {
    static int [] arr= new int[]{4,6,8,10};
    static int max;
    public static void main(String[] args) {
        generalizedGCD(4, arr);
    }
    public static int generalizedGCD(int num, int[] arr)
    {
        Arrays.sort(arr);
        max = arr[0];
        return calcGCD();
    }
    public static int calcGCD(){
        if(max==0) return 1;
        for(int i=0;i<arr.length;i++){
            if(arr[i] % max > 0){
                max--;
                return calcGCD();
            }
        }
        return max;
    }
}
