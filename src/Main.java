import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static Random random = new Random();
    public static final String SPECIAL_CHARS = "^!?@$#%&*_-=+~|/\\";
    public static final String LOWER_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    public static final String UPPER_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBERS = "0123456789";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // TODO: Design better UI/UX
        System.out.println("Welcome to the password generator.");
        System.out.println("How long should your password be? (3-24)");
        int passwordLength = scanner.nextInt();

        // Prompt user for y/n and "convert" to bool
        String specialCharsInput = "";
        int counter = 0;
        do {
            if (counter > 1) {
                System.out.println("Please enter either \"y\" for yes or \"n\" for no.");
            } else {
                System.out.println("Would you like special characters in your password? (y/n)");
            }
            specialCharsInput = scanner.nextLine();
            counter++;
        } while (!specialCharsInput.equals("y") && !specialCharsInput.equals(specialCharsInput, "n"));

        scanner.close();

        boolean specialChars = specialCharsInput.equals("y");

        System.out.print("Here is your password: " + GeneratePassword(passwordLength, specialChars));
        // TODO: Add option for users to save passwords to a file.
        // TODO: Add functionality to associate passwords with an account.
        //       This should include the ability to encrypt the password file for better security.
    }

    public static String GeneratePassword(int passwordLength, boolean specialChars) {
        // Initialize the string and add letter to avoid starting with nums or specials
        StringBuilder password = new StringBuilder();
        password.append(GetChar(1));

        // Limit selection in GetChar to non-specials if not selected
        int charOptions;
        if (specialChars) {
            charOptions = 9;
        } else {
            charOptions = 8;
        }

        // Build the password
        for (int i = 0; i < passwordLength - 1; i++) {
            password.append(GetChar(random.nextInt(charOptions)));
        }

        // Check to ensure password contains a special char if requested
        if (specialChars) {
            int specialCharCount = 0;
            for (int i = 1; i < password.length(); i++) {
                for (int j = 0; j < SPECIAL_CHARS.length(); j++) {
                    if (password.charAt(i) == SPECIAL_CHARS.charAt(j)) {
                        specialCharCount++;
                    }
                }
            }
            if (specialCharCount == 0) {
                int charToReplace = random.nextInt(passwordLength - 1) + 1;
                password.setCharAt(charToReplace, GetChar(9));
            }
        }

        return password.toString();
    }

    public static char GetChar(int option) {
        // Use switch block to intake option from PasswordGenerator and produce appropriate chars
        char result = '0';
        switch (option) {
            case 1, 2, 3, 4, 5 -> { // Random letter (upper or lower)
                int upperOrLower = random.nextInt(2);
                if (upperOrLower == 0) {
                    result = LOWER_LETTERS.charAt(random.nextInt(26));
                } else {
                    result = UPPER_LETTERS.charAt(random.nextInt(26));
                }
            }
            case 6, 7, 8 -> { // Random single-digit integer
                result = NUMBERS.charAt(random.nextInt(10));
            }
            case 9 -> { // Random special char
                result = SPECIAL_CHARS.charAt(random.nextInt(17));
            }
        }

        return result;
    }
}