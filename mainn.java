

import java.io.File;

public class mainn {
    public static void main(String[] args) throws Exception {
        String inputPath = "/home/bh289/Documents/clg/sem_5/cyber sec/cp/tmp";
        String outputPath = "/home/bh289/Documents/clg/sem_5/cyber sec/cp/tmpp";
        String secretKey="YourSecretKeyYourSecretKeyYourSe";
        Boolean encryptt = true;
        if (encryptt){
            encrypt.encryptfolder(new File(inputPath),new File(outputPath), secretKey);
        }else {
            decrypt.decryptfolder(new File(inputPath),new File(outputPath), secretKey);
        }
    }
}
