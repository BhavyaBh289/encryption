import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.Objects;

public class encrypt {

    public static void encryptfolder(File inputFolderPath ,File outputFolderPath ,String secretKey  )throws Exception {
        if (!outputFolderPath.exists()) {
            outputFolderPath.mkdirs();
        }
        for (File sourceFile : Objects.requireNonNull(inputFolderPath.listFiles())) {
            if (sourceFile.isDirectory()) {
                File subfolderDestination = new File(outputFolderPath, sourceFile.getName());
                encryptfolder(sourceFile, subfolderDestination, secretKey);
            } else {
                encryptfile(sourceFile, new File(outputFolderPath, sourceFile.getName()), secretKey);
            }
        }
    }
    public static void encryptfile(File inputFilePath, File outputFilePath, String secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        try (InputStream inputStream = new FileInputStream(inputFilePath);
             OutputStream outputStream = new FileOutputStream(outputFilePath)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byte[] encryptedBytes = cipher.update(buffer, 0, bytesRead);
                outputStream.write(encryptedBytes);
            }

            byte[] finalEncryptedBytes = cipher.doFinal();
            outputStream.write(finalEncryptedBytes);
        }
    }
}
