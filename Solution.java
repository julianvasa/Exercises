import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
/**
 * In a neighborhood formed by eight houses (represented by an Array), the houses compete with
 * each other every day. For each house, the "competition" follows the following logic:
 * 	- If both neighbors are active (1) or inactive (0), the current house will be inactive next day.
 *  - In the other cases, the current house will be active in the next day.
 * The algorithm processes the state of all houses after a given number of days.
 *
 * @author Julian Vasa
 */
public class Solution
{
    public static void main(String[] args) {
        int [] states= new int[]{1, 1, 1, 0, 1, 1, 1, 1};
        cellCompete(states, 2);
    }

    public static List<Integer> cellCompete(int[] states, int days)
    {
        for(int i=days; i>0;i--) {
            //System.out.println("INIT " + Arrays.toString(states));
            states = changeCells(states);
            //System.out.println(" ");
            //System.out.println("FINAL " + Arrays.toString(states));
        }
        List<Integer> list  = Arrays.stream(states).boxed().collect(Collectors.toList());
        return list;
    }

    public static int[] changeCells(int[] states){
        int[] tmp =  new int[states.length];
        System.arraycopy( states, 0, tmp, 0, states.length );

        for(int i=0; i<tmp.length;i++){
            //System.out.println(" ");
            //System.out.println(":::: "+ states[i]+" ");
            if(i==0){
                //System.out.print("CHECK " + i +" " + Arrays.toString(states)+" " + Arrays.toString(tmp));

                //System.out.print(i +" "+states[i]+" >> 0 "+states[i+1]+ " "+(states[i]^0)+ " "+(states[i]^states[i+1]));
                if((0^states[i+1])==0){
                    tmp[i] = 0;
                }
                else {
                    tmp[i] = 1;
                }
            }
            else if(i==tmp.length-1){
                //System.out.print("CHECK " + i +" " + Arrays.toString(states)+" " + Arrays.toString(tmp));
                //System.out.print(i +" "+states[i]+" >> "+states[i-1]+ " 0 "+(states[i]^states[i-1])+ " "+(states[i]^0));
                if((0^states[i-1])==0){
                    tmp[i] = 0;
                }
                else {
                    tmp[i] = 1;
                }
            }
            else {
                //System.out.print("CHECK " + i +" " + Arrays.toString(states)+" " + Arrays.toString(tmp));
                //System.out.print(i +" "+states[i]+" >> "+states[i-1]+ " "+states[i+1]+ " "+(states[i]^states[i-1])+ " "+(states[i]^states[i+1]));
                if((states[i+1]^states[i-1])==0){
                    tmp[i] = 0;
                }
                else {
                    tmp[i] = 1;
                }
            }
            //System.out.println(":::: FINISH "+ tmp[i]+" ");

        }
        return tmp;
    }

}