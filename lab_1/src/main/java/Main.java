import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) throws IOException {
        FileReaderWriter fileReaderWriter = new FileReaderWriter();
        System.out.println("--------ZADANIE 1-----------");
        CaesarCipher caesarCipher = new CaesarCipher();
        String content = fileReaderWriter.readFromFile("ceaser-cipher-in.txt", StandardCharsets.UTF_8);
        System.out.println(content);
        int s =10;
        System.out.println("Text  : " + content);
        System.out.println("Shift : " + s);
        String encrypted = caesarCipher.encrypt(content, s);
        System.out.println("encrypted: " + encrypted);
        String decrypted = caesarCipher.decrypt(encrypted, s);
        System.out.println("decrypted: " + decrypted );
        fileReaderWriter.saveToFile("ceaser-cipher-out.txt",decrypted);

        System.out.println("--------ZADANIE 2-----------");
        DictionaryCompression dictionaryCompression = new DictionaryCompression();
        String beforeCompression = fileReaderWriter.readFromFile("dictionary-compression-in.txt", StandardCharsets.UTF_8);
        System.out.println("BEFORE COMPRESSION: "+ beforeCompression);
        String afterCompression = dictionaryCompression.compression(beforeCompression);
        System.out.println("AFTER COMPRESSION: "+ afterCompression);
        fileReaderWriter.saveToFile("dictionary-compression-out.txt",afterCompression);


    }
}
