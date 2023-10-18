import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class decrypt {
    public static void main(String[] args) throws Exception {
        // Replace these with your own values
        String encryptedFilePath = "/home/bh289/Documents/clg/sem_5/cyber sec/cp/encFile.txt";
        String decryptedFilePath = "/home/bh289/Documents/clg/sem_5/cyber sec/cp/decFile.txt";
        String secretKey = "YourSecretKeyYourSecretKeyYourSe";

        byte[] iv = new byte[16]; // Initialization Vector

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

        try (FileInputStream encryptedInputStream = new FileInputStream(encryptedFilePath);
             CipherInputStream cipherInputStream = new CipherInputStream(encryptedInputStream, cipher);
             FileOutputStream decryptedOutputStream = new FileOutputStream(decryptedFilePath)) {

            int read;
            byte[] buffer = new byte[1024];
            while ((read = cipherInputStream.read(buffer)) != -1) {
                decryptedOutputStream.write(buffer, 0, read);
            }
        }
    }
}
