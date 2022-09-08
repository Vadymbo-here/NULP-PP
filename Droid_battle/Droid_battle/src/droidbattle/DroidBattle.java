package droidbattle;

import arenas.*;
import controller.DuelController;
import droids.*;

public class DroidBattle {
    public void battle() throws InterruptedException {
        Droid droid1 = new Droid("Saitama", 100, 15, 8, 70, 5);
        Droid droid2 = new Droid("Kageyama", 100, 20, 5, 90, 15);

        // choose arena: field , ..., ...

        Arena arena = new Arena(0, 0);
        DuelController control = new DuelController(droid1, droid2, arena);
        Droid winner = control.startFight();

        System.out.println("--------------");
        System.out.println("The winner is " + winner.getName());

    }

    public void partyBatlle() throws InterruptedException {
        //
    }
}
