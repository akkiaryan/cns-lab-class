import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Scanner;

public class DESExample {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine();

        // Generate a DES key
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        keyGen.init(56); // DES uses a 56-bit key size
        SecretKey secretKey = keyGen.generateKey();

        // Encryption
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);

        System.out.println("Encrypted (Ciphertext): " + encryptedText);

        // Decryption
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        String decryptedText = new String(decryptedBytes);

        System.out.println("Decrypted Text: " + decryptedText);

        scanner.close();
    }
}