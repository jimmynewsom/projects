import java.util.ArrayList;
import java.util.HashMap;

public class Kryptos {
    public static HashMap<Character, Integer> letterMap;
    public static final char[] numberMap = {'K', 'R', 'Y', 'P', 'T', 'O', 'S', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'L', 'M', 'N', 'Q', 'U', 'V', 'W', 'X', 'Z'};

    static {
        letterMap = new HashMap<>();
        for(int i = 0; i < 26; i++)
            letterMap.put(numberMap[i], i);
    }

    public static final String k1 = "EMUFPHZLRFAXYUSDJKZLDKRNSHGNFIVJYQTQUXQBQVYUVLLTREVJYQTMKYRDMFD";

    public static final String k4 = "OBKRUOXOGHULBSOLIFBBWFLRVQQPRNGKSSOTWTQSJQSSEKZZWATJKLUDIAWINFBNYPVTTMZFPKWGDKZXTJCDIGKUHUAUEKCAR";

    public static String encrypt_vigenere(String plainText, String key){
        plainText = plainText.toUpperCase();
        key = key.toUpperCase();
        StringBuilder cipherTextBuilder = new StringBuilder();

        for(int i = 0; i < plainText.length(); i++){
            // take the next letter and shift it by the next letter in the key
            cipherTextBuilder.append(numberMap[(letterMap.get(plainText.charAt(i)) + letterMap.get(key.charAt(i % key.length()))) % 26]);
        }

        return cipherTextBuilder.toString();
    }

    public static String decrypt_vigenere(String cipherText, String key){
        cipherText = cipherText.toUpperCase();
        key = key.toUpperCase();
        StringBuilder plainTextBuilder = new StringBuilder();

        for(int i = 0; i < cipherText.length(); i++){
            // take the next letter and shift it by the next letter in the key
            plainTextBuilder.append(numberMap[(letterMap.get(cipherText.charAt(i)) - letterMap.get(key.charAt(i % key.length())) + 26) % 26]);
        }

        return plainTextBuilder.toString();
    }



    //only works for vigenere ciphers, but assuming k4 is a "BC" cipher maybe I can use this to start to pry the key out

    public static String derive_key(String cipherText, String plainText) throws Exception {
        if(cipherText.length() != plainText.length())
            throw new Exception("ERROR! INPUTS MUST BE SAME LENGTH!");

        cipherText = cipherText.toUpperCase();
        plainText = plainText.toUpperCase();
        StringBuilder keyBuilder = new StringBuilder();
        for(int i = 0; i < cipherText.length(); i++){
            keyBuilder.append(numberMap[(letterMap.get(cipherText.charAt(i)) - letterMap.get(plainText.charAt(i)) + 26) % 26]);
        }

        return keyBuilder.toString();
    }

    public static String Kshift(String str, int n){
        String shifted = "";
        for(Character c : str.toCharArray())
            shifted += numberMap[(letterMap.get(c) + n) % 26];

        return shifted;
    }

    public static void main(String[] args){
        String keyGuess = "";
        try {
            System.out.println(derive_key("BERLINCLOCK", "NYPVTTMZFPK"));
            keyGuess = derive_key("BERLINCLOCK", "NYPVTTMZFPK");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println();

        for(int i = 0; i < 26; i++){
            String newGuess = Kshift(keyGuess, i);
            System.out.println("Current key: " + newGuess);

/*
            for(int j = 0; j < newGuess.length(); j++)
                System.out.print(letterMap.get(newGuess.charAt(j)) + " ");
                System.out.println();


            System.out.println(k4.substring(63, 74));
*/
        }













    }




}