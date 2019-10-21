import java.util.*;

//Strings and Arrays
public class ChapterOne {
    //1.1
    public boolean isUnique(String str){
        HashSet<Character> set = new HashSet<>();
        for(Character c : str.toCharArray()){
            if(set.contains(c))
                return false;
            else
                set.add(c);
        }
        return true;
    }

    //1.2
    public boolean isPermutation(String str1, String str2){
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();

        for(Character c : str1.toCharArray()){
            if(map1.containsKey(c))
                map1.put(c, map1.get(c) + 1);
            else
                map1.put(c, 1);
        }

        for(Character c : str2.toCharArray()){
            if(map2.containsKey(c))
                map2.put(c, map2.get(c) + 1);
            else
                map2.put(c, 1);
        }

        for(Character c : map1.keySet()) {
            if (!map2.containsKey(c) || (map1.get(c) != map2.get(c)))
                return false;
        }

        return true;
    }


    //1.3 - I know I'm supposed to do this in place, but idk why I would ever do that....
    //also, this will run faster even though it uses more memory
    public String urlify(String str){
        StringBuilder builder = new StringBuilder();
        int z = str.length();
        while(str.charAt(z - 1) == ' ')
            z--;

        //z is the start of the trailing spaces at the end of the string

        for(int i = 0; i < z; i++){
            if(str.charAt(i) == ' ')
                builder.append("%20");
            else
                builder.append(str.charAt(i));
        }

        return builder.toString();
    }


    //1.4
    public boolean palindromePermutation(String input){
        String preprocessed = input.toLowerCase();
        HashMap<Character, Integer> map = new HashMap<>();
        for(Character c : preprocessed.toCharArray()){
            if(c >= 'a' && c <= 'z'){
                if(map.containsKey(c))
                    map.put(c, map.get(c) + 1);
                else
                    map.put(c, 1);
            }
        }

        boolean foundOne = false;
        for(Character c : map.keySet()){
            if(map.get(c) % 2 != 0){
                if(foundOne == false) //this seems more readable
                    foundOne = true;
                else
                    return false;
            }
        }

        return true;
    }

    //1.5
    public boolean oneAway(String str1, String str2){
        if(str1.length() == str2.length()){
            boolean foundOne = false;
            for(int i = 0; i < str1.length(); i++){
                if(str1.charAt(i) != str2.charAt(i)){
                    if(foundOne == false)
                        foundOne = true;
                    else
                        return false;
                }
            }
        } else {
            String longer, shorter;
            if(str1.length() > str2.length()){
                longer = str1;
                shorter = str2;
            } else {
                longer = str2;
                shorter = str1;
            }

            if(longer.length() - 1 != shorter.length())
                return false;

            int j = 0; boolean foundOne = false;
            for(int i = 0; i < shorter.length(); i++){
                if(shorter.charAt(i) != longer.charAt(j)){
                    if(foundOne == false){
                        foundOne = true;
                        j++;
                        if(shorter.charAt(i) != longer.charAt(j))
                            return false;
                    }
                }
            }
        }

        return true;
    }

    //1.6
    public String stringCompression(String str){
        StringBuilder builder = new StringBuilder();
        char currentChar = str.charAt(0);
        int count = 1;
        for(int i = 1; i < str.length(); i++){
            if(str.charAt(i) == currentChar)
                count++;
            else{
                builder.append(currentChar + count);
                currentChar = str.charAt(i);
                count = 1;
            }
        }
        builder.append(currentChar + count);

        String str2 = builder.toString();

        if(str.length() <= str2.length())
            return str;
        else
            return str2;

    }


    //1.7
    public void rotateMatrix(int[][] image){
        int n = image.length;
        for(int i = 0; i < n/2; i++){
            for(int j = i; j < n - 1 - i; j++){
                int temp = image[i][j];
                image[i][j] = image[n-1-i][j];
                image[n-1-i][j] = image[n-1-i][n-1-j];
                image[n-1-i][n-1-j] = image[i][n-1-j];
                image[i][n-1-j] = temp;
            }
        }
    }


    //1.8
    public void zeroMatrix(int[][] matrix){
        int m = matrix.length;
        int n = matrix[0].length;

        HashSet<Integer> rows = new HashSet<>();
        HashSet<Integer> cols = new HashSet<>();

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 0){
                        rows.add(i);
                        cols.add(j);
                }
            }
        }

        for(int i : rows){
            for(int j = 0; j < n; j++){
                matrix[i][j] = 0;
            }
        }

        for(int j : cols){
            for(int i = 0; i < m; i++){
                matrix[i][j] = 0;
            }
        }
    }

    /*
    //1.9
    public boolean stringRotation(String s1, String s2){
        if(s1.length() != s2.length())
            return false;

            // v1, whoops - bad
            //for(int i = 0; i < s1.length(); i++){
            //    String rotatation = s1.substring(i) + s1.substring(0, i);
            //    if(rotation.equals(s2))
            //        return true;
            //}
            //
            //return false;

        //v2, from book - better
        String xyxy = s1 + s1;

        return isSubstring(xyxy, s2);
    }
     */

}
