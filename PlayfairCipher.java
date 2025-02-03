import java.util.Scanner;

public class PlayfairCipher {

    private static char[][] createMatrix(String key) {
        char[][] matrix = new char[5][5];
        boolean[] used = new boolean[26];
        used['J' - 'A'] = true; // Treat J as I
        key = key.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");

        int index = 0;
        for (char c : key.toCharArray()) {
            if (!used[c - 'A']) {
                matrix[index / 5][index % 5] = c;
                used[c - 'A'] = true;
                index++;
            }
        }

        for (char c = 'A'; c <= 'Z'; c++) {
            if (!used[c - 'A']) {
                matrix[index / 5][index % 5] = c;
                index++;
            }
        }
        return matrix;
    }

    private static String processText(String text) {
        text = text.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        StringBuilder processed = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            processed.append(text.charAt(i));
            if (i < text.length() - 1 && text.charAt(i) == text.charAt(i + 1)) {
                processed.append('X');
            }
        }
        if (processed.length() % 2 != 0) {
            processed.append('X');
        }
        return processed.toString();
    }

    private static String encrypt(String plaintext, char[][] matrix) {
        plaintext = processText(plaintext);
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i += 2) {
            char a = plaintext.charAt(i);
            char b = plaintext.charAt(i + 1);
            int[] posA = findPosition(a, matrix);
            int[] posB = findPosition(b, matrix);

            if (posA[0] == posB[0]) {
                ciphertext.append(matrix[posA[0]][(posA[1] + 1) % 5]);
                ciphertext.append(matrix[posB[0]][(posB[1] + 1) % 5]);
            } else if (posA[1] == posB[1]) {
                ciphertext.append(matrix[(posA[0] + 1) % 5][posA[1]]);
                ciphertext.append(matrix[(posB[0] + 1) % 5][posB[1]]);
            } else {
                ciphertext.append(matrix[posA[0]][posB[1]]);
                ciphertext.append(matrix[posB[0]][posA[1]]);
            }
        }
        return ciphertext.toString();
    }

    private static int[] findPosition(char c, char[][] matrix) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == c) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the key: ");
        String key = scanner.nextLine();

        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine();

        char[][] matrix = createMatrix(key);
        String ciphertext = encrypt(plaintext, matrix);

        System.out.println("Plaintext: " + plaintext);
        System.out.println("Ciphertext: " + ciphertext);

        scanner.close();
    }
}