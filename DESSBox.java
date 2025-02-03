import java.util.Scanner;

public class DESSBox {

    // Example S-Boxes (for S1, S5, and S8 only)
    private static final int[][] S1 = {
        {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
        {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
        {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
        {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
    };

    private static final int[][] S5 = {
        {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
        {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
        {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
        {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}
    };

    private static final int[][] S8 = {
        {13, 1, 2, 15, 8, 13, 4, 10, 11, 7, 6, 0, 14, 9, 3, 5},
        {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
        {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9},
        {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4}
    };

    public static String applySBox(String input, int[][] sBox) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < input.length(); i += 6) {
            String chunk = input.substring(i, i + 6);

            // Row: First and Last bit
            int row = Integer.parseInt("" + chunk.charAt(0) + chunk.charAt(5), 2);

            // Column: Middle 4 bits
            int col = Integer.parseInt(chunk.substring(1, 5), 2);

            // Lookup S-Box value
            int value = sBox[row][col];
            output.append(String.format("%4s", Integer.toBinaryString(value)).replace(' ', '0'));
        }

        return output.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the 6-bit binary strings
        System.out.print("Enter a 6-bit binary string for S1: ");
        String s1Input = scanner.nextLine();

        System.out.print("Enter a 6-bit binary string for S5: ");
        String s5Input = scanner.nextLine();

        System.out.print("Enter a 6-bit binary string for S8: ");
        String s8Input = scanner.nextLine();

        // Validate input
        if (s1Input.length() != 6 || !s1Input.matches("[01]+") ||
            s5Input.length() != 6 || !s5Input.matches("[01]+") ||
            s8Input.length() != 6 || !s8Input.matches("[01]+")) {
            System.out.println("Invalid input. Each input must be exactly 6 binary digits (0s and 1s).");
            return;
        }

        // Apply S-Boxes
        String s1Output = applySBox(s1Input, S1);
        String s5Output = applySBox(s5Input, S5);
        String s8Output = applySBox(s8Input, S8);

        // Display results
        System.out.println("S1 Output: " + s1Output);
        System.out.println("S5 Output: " + s5Output);
        System.out.println("S8 Output: " + s8Output);

        scanner.close();
    }
}