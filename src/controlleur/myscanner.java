package controlleur;

import java.util.Scanner;

public class myscanner {
    public static int getIntInput(String... msg) {
        Scanner scan = new Scanner(System.in);
        String message = "Entrez un nombre entier : ";
        if (msg.length > 0 )
            message = msg[0] ;
        System.out.print(message);


        // This method reads the number provided using keyboard
        int num = scan.nextInt();

        // Closing Scanner after the use
        //  scan.close();
        return num;
    }

    public static String getStringInput(String... msg) {
        Scanner scan = new Scanner(System.in);
        String message = "Entrez un texte : ";
        if (msg.length > 0 )
            message = msg[0] ;
        System.out.print(message);

        // This method reads the number provided using keyboard
        String str = scan.nextLine();

        // Closing Scanner after the use
        //  scan.close();
        return str;
    }
}
