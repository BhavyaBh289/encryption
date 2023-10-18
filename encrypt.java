import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class encrypt {
    public static void main(String[] args) throws Exception {
        // Replace these with your own values
        String inputFilePath = "/home/bh289/Documents/clg/sem_5/cyber sec/cp/Text File.txt";
        String outputFilePath = "/home/bh289/Documents/clg/sem_5/cyber sec/cp/encFile.txt";
        String secretKey = "YourSecretKeyYourSecretKeyYourSe";

        // Use a fixed IV (16 bytes of zeros for demonstration)
        byte[] fixedIV = new byte[16];
        IvParameterSpec ivParameterSpec = new IvParameterSpec(fixedIV);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");

        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

        try (FileInputStream inputStream = new FileInputStream(inputFilePath);
             FileOutputStream outputStream = new FileOutputStream(outputFilePath);
             CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher)) {

            int read;
            byte[] buffer = new byte[1024];
            while ((read = inputStream.read(buffer)) != -1) {
                cipherOutputStream.write(buffer, 0, read);
            }
        }
    }
}
