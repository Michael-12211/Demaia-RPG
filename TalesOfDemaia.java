import java.awt.*;
import java.io.*;
import java.util.*;
import hsa.Console;

/*
essentials file indexes
name: 0
area: 50
map: 100
map x: 150
map y: 160
map z: 170
x: 180
y: 190
race: 200
level: 210
current helath: 220
current mana: 230
helmet: 240
armor: 250
weapon: 260
boots: 270
accessory: 280
tool 1: 290
tool 2: 300
skill 1: 310
skill 2: 320
skill 3: 330
skill 4: 340
exp: 350
*/

public class TalesOfDemaia
{
    static Console c;           // The output console

    public static void main (String[] args) throws IOException
    {
	c = new Console (35, 187);

	while (true)
	{
	    c.clear ();
	    c.println ("Welcome player");
	    c.println ("Select an option");
	    c.println ("0: exit");
	    c.println ("1: load save");
	    c.println ("2: new game");

	    int choice = c.readInt ();

	    if (choice == 0) //exit
	    {
		break;
	    }
	    if (choice == 1) //load game
	    {
		load ();
	    }
	    if (choice == 2) //new game
	    {
		make ();
	    }
	}
    } // main method


    public static void load () throws IOException
    {
	c.clear ();
	c.println ("what is the name of the save file you wish to load?");
	String name = c.readString ();
	enter (name);
    }


    public static void make () throws IOException
    {
	c.clear ();
	c.println ("First, what will you save your game under?");
	c.println ("Enter any one word. It will become the name of the save file");
	String file = c.readString ();
	c.clear ();
	c.println ("Next, please tell me your name");
	String name = c.readString ();
	int race = 0;
	int[] sk = {0, 0, 0, 0};
	battleSystem.stats st;
	while (true)
	{
	    c.clear ();
	    c.println ("What race do you wish to be?");
	    c.println ("This will determine your base stats");
	    c.println ("1: human");
	    c.println ("2: marapet l");
	    c.println ("3: marapet s");
	    c.println ("4: goblin");
	    c.println ("5: kobold");
	    c.println ("6: wyvern");
	    race = c.readInt ();
	    st = new battleSystem.stats (1, race, 0, 0, 0, sk, 0, 0, 0, 0);
	    c.println ();
	    c.println ("as such, your stats are:");
	    c.println ("health: " + st.health);
	    c.println ("mana: " + st.mana);
	    c.println ("strength: " + st.attack);
	    c.println ("arcana: " + st.arcana);
	    c.println ("speed: " + st.speed);
	    c.println ("Is this acceptable for you? (y)");
	    if (c.getChar () == 'y')
	    {
		break;
	    }
	}
	c.clear ();
	c.println ("That is all. Enjoy the game");
	c.getChar ();

	//begingin of making files
	RandomAccessFile raf = new RandomAccessFile ("users/" + file + "/essentials", "rw");
	raf.seek (0);
	raf.writeUTF (name);
	raf.seek (50);
	raf.writeUTF ("farm");
	raf.seek (100);
	raf.writeUTF ("family_farm");
	raf.seek (150);
	raf.writeInt (0);
	raf.seek (160);
	raf.writeInt (1);
	raf.seek (170);
	raf.writeInt (0);
	raf.seek (180);
	raf.writeInt (1);
	raf.seek (190);
	raf.writeInt (2);
	raf.seek (200);
	raf.writeInt (race);
	raf.seek (210);
	raf.writeInt (1);
	battleSystem.stats temp = new battleSystem.stats (1, race, 1, 0, 0, sk, 0, 0, 0, 0);
	raf.seek (220);
	raf.writeInt (temp.getHealth ());
	raf.seek (230);
	raf.writeInt (temp.getMana ());
	for (int i = 0 ; i < 11 ; i++) //equipment
	{
	    raf.seek (240 + 10 * i);
	    raf.writeInt (0);
	}
	raf.seek (260);
	raf.writeInt (1); //basic cudgel
	raf.seek (350);
	raf.writeInt (0);
	raf.close ();

	raf = new RandomAccessFile ("users/" + file + "/progress", "rw");
	for (int i = 0 ; i < 200 ; i++)
	{
	    raf.seek (i * 10);
	    raf.writeInt (0);
	}
	raf.close ();

	raf = new RandomAccessFile ("users/" + file + "/inventory/helmets", "rw");
	for (int i = 0 ; i < 100 ; i++)
	{
	    raf.seek (i * 10);
	    raf.writeInt (0);
	}
	raf.close ();
	
	raf = new RandomAccessFile ("users/" + file + "/inventory/armor", "rw");
	for (int i = 0 ; i < 100 ; i++)
	{
	    raf.seek (i * 10);
	    raf.writeInt (0);
	}
	raf.close ();

	raf = new RandomAccessFile ("users/" + file + "/inventory/boots", "rw");
	for (int i = 0 ; i < 100 ; i++)
	{
	    raf.seek (i * 10);
	    raf.writeInt (0);
	}
	raf.close ();
	
	raf = new RandomAccessFile ("users/" + file + "/inventory/tools", "rw");
	for (int i = 0 ; i < 100 ; i++)
	{
	    raf.seek (i * 10);
	    raf.writeInt (0);
	}
	raf.close ();
	
	raf = new RandomAccessFile ("users/" + file + "/inventory/skills", "rw");
	for (int i = 0 ; i < 100 ; i++)
	{
	    raf.seek (i * 10);
	    raf.writeInt (0);
	}
	raf.close ();

	raf = new RandomAccessFile ("users/" + file + "/inventory/assorted", "rw");
	for (int i = 0 ; i < 100 ; i++)
	{
	    raf.seek (i * 10);
	    raf.writeInt (0);
	}
	raf.close ();
	//end of making files

	enter (file);
    }


    public static void enter (String name) throws IOException
    {
	overworld game = new overworld (c, name);
	game.play ();
    }
} // TalesOfDemaia class

/*
progress

Important progress
2: parents
5: lealet / lealet outskirts

Unimportant progress
0: garden chet
1: mango chest
3: dresser tutorial
4: hair flower
6: whittling subquest
7: hanging clothes
8: mushroom sidequest
*/
