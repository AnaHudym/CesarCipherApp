package MyApp.cipher;

import MyApp.Constants;

public class BruteForce extends CesarCipherAction {
    @Override
    public String execute(String text) {

        String[] commonEnglishWords = {"the", "and", "to", "is", "it", "of", "for", "has"};

        String bestAttempt = "";
        int maxFoundWords = 0;

        for (int key = 1; key < Constants.ALPHABET_SIZE; key++) {
            String attempt = new Decryption(key).execute(text);

            int foundWords = 0;
            for (String word : commonEnglishWords){
                if (attempt.toLowerCase().contains(word)){
                    foundWords++;
                }
            }
            if (foundWords > maxFoundWords){
                maxFoundWords = foundWords;
                bestAttempt = attempt;
            }
            if (maxFoundWords > 2){
                return bestAttempt;
            }
        }
        return "Failed to find the key";
    }
}
