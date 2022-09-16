import java.io.FileReader;
import java.util.Scanner;

import droidbattle.*;

/**
 * Code GIT Hub version {@link https://github.com/Vadymbo-here/NULP-PP.git}
 * 
 * Here we start our program and calls to diff classes
 * 
 * @author Vadym_Bogorodetskyy KN-207
 */
public class Main {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println("WELCOME TO MY VERSION OF A DROID BATTLE");
        System.out.println(
                "          \\\n        \\\n         \\%,     ,'     , ,.\n          \\%\\,';/J,\";\";\";;,,.\n~.------------\\%;((\');)));\';;,.,-----------,~\n~~:           ,\';@)((;\',\'((;(;;);;,\'         :~~\n~~ :           ;\'(@\'\'\'))\'~ \'\'; );(;));;,      : ~~\n~~  :            \'X \'(( \'),    (;;);;;;\'       :  ~~\n~~~~ :            / \') \'\' /;~   \';;;;;;;);,     :  ~~~~\n~~~~  :           / , \' ,/\' /     (\';;(;;;;,     : ~~~~\n~~~ :          (o  /] /\' /     ,);;;\';;;;;\',,  : ~~~\n~~ :           \'~\' \'~\'  \'      \'\';,  \'\';\" ';, : ~~\n~~:                             \''   \''  \''  :~~\n~\'-----------------------------------------\'~");
        System.out.println("=".repeat(40));
        System.out.println("=".repeat(40));
        System.out.println("\n");

        System.out.println(
                "Let's start our battles of droids. We have 3 types of arenas and droids.\nAlso, two GAME modes.\nThis is what we will choose first!\n");
        System.out.println("ENTER you option:");
        System.out.println("0. One By One. (1vs1).");
        System.out.println("1. Team By Team. (Team vs Team).");
        System.out.println("2. Show last FIGHT.");
        try (Scanner inp = new Scanner(System.in)) {
            int userInput;
            System.out.print("PLAYER: ");
            userInput = inp.nextInt();
            switch (userInput) {
                case 0:
                    System.out.println("Great choise. One by one battles the only way to explore the strongest hero!");
                    new DroidBattle().battle();
                    break;

                case 1:
                    new DroidBattle().partyBatlle();
                    break;

                case 2:
                    FileReader fr = new FileReader("battleLog.txt");
                    int i;
                    while ((i = fr.read()) != -1) {
                        System.out.print((char) i);
                    }
                    break;

                default:
                    System.out.println("Wrong input!!!");
                    System.out.println("RESTARTING THE GAME.");
                    main(args);
                    break;
            }
        }
    }
}