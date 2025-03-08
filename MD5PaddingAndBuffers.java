import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MD5PaddingAndBuffers {
    
    // Initial MD5 buffer values
    static int A = 0x67452301;
    static int B = 0xEFCDAB89;
    static int C = 0x98BADCFE;
    static int D = 0x10325476;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the original message from the user
        System.out.print("Enter the original message: ");
        String message = scanner.nextLine();
        scanner.close();

        // Step 1: Convert message to binary
        String binaryMessage = toBinary(message);
        System.out.println("Original message in binary: " + binaryMessage);

        // Step 2: Calculate padding
        int originalLength = binaryMessage.length();
        int paddingBits = (448 - (originalLength % 512) + 512) % 512;
        String padding = "1" + "0".repeat(paddingBits - 1);
        
        // Step 3: Append length in 64-bit binary
        String lengthBinary = String.format("%64s", Integer.toBinaryString(originalLength)).replace(' ', '0');

        // Step 4: Display results
        System.out.println("Total number of padding bits: " + paddingBits);
        System.out.println("Padding in binary: " + padding);
        System.out.println("Length of the original message: " + originalLength);
        System.out.println("Length of the original message in binary: " + lengthBinary);

        // Step 5: MD5 Buffer Transformation - Round 1
        int M0 = 0xD76AA478, K1 = 0xE8C7B756, M1 = 0x242070DB, K2 = 0xC1BDCEEE;
        int[] buffers = {A, B, C, D};

        // Step 1 of Round 1
        md5Transform(buffers, M0, K1);
        System.out.println("\nAfter Step 1 - Round 1:");
        printBuffers(buffers);

        // Step 2 of Round 1
        md5Transform(buffers, M1, K2);
        System.out.println("\nAfter Step 2 - Round 1:");
        printBuffers(buffers);
    }

    // Convert message to binary representation
    private static String toBinary(String text) {
        StringBuilder binary = new StringBuilder();
        for (char c : text.toCharArray()) {
            binary.append(String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0'));
        }
        return binary.toString();
    }

    // Simulated MD5 transformation step
    private static void md5Transform(int[] buffers, int M, int K) {
        int F = (buffers[1] & buffers[2]) | (~buffers[1] & buffers[3]); // Nonlinear function
        int temp = buffers[0] + F + M + K;
        buffers[0] = buffers[3];
        buffers[3] = buffers[2];
        buffers[2] = buffers[1];
        buffers[1] = buffers[1] + Integer.rotateLeft(temp, 7);
    }

    // Print the current buffer values
    private static void printBuffers(int[] buffers) {
        System.out.printf("A: %08X, B: %08X, C: %08X, D: %08X%n", buffers[0], buffers[1], buffers[2], buffers[3]);
    }
}
