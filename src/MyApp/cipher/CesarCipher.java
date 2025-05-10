package MyApp.cipher;

import MyApp.Constants;

public class CesarCipher {
    
    public String shiftText (String input, int key){
        StringBuilder result = new StringBuilder();

        String specialSymbols = ".,«»\"':!? ";
        
        for (char ch : input.toCharArray()){
            if (Character.isUpperCase(ch)){
                char c = (char) (((ch - 'A' + key + Constants.ALPHABET_SIZE) % Constants.ALPHABET_SIZE) + 'A');
                result.append(c);
            } else if (Character.isLowerCase(ch)) {
                char c = (char) (((ch - 'a' + key + Constants.ALPHABET_SIZE) % Constants.ALPHABET_SIZE) + 'a');
                result.append(c);
            } else if ( specialSymbols.indexOf(ch) != -1){
                int index = specialSymbols.indexOf(ch);
                int shiftedIndex = (index + key + specialSymbols.length()) % specialSymbols.length();
                result.append(specialSymbols.charAt(shiftedIndex));
            } else{
                result.append(ch);
            }
        }
        return result.toString();
    }
}
