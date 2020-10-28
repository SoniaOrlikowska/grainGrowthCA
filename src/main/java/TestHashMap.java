import java.security.KeyStore;
import java.util.*;

public class TestHashMap {

    public static void main(String[] args) {


        int[] temp = {2,3,3,4,4,6,6,6,8,8,8,9,9,9};

       // int[] popo = new int[];

        Map<Integer, Integer> nameAndCount = new HashMap<Integer, Integer>();

        for (int s : temp) {
            Integer count = nameAndCount.get(s);
            if (count == null) {
                nameAndCount.put(s, 1);
            } else {
                nameAndCount.put( s,++count);
            }
        }

        Iterator<Map.Entry<Integer, Integer>> itr = nameAndCount.entrySet().iterator();

        int maxValueInMap = (Collections.max(nameAndCount.values()));
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
        int grainDuplicatesCounter = 0;
        for(Map.Entry<Integer,Integer> entry : nameAndCount.entrySet()){

            if(entry.getValue() == maxValueInMap){

                System.out.println("VALUE:  " + entry.getValue() + " GRAIN: " + entry.getKey() + " COUNT: " + grainDuplicatesCounter);
                grainDuplicatesCounter++;
            }


        }
        System.out.println(grainDuplicatesCounter);


    }
}
