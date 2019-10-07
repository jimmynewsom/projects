import java.util.*;

public class Hard {

    //17.1


    //17.2
    public static void shuffle(ArrayList<Integer> cards){
        LinkedList<Integer> temp = new LinkedList<>(cards);
        cards = new ArrayList<>();
        Random r = new Random();
        for(int i = 0; i < 52; i++){
            cards.add(temp.remove(r.nextInt(cards.size())));
        }
    }

    //17.3
    public static Set<Integer> randomSet(int m, int[] arr){
        Random r = new Random();
        Set<Integer> resultSet = new HashSet<>();
        while(resultSet.size() <= m){
            int randomPick = arr[r.nextInt(arr.length)];
            if(!resultSet.contains(randomPick))
                resultSet.add(randomPick);
        }

        return resultSet;
    }

    /*
    //17.4
    public static int missingNumber(int[] arr){

    }
    */

    //17.5
    //start with the full string / array, then work backwards from the left and right
    // ie, for the string "abc" -> abc, ab, a, bc, b, c
    public static String lettersAndNumbers(String s){
        int numCount = 0, letterCount = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) >= 0 && s.charAt(i) <= 9)
                numCount++;
            else
                letterCount++;
        }

        if(numCount == letterCount)
            return s;

        int max = 0;
        int li = 0, ri = 0;

        for(int i = 0; i < s.length(); i++){
            int currentNC = numCount, currentLC = letterCount;
            for(int j = s.length() - 1; j > i; j--){
                if(s.charAt(j) >= 0 && s.charAt(j) <= 9)
                    currentNC--;
                else
                    currentLC--;

                if(currentNC == currentLC && i - j > max){
                    max = i-j;
                    li = i;
                    ri = j;
                }
            }

            if(s.charAt(i) >= 0 && s.charAt(i) <= 9)
                numCount--;
            else
                letterCount--;
        }
        if(max == 0)
            return "";
        else
            return s.substring(li, ri);
    }

    //17.7
    //my version returns a HashMap of sets of names to totals for that set,
    //instead of a list of one unique name from the set and the set total,
    //but otherwise it's the same
    public static HashMap<Set<String>, Integer> babyNames(HashMap<String, Integer> nameFrequencies, String[][] equivalentNames){
        HashMap<String, Set<String>> groupMap = new HashMap<>();
        HashMap<Set<String>, Integer> groupTotals = new HashMap<>();

        for(String[] namePair : equivalentNames){
            String name1 = namePair[0];
            String name2 = namePair[1];
            if(groupMap.containsKey(name1)){
                Set<String> group1 = groupMap.get(name1);
                if(groupMap.containsKey(name2)){
                    Set<String> group2 = groupMap.get(name2);
                    groupTotals.put(group1, groupTotals.get(group1) + groupTotals.get(group2));
                    groupTotals.remove(group2);
                    for(String name : group2) {
                        group1.add(name);
                        groupMap.put(name, group1);
                    }
                } else {
                    group1.add(name2);
                    groupMap.put(name2, group1);
                    groupTotals.put(group1, groupTotals.get(group1) + nameFrequencies.get(name2));
                }
            }  else if(groupMap.containsKey(name2)){
                Set<String> group2 = groupMap.get(name2);
                group2.add(name1);
                groupMap.put(name1, group2);
                groupTotals.put(group2, groupTotals.get(group2) + nameFrequencies.get(name1));
            } else {
                Set<String> newGroup = new HashSet<>();
                newGroup.add(name1);
                newGroup.add(name2);
                groupMap.put(name1, newGroup);
                groupMap.put(name2, newGroup);
                groupTotals.put(newGroup, nameFrequencies.get(name1) + nameFrequencies.get(name2));
            }
        }

        return groupTotals;
    }

    //17.8
    public static int circusTower(int[][] heightWeight){
        return 0;

    }

    //17.9
    public static int kthMultiple(int k){
        int counter = 0;

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(1);
        while(counter + 1 < k){
            int current = queue.poll();
            counter++;
            if(!(current % 5 == 0 || current % 7 == 0)){
                queue.add(current * 3);
                queue.add(current * 5);
                queue.add(current * 7);
            } else if(!(current % 7 == 0)){
                queue.add(current*5);
                queue.add(current*7);
            } else {
                queue.add(current*7);
            }
        }

        return queue.poll();
    }

    public static void main(String[] args){

    }
}
