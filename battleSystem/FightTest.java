package battleSystem;

// The "FightTest" class.
import java.awt.*;
import hsa.Console;

public class FightTest
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	c = new Console (35, 187);

	screen dis = new screen (c, 1);

	int[] skills = {0, 0, 0, 0};
	stats st = new stats (1, 1, 1, 1, 2, skills, 0, 1, 1, 0);
	fighter fightA = new player (dis, "player A", 1, 1, st, st.health, st.mana);
	fighter fightB = new enemy (dis, "test B", 10, 2, 1);
	fightA.setOpponent (fightB);
	fightB.setOpponent (fightA);

	combat battle = new combat (fightA, fightB);

	battle.fight (dis);
    } // main method
} // FightTest class
