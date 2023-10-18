import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
    public static void encryptfile(File inputFilePath ,File outputFilePath ,String secretKey  )throws Exception {
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
