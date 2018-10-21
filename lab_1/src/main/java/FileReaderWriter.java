import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileReaderWriter {

    String readFromFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    void saveToFile(String path, String text)
            throws IOException
    {
        Files.write( Paths.get(path), text.getBytes(), StandardOpenOption.CREATE);
    }
}
