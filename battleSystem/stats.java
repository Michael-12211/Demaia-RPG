package battleSystem;

public class stats
{
    public int[] options;
    public double[] resistance;
    public int level;
    public int race;
    public int weapon;
    public int tool1;
    public int tool2;
    public int[] skills;
    public int head;
    public int armor;
    public int boots;
    public int accessory;
    public int health;
    public int mana;
    public int speed;
    public int attack;
    public int arcana;

    public stats (int lvl, int r, int w, int t1, int t2, int[] sk, int h, int ar, int b, int a)
    {
	options = new int [9];
	resistance = new double [200];
	for (int i = 0 ; i < resistance.length ; i++)
	{
	    resistance [i] = 1;
	}
	level = lvl;
	race = r;
	weapon = w;
	tool1 = t1;
	tool2 = t2;
	skills = sk;
	head = h;
	armor = ar;
	boots = b;
	accessory = a;

	calc ();
    }


    public void zero ()
    {
	for (int i = 0 ; i < resistance.length ; i++)
	{
	    resistance [i] = 1;
	}
	attack = 20 + level;
	health = 30 + level;
	mana = 10;
	speed = 20 + level;
	arcana = 20 + level;
    }


    public void calc ()
    {
	zero ();
	raceE ();
	weaponE ();
	toolE (tool1, 1);
	toolE (tool2, 2);
	skillE ();
	headE ();
	armorE ();
	bootsE ();
	accessoryE ();
    }


    public void raceE ()
    {
	if (race == 1) //dryad
	{
	    attack += (int) (10 + level);
	    health += (int) (30 + 3 * level);
	    mana += (int) (20 + (level / 5));
	    speed += (int) (30 + 2 * level);
	    arcana += (int) (20 + level * 2);
	}
	if (race == 2) //marapet l
	{
	    attack += (int) (20 + level * 2);
	    health += (int) (40 + 4 * level);
	    mana += (int) (5 + (level / 20));
	    speed += (int) (25 + 1.8 * level);
	    arcana += (int) (30 + level * 3);
	}
	if (race == 3) //marapet s
	{
	    attack += (int) (25 + level * 2.5);
	    health += (int) (50 + 5 * level);
	    mana += (int) (10 + (level / 10));
	    speed += (int) (10 + (level * 2) / 3);
	    arcana += (int) (10 + level);
	}
	if (race == 4) //goblin
	{
	    attack += (int) (15 + level * 1.5);
	    health += (int) (30 + 3 * level);
	    mana += (int) (20 + (level / 5));
	    speed += (int) (15 + level);
	    arcana += (int) (30 + level * 3);
	}
	if (race == 5) //kobold
	{
	    attack += (int) (20 + level * 2);
	    health += (int) (50 + 5 * level);
	    mana += (int) (15 + (level / 7));
	    speed += (int) (10 + (level * 2) / 3);
	    arcana += (int) (20 + level * 2);
	}
	if (race == 6) //wyvern
	{
	    attack += (int) (10 + level);
	    health += (int) (50 + 5 * level);
	    mana += (int) (30 + (level / 3));
	    speed += (int) (30 + level * 2);
	    arcana += (int) (15 + level * 1.5);
	}
	if (race == 7) //atlantean
	{
	}
	if (race == 8) //leon
	{
	}
	if (race == 9) //demon
	{
	}
    }


    public void weaponE ()
    {
	if (weapon == 1) //basic cudgel
	{
	    options [1] = 1;
	    attack += 10;
	}
    }


    public void toolE (int tool, int which)
    {
	if (tool == 1) //spore cap
	{
	    options [which + 2] = 7;
	}
    }


    public void skillE ()
    {
	for (int i = 0 ; i < skills.length ; i++)
	{
	    options [i + 5] = skills [i];
	}
    }


    public void headE ()
    {
	if (head == 1) //hair flower
	{
	    resistance [7] -= 0.1;
	}
    }


    public void armorE ()
    {
	if (armor == 1) //leather vestments
	{
	    resistance [2] -= 0.1;
	}
    }


    public void bootsE ()
    {
	if (boots == 1) //gardening boots
	{
	    resistance [3] -= 0.05;
	}
    }


    public void accessoryE ()
    {
    }


    public int[] getOptions ()
    {
	return options;
    }


    public int getHealth ()
    {
	return health;
    }


    public int getMana ()
    {
	return mana;
    }


    public void setRace (int r)
    {
	race = r;
	calc ();
    }


    public void setLevel (int l)
    {
	level = l;
	calc ();
    }


    public void setHead (int h)
    {
	head = h;
	calc ();
    }


    public void setArmor (int a)
    {
	armor = a;
	calc ();
    }


    public void setBoots (int b)
    {
	boots = b;
	calc ();
    }


    public void setTools (int t, int w)
    {
	if (w == 1)
	{
	    tool1 = t;
	}
	if (w == 2)
	{
	    tool2 = t;
	}
    }


    public void setSkills (int s, int w)
    {
	skills [w] = s;
	calc ();
    }
}
