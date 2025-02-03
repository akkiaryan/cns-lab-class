import java.util.Scanner;

public class AESMixColumns {

    // Predefined Mix Column matrix
    private static final int[][] MIX_COLUMN_MATRIX = {
        {0x02, 0x03, 0x01, 0x01},
        {0x01, 0x02, 0x03, 0x01},
        {0x01, 0x01, 0x02, 0x03},
        {0x03, 0x01, 0x01, 0x02}
    };

    // Galois field multiplication
    private static int galoisMultiply(int a, int b) {
        int result = 0;
        while (b > 0) {
            if ((b & 1) == 1) {
                result ^= a;
            }
            a = (a << 1);
            if ((a & 0x100) != 0) {
                a ^= 0x1b; // Irreducible polynomial
            }
            b >>= 1;
        }
        return result & 0xFF; // Ensure result is 8-bit
    }

    // Mix Column Transformation
    private static int[][] mixColumns(int[][] state) {
        int[][] result = new int[4][4];
        for (int col = 0; col < 4; col++) {
            for (int row = 0; row < 4; row++) {
                result[row][col] = galoisMultiply(MIX_COLUMN_MATRIX[row][0], state[0][col])
                                 ^ galoisMultiply(MIX_COLUMN_MATRIX[row][1], state[1][col])
                                 ^ galoisMultiply(MIX_COLUMN_MATRIX[row][2], state[2][col])
                                 ^ galoisMultiply(MIX_COLUMN_MATRIX[row][3], state[3][col]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the 4x4 state matrix
        int[][] state = new int[4][4];
        System.out.println("Enter the 4x4 state matrix (in hexadecimal): ");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                state[i][j] = Integer.parseInt(scanner.next(), 16);
            }
        }

        // Apply Mix Column operation
        int[][] mixedState = mixColumns(state);

        // Display the result
        System.out.println("After Mix Column Transformation:");
        for (int[] row : mixedState) {
            for (int val : row) {
                System.out.printf("%02X ", val);
            }
            System.out.println();
        }

        scanner.close();
    }
}