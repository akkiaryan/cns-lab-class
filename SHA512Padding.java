import java.util.Scanner;

public class SHA512Padding {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input message size in bits
        System.out.print("Enter the message size in bits: ");
        long messageSize = scanner.nextLong();

        // SHA-512 block size is 1024 bits
        long blockSize = 1024;

        // Padding calculation
        long paddingBits = (blockSize - (messageSize + 128) % blockSize) % blockSize;
        long totalBlocks = (messageSize + paddingBits + 128) / blockSize;

        System.out.println("Padding bits required: " + paddingBits);
        System.out.println("Total number of blocks required: " + totalBlocks);
        
        scanner.close();
    }
}
