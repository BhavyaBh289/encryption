import javax.crypto.Cipher;
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
    public static void decryptfile(File inputFilePath, File outputFilePath, String secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

        try (FileInputStream encryptedInputStream = new FileInputStream(inputFilePath);
             FileOutputStream decryptedOutputStream = new FileOutputStream(outputFilePath)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = encryptedInputStream.read(buffer)) != -1) {
                byte[] decryptedBytes = cipher.update(buffer, 0, bytesRead);
                decryptedOutputStream.write(decryptedBytes);
            }

            byte[] finalDecryptedBytes = cipher.doFinal();
            decryptedOutputStream.write(finalDecryptedBytes);
        }
    }
}
