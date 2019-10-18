import java.util.*;

public class Sudoku {
    public static void solvePuzzle(int[][] puzzle) {
        Integer[] s1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        ArrayList<Integer> startingSet = new ArrayList<Integer>(Arrays.asList(s1));
        ArrayList<Set<Integer>> blockSets = new ArrayList<>();
        ArrayList<Set<Integer>> rowSets = new ArrayList<>();
        ArrayList<Set<Integer>> colSets = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            blockSets.add(new HashSet<Integer>(startingSet));
            rowSets.add(new HashSet<Integer>(startingSet));
            colSets.add(new HashSet<Integer>(startingSet));
        }

        Queue<Integer[]> queue = new LinkedList<Integer[]>();

        //first we use elimination - delete values once I find them so I can use intersections of missing values later

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(puzzle[i][j] != 0){
                    rowSets.get(j).remove(puzzle[i][j]);
                    colSets.get(i).remove(puzzle[i][j]);
                    blockSets.get((j/3)*3 + (i/3)).remove(puzzle[i][j]);
                }
                else
                    queue.add(new Integer[] {i, j});
            }
        }

        while(!queue.isEmpty()){
            Integer[] nextSpot = queue.poll();
            int i = nextSpot[0]; int j = nextSpot[1];

            //get the intersection of the sets
            Set<Integer> possibilities = new HashSet<Integer>(blockSets.get((j/3)*3 + (i/3)));
            possibilities.retainAll(rowSets.get(nextSpot[j]));
            possibilities.retainAll(rowSets.get(nextSpot[j]));

            if(possibilities.size() == 1){
                //if there's only possibility, that's the answer
                puzzle[i][j] = possibilities.toArray(new Integer[1])[0];
            }
            else {
                //otherwise, I'm not entirely sure...
                //
                queue.add(nextSpot);
            }
        }







    }

}