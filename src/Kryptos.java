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

    public static final String k2 = "VFPJUDEEHZWETZYVGWHKKQETGFQJNCEGGWHKK?DQMCPFQZDQMMIAGPFXHQRLGTIMVMZJANQLVKQEDAGDVFRPJUNGEUNAQZGZLECGYUXUEENJTBJLBQCRTBJDFHRR" +
            "YIZETKZEMVDUFKSJHKFWHKUWQLSZFTIHHDDDUVH?DWKBFUFPWNTDFIYCUQZEREEVLDKFEZMOQQJLTTUGSYQPFEUNLAVIDXFLGGTEZ?FKZBSFDQVGOGIPUFXHHDRKFFHQNTGPUAECNUVPDJMQCLQUMUNEDFQ" +
            "ELZZVRRGKFFVOEEXBDMVPNFQXEZLGREDNQFMPNZGLFLPMRJQYALMGNUVPDXVKPDQUMEBEDMHDAFMJGZNUPLGEWJLLAETG";

    public static final String k3 = "ENDYAHROHNLSRHEOCPTEOIBIDYSHNAIACHTNREYULDSLLSLLNOHSNOSMRWXMNETPRNGATIHNRARPESLNNELEBLPIIACAEWMTWNDITEENRAHCTENEUDRETNHAEOE" +
            "TFOLSEDTIWENHAEIOYTEYQHEENCTAYCREIFTBRSPAMHHEWENATAMATEGYEERLBTEEFOASFIOTUETUAEOTOARMAEERTNRTIBSEDDNIAAHTTMSTEWPIEROAGRIEWFEBAECTDDHILCEIHSITEGOEAOSDDRYDLORIT" +
            "RKLMLEHAGTDHARDPNEOHMGFMFEUHEECDMRIPFEIMEHNLSSTTRTVDOHW?";

    public static final String k4 = "OBKRUOXOGHULBSOLIFBBWFLRVQQPRNGKSSOTWTQSJQSSEKZZWATJKLUDIAWINFBNYPVTTMZFPKWGDKZXTJCDIGKUHUAUEKCAR";

    public static String encrypt_vigenere(String plainText, String key){
        plainText = plainText.toUpperCase();
        key = key.toUpperCase();
        StringBuilder cipherTextBuilder = new StringBuilder();

        int j = 0;
        for(int i = 0; i < plainText.length(); i++){
            if(plainText.charAt(i) == '?')
                cipherTextBuilder.append('?');
            else {
                // take the next letter and shift it by the next letter in the key
                cipherTextBuilder.append(numberMap[(letterMap.get(plainText.charAt(i)) + letterMap.get(key.charAt(j % key.length()))) % 26]);
                j++;
            }
        }

        return cipherTextBuilder.toString();
    }

    public static String decrypt_vigenere(String cipherText, String key){
        cipherText = cipherText.toUpperCase();
        key = key.toUpperCase();
        StringBuilder plainTextBuilder = new StringBuilder();

        int j = 0;
        for(int i = 0; i < cipherText.length(); i++){
            // take the next letter and shift it by the next letter in the key
            if(cipherText.charAt(i) == '?')
                plainTextBuilder.append('?');
            else {
                plainTextBuilder.append(numberMap[(letterMap.get(cipherText.charAt(i)) - letterMap.get(key.charAt(j % key.length())) + 26) % 26]);
                j++;
            }
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


    //trying to shift "keys" in case the value is cumulative
    public static String kShift(String str, int n){
        String shifted = "";
        for(Character c : str.toCharArray())
            shifted += numberMap[(letterMap.get(c) + n) % 26];

        return shifted;
    }

    public static void main(String[] args) throws Exception{
        // w/o masking
        String keyGuess = "";
        try {
            keyGuess = derive_key("NFBNYPVTTMZFPK", "THEBERLINCLOCK");
            System.out.println(keyGuess);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

        for(int i = 0; i < 10; i++){
            System.out.print(numberMap[(letterMap.get(keyGuess.charAt(i+1)) - letterMap.get(keyGuess.charAt(i)) + 26) % 26]);
        }
        System.out.println("\n");


        //with masking
        // L -> Q, O -> U, E -> A
        String maskGuess = "";
        try {
            maskGuess = derive_key("NFBNYPVTTMZFPK", "THEBARQINCQUCK");
            System.out.println(maskGuess);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

        for(int i = 0; i < 10; i++){
            System.out.print(numberMap[(letterMap.get(maskGuess.charAt(i+1)) - letterMap.get(maskGuess.charAt(i)) + 26) % 26]);
        }
        System.out.println("\n");


        //testing decrypt vigenere method (it works)
        //System.out.println(decrypt_vigenere(k1, "PALIMPSEST"));
        //System.out.println(decrypt_vigenere(k2, "ABSCISSA"));


        //looking for words...
        String guess = decrypt_vigenere(k4, keyGuess +  "A");
        System.out.println(guess.substring(0, 15));
        System.out.println("THEBERLINCLOCKA");
        System.out.println(guess.substring(15, 30));
        System.out.println("THEBERLINCLOCKA");
        System.out.println(guess.substring(30, 45));
        System.out.println("THEBERLINCLOCKA");
        System.out.println(guess.substring(45, 60));
        System.out.println("THEBERLINCLOCKA");
        System.out.println(guess.substring(60, 75));
        System.out.println("THEBERLINCLOCKA");
        System.out.println(guess.substring(75, k4.length()));



        System.out.println();
        String passageGuess = decrypt_vigenere(k4, keyGuess + "A");

        for(int i = 0; i < 26; i++) {
            System.out.println(kShift(passageGuess, i).substring(0, 77));
            System.out.println();
        }


        //split k4 on W's (idea from internet)
        // this is kind of a stretch, but the symmetry is kind of nice
        String kodd =  "OBKRUOXOGHULBSOLIFBBTQSJQSSEKZZINFBNYPVTTMZFPK";
        String keven = "FLRVQQPRNGKSSOTATJKLUDIAGDKZXTJCDIGKUHUAUEKCAR";






    }




}