import java.util.Scanner;

public class DESPermutation {

    // Initial Permutation (IP) Table
    private static final int[] IP = {
        58, 50, 42, 34, 26, 18, 10, 2,
        60, 52, 44, 36, 28, 20, 12, 4,
        62, 54, 46, 38, 30, 22, 14, 6,
        64, 56, 48, 40, 32, 24, 16, 8,
        57, 49, 41, 33, 25, 17, 9,  1,
        59, 51, 43, 35, 27, 19, 11, 3,
        61, 53, 45, 37, 29, 21, 13, 5,
        63, 55, 47, 39, 31, 23, 15, 7
    };

    // Final Permutation (FP) Table
    private static final int[] FP = {
        40, 8, 48, 16, 56, 24, 64, 32,
        39, 7, 47, 15, 55, 23, 63, 31,
        38, 6, 46, 14, 54, 22, 62, 30,
        37, 5, 45, 13, 53, 21, 61, 29,
        36, 4, 44, 12, 52, 20, 60, 28,
        35, 3, 43, 11, 51, 19, 59, 27,
        34, 2, 42, 10, 50, 18, 58, 26,
        33, 1, 41, 9, 49, 17, 57, 25
    };

    // Function to apply permutation based on a given table
    private static String permute(String input, int[] table) {
        StringBuilder output = new StringBuilder();
        for (int position : table) {
            output.append(input.charAt(position - 1)); // Positions in the table are 1-based
        }
        return output.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the 64-bit binary string
        System.out.print("Enter a 64-bit binary string: ");
        String input = scanner.nextLine();

        // Validate the input
        if (input.length() != 64 || !input.matches("[01]+")) {
            System.out.println("Invalid input. Please enter exactly 64 binary digits (0s and 1s).");
            return;
        }

        // Apply Initial Permutation
        String initialPermutation = permute(input, IP);

        // Apply Final Permutation
        String finalPermutation = permute(initialPermutation, FP);

        // Display results
        System.out.println("Input (64-bit):           " + input);
        System.out.println("After Initial Permutation: " + initialPermutation);
        System.out.println("After Final Permutation:   " + finalPermutation);

        scanner.close();
    }
}