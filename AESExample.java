import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class AESExample {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine();

        System.out.print("Enter a 16-character key: ");
        String key = scanner.nextLine();

        // Create secret key
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");

        // Encryption
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
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