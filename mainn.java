

import java.io.File;

public class mainn {
    public static void main(String[] args) throws Exception {
        String inputPath = "/home/bh289/Documents/clg/sem_5/cyber sec/cp/tmpp";
        String outputPath = "/home/bh289/Documents/clg/sem_5/cyber sec/cp/tmppp";
        String secretKey="YourSecretKeyYourSecretKeyYourSe";
        Boolean encryptt = false;
        if (encryptt){
            encrypt.encryptfolder(new File(inputPath),new File(outputPath), secretKey);
        }else {
            decrypt.decryptfolder(new File(inputPath),new File(outputPath), secretKey);
        }
    }
}
