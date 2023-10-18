import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Objects;

public class decrypt {
    public static void decryptfolder(File inputFolderPath ,File outputFolderPath ,String secretKey  )throws Exception {
        if (!outputFolderPath.exists()) {
            outputFolderPath.mkdirs();
        }
        for (File sourceFile : Objects.requireNonNull(inputFolderPath.listFiles())) {
            if (sourceFile.isDirectory()) {
                File subfolderDestination = new File(outputFolderPath, sourceFile.getName());
                decryptfolder(sourceFile, subfolderDestination, secretKey);
            } else {
                decryptfile(sourceFile, new File(outputFolderPath, sourceFile.getName()), secretKey);
            }
        }
    }
    public static void decryptfile(File inputFilePath ,File outputFilePath ,String secretKey  )throws Exception {

        byte[] iv = new byte[16];

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

        try (FileInputStream encryptedInputStream = new FileInputStream(inputFilePath);
             CipherInputStream cipherInputStream = new CipherInputStream(encryptedInputStream, cipher);
             FileOutputStream decryptedOutputStream = new FileOutputStream(outputFilePath)) {

            int read;
            byte[] buffer = new byte[1024];
            while ((read = cipherInputStream.read(buffer)) != -1) {
                decryptedOutputStream.write(buffer, 0, read);
            }
        }
    }
}
