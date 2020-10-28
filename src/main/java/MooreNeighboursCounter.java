import java.security.KeyStore;
import java.util.*;

public class MooreNeighboursCounter {

    public MooreNeighboursCounter(int[][] initialStateMatrix, int[][] newStateMatrix) {

        int rowsLength = initialStateMatrix.length;
        int columnsLength = initialStateMatrix[0].length;


        for (int i = 0; i < rowsLength; i++) {
            for (int j = 0; j < columnsLength; j++) {

//todo there should be 2 methods in case of changing neighbourhood type one for counting specific neighbourhood, one doing the rest

                //out of matrix boundaries
                if (i != 0 && i != rowsLength - 1 && j != 0 && j != columnsLength - 1) {

                    int neighbourCounter = 0;
                    ArrayList<Integer> temp = new ArrayList<Integer>(8);


                    //how many neighbour?

                    if (initialStateMatrix[i - 1][j - 1] != 0) {
                        neighbourCounter++;
                        temp.add(initialStateMatrix[i - 1][j - 1]);
                    }

                    if (initialStateMatrix[i - 1][j] != 0) {
                        neighbourCounter++;
                        temp.add(initialStateMatrix[i - 1][j]);
                    }

                    if (initialStateMatrix[i + 1][j - 1] != 0) {
                        neighbourCounter++;
                        temp.add(initialStateMatrix[i + 1][j - 1]);
                    }
                    if (initialStateMatrix[i + 1][j] != 0) {
                        neighbourCounter++;
                        temp.add(initialStateMatrix[i + 1][j]);
                    }
                    if (initialStateMatrix[i][j - 1] != 0) {
                        neighbourCounter++;
                        temp.add(initialStateMatrix[i][j - 1]);
                    }
                    if (initialStateMatrix[i][j + 1] != 0) {
                        neighbourCounter++;
                        temp.add(initialStateMatrix[i][j - 1]);
                    }

                    if (initialStateMatrix[i + 1][j + 1] != 0) {
                        neighbourCounter++;
                        temp.add(initialStateMatrix[i + 1][j + 1]);
                    }
                    if (initialStateMatrix[i - 1][j + 1] != 0) {
                        neighbourCounter++;
                        temp.add(initialStateMatrix[i - 1][j + 1]);
                    }


                    //to powinna być metoda

                    if (temp.size() == 1) initialStateMatrix[i][j] = temp.get(0); // if there is only one neighbour

                    else {
                        //if there're duplicates:
                        //if all keys are = single ==> choose randomly
                        //find the most powerful grain ( which key is the largest?) if there is any other than 1 key?

                        Map<Integer, Integer> grainAndCount = new HashMap<Integer, Integer>();

                        for (int s : temp) {
                            Integer count = grainAndCount.get(s);
                            if (count == null) {
                                grainAndCount.put(s, 1);
                            } else {
                                grainAndCount.put(s, ++count); // how many grains of each type is int the neighbourhood? grainAndCount [{1=4},{2=1},{3,0},{4,1}]
                            }
                        }

                        Iterator<Map.Entry<Integer, Integer>> itr = grainAndCount.entrySet().iterator();

                        int maxValueInMap = (Collections.max(grainAndCount.values())); //the most powerful grain grain with bigger value

                        if (maxValueInMap != 1) { //powinnam sprawdzic czy GRAIN ma unikatowe wartosci


                            for (Map.Entry<Integer, Integer> entry : grainAndCount.entrySet()) {
                                if (entry.getValue() == maxValueInMap) {
                                    newStateMatrix[i][j] = entry.getKey();
                                    grainDuplicatesCounter++;
                                }
                            }



                        } else {



                        }

                        //  while (itr.hasNext()) {
                        //    System.out.println(itr.next());
                        //}




                        Random random = new Random();
                        int randomIndex = random.nextInt(temp.size());

                    }

                }

            }
        }
    }
}

