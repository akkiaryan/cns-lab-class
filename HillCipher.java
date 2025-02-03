import java.util.Scanner;

public class HillCipher {

    private static int[][] createKeyMatrix(String key, int size) {
        int[][] keyMatrix = new int[size][size];
        int index = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                keyMatrix[i][j] = key.charAt(index) - 'A';
                index++;
            }
        }
        return keyMatrix;
    }

    private static int[] createTextMatrix(String text, int size) {
        int[] textMatrix = new int[size];
        for (int i = 0; i < size; i++) {
            textMatrix[i] = text.charAt(i) - 'A';
        }
        return textMatrix;
    }

    private static int[] multiplyMatrix(int[][] keyMatrix, int[] textMatrix, int size) {
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i] += keyMatrix[i][j] * textMatrix[j];
            }
            result[i] %= 26;
        }
        return result;
    }

    private static String matrixToString(int[] matrix) {
        StringBuilder result = new StringBuilder();
        for (int value : matrix) {
            result.append((char) (value + 'A'));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the key (9 characters for 3x3 matrix): ");
        String key = scanner.nextLine();

        System.out.print("Enter the plaintext (3 characters): ");
        String plaintext = scanner.nextLine();

        int size = 3;
        int[][] keyMatrix = createKeyMatrix(key, size);
        int[] textMatrix = createTextMatrix(plaintext, size);

        int[] ciphertextMatrix = multiplyMatrix(keyMatrix, textMatrix, size);
        String ciphertext = matrixToString(ciphertextMatrix);

        System.out.println("Plaintext: " + plaintext);
        System.out.println("Ciphertext: " + ciphertext);

        scanner.close();
    }
}