import java.util.Scanner;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Bar length: ");
        int length = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Bar character: ");
        String bar = scanner.nextLine();

        System.out.print("Bar comment: ");
        String comment = scanner.nextLine();

        String result;

        try {
            result = generateBarString(comment, length, (bar.length() > 0) ? bar : "=");
            System.out.println(result);
            System.out.print("Copy to clipboard? (y/n) ");
            if (scanner.nextLine().startsWith("y"))
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(result), null);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        System.out.print("Generate another? (y/n) ");
        if (scanner.nextLine().startsWith("y"))
            main(args);

        scanner.close();
    }

    public static String generateBarString(String comment, int length, String bar) throws IllegalArgumentException {
        if (length < comment.length() + 2)
            throw new IllegalArgumentException("Length (" + length + ") < length of \" " + comment + " \" (" + (comment.length() + 2) + ")");

        String returnString = "";
        double wing = (length - comment.length() - 2) / 2;
        while (returnString.length() < wing)
            for (int i = 0; i < bar.length() && returnString.length() < wing; i++)
                returnString += bar.substring(i, i+1);
        returnString += " " + comment + " ";
        while (returnString.length() < length)
            for (int i = 0; i < bar.length() && returnString.length() < length; i++)
                returnString += bar.substring(i, i+1);
        return returnString;
    }
    
    public static String generateBarString(String comment, int length, char bar) throws IllegalArgumentException {
        return generateBarString(comment, length, Character.toString(bar));
    }

    public static String generateBarString(String comment, int length) throws IllegalArgumentException {
        return generateBarString(comment, length, '=');
    }
}
