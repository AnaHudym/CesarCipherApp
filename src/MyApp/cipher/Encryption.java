package MyApp.cipher;

public class Encryption extends CesarCipherAction{
    private final int key;
    private final CesarCipher cipher = new CesarCipher();

    public Encryption (int key){
        this.key = key;
    }

    @Override
    public String execute(String text) {
        return cipher.shiftText(text, key);
    }
}
