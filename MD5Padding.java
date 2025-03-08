import java.util.Scanner;

public class MD5Padding {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Get the original message
        System.out.print("Enter the original message: ");
        String message = scanner.nextLine();
        
        // Convert message to binary
        StringBuilder binaryMessage = new StringBuilder();
        for (char c : message.toCharArray()) {
            binaryMessage.append(String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0'));
        }
        
        int originalLength = binaryMessage.length();
        
        // Padding process (1 bit followed by 0s)
        StringBuilder padding = new StringBuilder("1");
        while ((binaryMessage.length() + padding.length() + 64) % 512 != 0) {
            padding.append("0");
        }
        
        // Convert original length to binary (64-bit representation)
        String lengthBinary = String.format("%64s", Integer.toBinaryString(originalLength)).replace(' ', '0');
        
        // Display results
        System.out.println("\nOriginal Message in Binary: " + binaryMessage);
        System.out.println("Total Padding Bits: " + padding.length());
        System.out.println("Padding Bits in Binary: " + padding);
        System.out.println("Original Message Length: " + originalLength + " bits");
        System.out.println("Length in Binary (64-bit representation): " + lengthBinary);
        
        scanner.close();
    }
}
