import java.util.Scanner;

public class MD5Round1 {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Getting user input for MD buffers
        System.out.print("Enter initial MD buffers (A, B, C, D) in hex format:\n");
        System.out.print("A: "); int A = Integer.parseUnsignedInt(scanner.next(), 16);
        System.out.print("B: "); int B = Integer.parseUnsignedInt(scanner.next(), 16);
        System.out.print("C: "); int C = Integer.parseUnsignedInt(scanner.next(), 16);
        System.out.print("D: "); int D = Integer.parseUnsignedInt(scanner.next(), 16);

        // Getting user input for M0 and K1
        System.out.print("Enter M0 (in hex): "); int M0 = Integer.parseUnsignedInt(scanner.next(), 16);
        System.out.print("Enter K1 (in hex): "); int K1 = Integer.parseUnsignedInt(scanner.next(), 16);

        // Step 1 Transformation
        int[] buffers = {A, B, C, D};
        md5Transform(buffers, M0, K1);
        
        System.out.println("\nAfter Step 1 - Round 1:");
        printBuffers(buffers);

        // Using the output of Step 1 as input for Step 2
        System.out.print("\nEnter M1 (in hex): "); int M1 = Integer.parseUnsignedInt(scanner.next(), 16);
        System.out.print("Enter K2 (in hex): "); int K2 = Integer.parseUnsignedInt(scanner.next(), 16);

        // Step 2 Transformation
        md5Transform(buffers, M1, K2);
        
        System.out.println("\nAfter Step 2 - Round 1:");
        printBuffers(buffers);

        scanner.close();
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

    // Print the buffer values in hexadecimal
    private static void printBuffers(int[] buffers) {
        System.out.printf("A: %08X, B: %08X, C: %08X, D: %08X%n", buffers[0], buffers[1], buffers[2], buffers[3]);
    }
}
