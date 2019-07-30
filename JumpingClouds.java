/**
 * Emma is playing a new mobile game that starts with consecutively numbered clouds. Some of the clouds are thunderheads and others are cumulus. She can jump on any cumulus cloud having a number that is equal to the number of the current cloud plus  or . She must avoid the thunderheads. Determine the minimum number of jumps it will take Emma to jump from her starting postion to the last cloud. It is always possible to win the game.
 * For each game, Emma will get an array of clouds numbered  if they are safe or  if they must be avoided. For example,  indexed from . The number on each cloud is its index in the list so she must avoid the clouds at indexes  and . She could follow the following two paths:  or . The first path takes jumps while the second takes .
 *
 * Function Description
 * Complete the jumpingOnClouds function in the editor below. It should return the minimum number of jumps required, as an integer.
 * jumpingOnClouds has the following parameter(s):
 * c: an array of binary integers
 * Input Format
 * The first line contains an integer , the total number of clouds. The second line contains  space-separated binary integers describing clouds  where .
 *
 * Constraints
 * Output Format
 * Print the minimum number of jumps needed to win the game.
 * Sample Input 0
 * 7
 * 0 0 1 0 0 1 0
 * Sample Output 0
 *
 * 4
 * Explanation 0:
 * Emma must avoid  and . She can win the game with a minimum of  jumps:
 *
 * jump(2).png
 *
 * Sample Input 1
 *
 * 6
 * 0 0 0 0 1 0
 * Sample Output 1
 *
 * 3
 * Explanation 1:
 * The only thundercloud to avoid is . Emma can win the game in  jumps:
 *
 * @author Julian Vasa
 */


public class JumpingClouds {
    static int [] arr= new int[]{0, 0, 1, 0, 0, 1, 0};

    public static void main(String[] args) {
        jumpingOnClouds(arr);
    }

    static int jumpingOnClouds(int[] c) {
        int jumps =0;

        for(int i=0;i<c.length;i++) {
            System.out.println("i:"+i);

            if(c[i]==0){
                if(i<c.length-2 && c[i+2]==0 ){
                    i = i+1;
                    jumps++;
                }
                else if(i < c.length-1 && c[i+1]==0){
                    jumps++;
                    continue;
                }
            }
        }
        return jumps;
    }
}
