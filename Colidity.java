import java.util.*;

/**
 * Let us consider a sequence a0, a1,...,an≠1. The leader of this sequence is the element whose
 * value occurs more than n
 * 2 times.
 * a0 a1 a2 a3 a4 a5 a6
 * 6 8 4 6 8 6 6
 * 0 1 2 3 4 5 6
 * In the picture the leader is highlighted in gray. Notice that the sequence can have at most one
 * leader. If there were two leaders then their total occurrences would be more than 2 · n
 * 2 = n,
 * but we only have n elements.
 * The leader may be found in many ways. We describe some methods here, starting with
 * trivial, slow ideas and ending with very creative, fast algorithms. The task is to find the value
 * of the leader of the sequence a0, a1,...,an≠1, such that 0 ˛ ai ˛ 109. If there is no leader,
 * the result should be -1.
 *
 * @author Julian Vasa
 */
public class Colidity
{
    static Set<Integer> toReturn =  new HashSet<Integer>();
    static Set<Integer> uniqueElems = new HashSet<Integer>();
    static Map<Integer, Integer> counters = new HashMap<>();
    static int [] arr= new int[]{1, 2, 2, 1, 2};

    public static void main(String[] args) {
        for (int i : arr)
        {
            uniqueElems.add(i);
        }
        solution(4, 2, arr);
    }

    public static int[] solution(int K, int M, int[] A) {
        check(A,K);
        List<Integer> leadersSorted = new ArrayList<>(toReturn);
        Collections.sort(leadersSorted);
        return leadersSorted.stream().mapToInt(i -> i).toArray();
    }

    public static void check(int[] arr, int segmentSize){
        for(int i=0;i<arr.length-segmentSize+1;i++){
            int[] tmp = new int[arr.length];
            System.arraycopy( arr, 0, tmp, 0, arr.length );

            for(int j=i;j<(i+segmentSize);j++){
                //System.out.println(j);
                tmp[j] = tmp[j]+1;
            }
            getLeader(tmp);
        }
    }

    public static void getLeader(int[] tmp){
        uniqueElems = new HashSet<Integer>();
        counters = new HashMap<>();
        for (int i : tmp)
        {
            uniqueElems.add(i);
        }
        //System.out.println("getLeader " + Arrays.toString(tmp));
        int halfSize = (int) Math.floor(tmp.length / 2);
        uniqueElems.forEach(t-> {
            for(int j=0;j<tmp.length;j++){
                if(tmp[j] == t) {
                    if(counters.containsKey(t)){
                        counters.put(t, counters.get(t)+1);
                    }
                    else {
                        counters.put(t, 1);
                    }
                }
            }
        });
        counters.forEach((t,v) -> {
            if(v>halfSize) {
                toReturn.add(t);
                //System.out.println("Hashmap " + t + " " + v);
            }
        });
    }
}