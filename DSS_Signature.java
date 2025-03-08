import java.math.BigInteger;
import java.security.*;
import java.util.Scanner;

public class DSS_Signature {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        // Step 1: Get two prime numbers from user
        System.out.print("Enter first prime number (p): ");
        BigInteger p = new BigInteger(sc.nextLine());
        System.out.print("Enter second prime number (q): ");
        BigInteger q = new BigInteger(sc.nextLine());

        // Step 2: Generate the public key
        System.out.print("Enter a private key (should be less than q): ");
        BigInteger privateKey = new BigInteger(sc.nextLine());

        System.out.print("Enter a random integer (k): ");
        BigInteger k = new BigInteger(sc.nextLine());

        System.out.print("Enter the original message: ");
        String message = sc.nextLine();

        // Compute Hash (h) of the message
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] messageHash = md.digest(message.getBytes());
        BigInteger h = new BigInteger(1, messageHash);

        // Step 3: Compute r = (g^k mod p) mod q
        BigInteger g = new BigInteger("2"); // Example generator g
        BigInteger r = g.modPow(k, p).mod(q);

        // Step 4: Compute s = (h + privateKey * r) * k^-1 mod q
        BigInteger kInv = k.modInverse(q);
        BigInteger s = (h.add(privateKey.multiply(r))).multiply(kInv).mod(q);

        // Public Key computation: y = g^x mod p
        BigInteger publicKey = g.modPow(privateKey, p);

        // Sender Side Output
        System.out.println("\nSender Side Output:");
        System.out.println("r: " + r);
        System.out.println("Public Key (y): " + publicKey);
        System.out.println("a (random integer k): " + k);
        System.out.println("b (message hash h): " + h);

        // Receiver Side: Verification
        BigInteger w = s.modInverse(q);
        BigInteger u1 = (h.multiply(w)).mod(q);
        BigInteger u2 = (r.multiply(w)).mod(q);
        BigInteger v = ((g.modPow(u1, p).multiply(publicKey.modPow(u2, p))).mod(p)).mod(q);

        // Receiver Side Output
        System.out.println("\nReceiver Side Output:");
        System.out.println("z (Hash of received message h): " + h);
        System.out.println("u1: " + u1);
        System.out.println("u2: " + u2);
        System.out.println("Verification Formula (v): " + v);

        if (v.equals(r)) {
            System.out.println("Signature Verified: ✅ Valid");
        } else {
            System.out.println("Signature Verification Failed: ❌ Invalid");
        }

        sc.close();
    }
}
