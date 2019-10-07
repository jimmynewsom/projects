import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Sudoku {
    public static void solvePuzzle(int[][] puzzle){
        boolean inProgress = true;
        ArrayList<Set<Integer>> blockSets = new ArrayList<>();
        ArrayList<Set<Integer>> rowSets = new ArrayList<>();
        ArrayList<Set<Integer>> colSets = new ArrayList<>();

        //initialize hashsets
        for(int i = 0; i < 9; i++){
            HashSet<Integer> currentBlock = new HashSet<>();
            for(int j = 0; j < 9; j++){
                if(puzzle[(j / 3) + (i / 3)][(j % 3) + (i % 3)] != 0)
                    currentBlock.add(puzzle[(j / 3) + (i / 3)][(j % 3) + (i % 3)]);
            }

            blockSets.add(currentBlock);

            currentBlock = new HashSet<>();
            for(int j = 0; j < 9; j++){
                if(puzzle[i][j] != 0)
                    currentBlock.add(puzzle[i][j]);
            }

            rowSets.add(currentBlock);

            currentBlock = new HashSet<>();
            for(int j = 0; j < 9; j++){
                if(puzzle[j][i] != 0)
                    currentBlock.add(puzzle[j][i]);
            }

            colSets.add(currentBlock);
        }


/*
        while(inProgress){
            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    if(puzzle[i][j] == 0){
                        for(int guess = 1; guess <= 9; guess++){
                            if(!(blockSets.get().contains(guess)) && !(rowSets.get(i).contains(guess))
                               && !(colSets.get(j).contains(guess)))
                                    //possible answer
                                System.out.println("farts");
                        }



                    }
                }
            }
        }

*/

    }

    public static void main(String[] args){
        int[][] puzzle =
            {{7, 6, 0, 4, 0, 1, 0, 0, 0},
             {0, 5, 0, 0, 7, 9, 1, 0, 6},
             {0, 0, 0, 5, 0, 0, 7, 0, 9},
             {0, 7, 0, 0, 0, 0, 3, 0, 8},
             {4, 1, 0, 0, 0, 0, 0, 6, 2},
             {6, 0, 8, 0, 0, 0, 0, 1, 0},
             {8, 0, 5, 0, 0, 3, 0, 0, 0},
             {1, 0, 7, 6, 2, 0, 0, 3, 0},
             {0, 0, 0, 7, 0, 5, 0, 2, 4}};

        solvePuzzle(puzzle);

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                System.out.print("| " + puzzle[i][j] + " |");
            }
            System.out.println();
        }
    }




}