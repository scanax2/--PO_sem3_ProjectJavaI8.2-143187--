package Project;

import java.util.Arrays;

public class DatabaseAlgorithms {
    // Unexpected changes LuhnAlgorith(int value) -> LuhnAlgorith(String value)
    public boolean luhnAlgorithm(String value){  

        int sum1 = 0; 
        int sum2 = 0;
        final int nDigits = value.length();
        for (int i = nDigits; i > 0; i--){

            int digit = Character.getNumericValue(value.charAt(i-1));
            int z = digit; 
            int y = digit;

            if (i % 2 != 0){
                z *= 2;
                if (z > 9) {
                    z -= 9;
                }
                sum1 += z;
            }
            else  sum2 += y;           
        }
        int sum = sum1 + sum2;
        if (value.length() != 16){
            sum = 1;
        }

        if (sum % 10 == 0){
            return true;
        } 
        else{
            return false;
        }
    }
    public int levensteinDistance(String word1, String word2){

        if (word1.isEmpty()) {
            return word2.length();
        }

        if (word2.isEmpty()) {
            return word1.length();
        } 
        int costOfSubstitution = word1.charAt(0) == word2.charAt(0) ? 0 : 1;
        int substitution = levensteinDistance(word1.substring(1), word2.substring(1)) + costOfSubstitution;
        int insertion = levensteinDistance(word1, word2.substring(1)) + 1;
        int deletion = levensteinDistance(word1.substring(1), word2) + 1;

        int[] numbers = {substitution, insertion, deletion};

        return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
    }
}