import java.util.Scanner;

import droidbattle.*;

public class Main {
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
        System.out.println("0. One By One. (1vs1)");
        System.out.println("1. Team By Team. (Team vs Team)");
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
                https://f21.sstrge.online/remote_control.php?time=1662751053&cv=2f1d438eb08e9edd6596e6d5f0461c7e&lr=0&cv2=f50722893d55950fc47813151f8edca0&file=%2Fvideos%2F595000%2F595027%2F595027_720p.mp4&cv3=2d8ee1a66c0ca6ce75d84c67a50a70e9&cv4=abae2f62caa3629162d454816dc7f478

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