import java.awt.*;
import java.io.*;
import java.util.*;
import hsa.Console;

/*
inventory codes
0: weapons
1: helmets
2: armor
3: boots
4: accessories
5: tools
6: assorted
7: skills
*/

/*
collection IDs

helmet codes
1: hair flower

boot codes
1: gardening boots

tool codes
1: spore cap

assorted codes
1: mango
*/

class inventory
{
    int[] [] own;

    String[] headNames;
    String[] armorNames;
    String[] weaponNames;
    String[] toolNames;
    String[] accessoryNames;
    String[] bootNames;
    String[] skillNames;
    String[] assortedNames;

    static Console c;

    public inventory (Console con)
    {
	c = con;
	own = new int [8] [100];
	initiate ();
    }


    public void equip (userData user)
    {
	while (true)
	{
	    c.clear ();
	    battleSystem.stats st = user.getStats ();
	    c.println ("0: head: " + headNames [st.head]);
	    c.println ("1: armor: " + armorNames [st.armor]);
	    c.println ("2: weapon: " + weaponNames [st.weapon]);
	    c.println ("3: acessory: " + accessoryNames [st.accessory]);
	    c.println ("4: boots: " + bootNames [st.boots]);
	    c.println ("5: tool 1: " + toolNames [st.tool1]);
	    c.println ("6: tool 2: " + toolNames [st.tool2]);
	    int[] sk = st.skills;
	    c.println ("7: skill 1: " + skillNames [sk [0]]);
	    c.println ("8: skill 2: " + skillNames [sk [1]]);
	    c.println ("9: skill 3: " + skillNames [sk [2]]);
	    c.println ("-: skill 4: " + skillNames [sk [3]]);

	    c.println ();
	    c.println ("Exp: " + user.getExp ());
	    c.println ("to next level: " + (int) (10 * Math.pow (st.level, 1.5)));

	    int choice = charGet ();

	    if (choice == 0)
	    {
		helmEquip (st);
	    }
	    if (choice == 1)
	    {
		armorEquip (st);
	    }
	    if (choice == 4)
	    {
		bootEquip (st);
	    }
	    if (choice == 5 || choice == 6)
	    {
		toolEquip (st, choice);
	    }
	    if (choice == 7 || choice == 8 || choice == 9 || choice == -1)
	    {
		skillEquip (st, choice);
	    }
	    if (choice == -2)
	    {
		break;
	    }
	}
    }


    public void helmEquip (battleSystem.stats st)
    {
	while (true)
	{
	    c.clear ();
	    for (int i = 0 ; i < headNames.length ; i++)
	    {
		if (own [1] [i] != 0 || i == 0)
		{
		    c.println (i + ": " + headNames [i]);
		}
	    }
	    int choice = c.readInt ();
	    if (own [1] [choice] != 0 || choice == 0)
	    {
		st.setHead (choice);
		break;
	    }
	}
    }


    public void armorEquip (battleSystem.stats st)
    {
	while (true)
	{
	    c.clear ();
	    for (int i = 0 ; i < armorNames.length ; i++)
	    {
		if (own [2] [i] != 0 || i == 0)
		{
		    c.println (i + ": " + armorNames [i]);
		}
	    }
	    int choice = c.readInt ();
	    if (own [2] [choice] != 0 || choice == 0)
	    {
		st.setArmor (choice);
		break;
	    }
	}
    }


    public void bootEquip (battleSystem.stats st)
    {
	while (true)
	{
	    c.clear ();
	    for (int i = 0 ; i < bootNames.length ; i++)
	    {
		if (own [3] [i] != 0 || i == 0)
		{
		    c.println (i + ": " + bootNames [i]);
		}
	    }
	    int choice = c.readInt ();
	    if (own [3] [choice] != 0 || choice == 0)
	    {
		st.setBoots (choice);
		break;
	    }
	}
    }


    public void toolEquip (battleSystem.stats st, int which)
    {
	which = which - 4;
	while (true)
	{
	    c.clear ();
	    for (int i = 0 ; i < toolNames.length ; i++)
	    {
		if (own [5] [i] != 0 || i == 0)
		{
		    c.println (i + ": " + toolNames [i]);
		}
	    }
	    int choice = c.readInt ();
	    if (own [5] [choice] != 0 || choice == 0)
	    {
		st.setTools (choice, which);
		break;
	    }
	}
    }


    public void skillEquip (battleSystem.stats st, int which)
    {
	which = which - 7;
	if (which == -8)
	{
	    which = 3;
	}
	while (true)
	{
	    c.clear ();
	    for (int i = 0 ; i < skillNames.length ; i++)
	    {
		if (own [7] [i] != 0 || i == 0)
		{
		    c.println (i + ": " + skillNames [i]);
		}
	    }
	    int choice = c.readInt ();
	    if (own [7] [choice] != 0 || choice == 0)
	    {
		st.setSkills (choice, which);
		break;
	    }
	}
    }


    public void initiate ()
    {
	headNames = new String [100];
	headNames [0] = "none";
	headNames [1] = "hair flower";

	armorNames = new String [100];
	armorNames [0] = "none";
	armorNames [1] = "leather overshirt";

	weaponNames = new String [100];
	weaponNames [0] = "none";
	weaponNames [1] = "basic cudgel";

	toolNames = new String [100];
	toolNames [0] = "none";
	toolNames [1] = "spore cap";

	accessoryNames = new String [100];
	accessoryNames [0] = "none";

	bootNames = new String [100];
	bootNames [0] = "none";
	bootNames [1] = "gardening boots";

	skillNames = new String [100];
	skillNames [0] = "none";
	skillNames [11] = "lob";
	skillNames [13] = "disarm";

	assortedNames = new String [100];
	assortedNames [0] = "none";
	assortedNames [1] = "Mango";
    }


    public int charGet ()
    {
	char select;
	while (true)
	{
	    select = c.getChar ();
	    if (select == '0' || select == '1' || select == '2' || select == '3' || select == '4' || select == '5' || select == '6' || select == '7' || select == '8' || select == '9')
	    {
		return (((int) (select)) - 48);
	    }
	    else if (select == '-')
	    {
		return -1;
	    }
	    else
	    {
		return -2;
	    }
	}
    }
}


