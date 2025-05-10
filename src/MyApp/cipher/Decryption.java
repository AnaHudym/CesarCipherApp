package MyApp.cipher;

public class Decryption extends CesarCipherAction{
    private final int key;
    private final CesarCipher cipher = new CesarCipher();

    public Decryption (int key){
        this.key = key;
    }

    @Override
    public String execute(String text) {
        return cipher.shiftText(text, -key);
    }
}
