package battleSystem;

public class fighter
{
    screen dis;
    fighter opponent;

    String name = "";
    int pos;
    int ID;
    int lvl;
    int health;
    int healthcap;
    int arcana;
    int mana;
    int manacap;
    int strength;
    int speed;
    int action;
    double[] resistance;
    /*
    damage types
    1: Pierce
    2: Bludgeon
    3: Slash
    4: Shock
    5: Burn
    6: Blast
    7: poison
    */
    int[] effects;
    /*
    effect codes
    1: Vulnerability
    2: Burning
    3: Damage reduction
    4: Bleed
    5: Nausia
    6: Poison
    7: airborne
    8: regeneration
    9: thorns
    10: venom
    */
    int[] priorities;
    int[] manaCost;
    String[] moveNames;
    /*
    move codes
    0: pass
    1: strike
    2: slash
    3: block
    4: fient
    5: shiv
    6: crushing blow
    7: contaminate
    8: bite
    9: fly
    10: hazerdous scratch
    11: lob
    12: rend
    13: disarm
    14: sting
    */

    public fighter (screen display, String title, int id, int position)
    {
	initialize ();
	dis = display;
	opponent = null;
	ID = id;
	pos = position;
	lvl = 0;

	resistance = new double [150];
	for (int i = 0 ; i < resistance.length ; i++)
	{
	    resistance [i] = 1.0;
	}
	effects = new int [100];
	for (int i = 0 ; i < effects.length ; i++)
	{
	    effects [i] = 0;
	}
	name = title;
	health = 50;
	healthcap = 50;
	arcana = 30;
	mana = 15;
	manacap = 15;
	strength = 30;
	speed = 30;
    }


    public void setOpponent (fighter villain)
    {
	opponent = villain;
    }


    public void turn ()
    {
	action = choice ();
    }


    public void enact ()
    {
	if (health >= 0)
	{
	    act (action);
	}
    }


    public int choice ()
    {
	return (int) (Math.random () * 8) + 1;
    }


    public void act (int what)
    {
	boolean work = true;

	if (manaCost [what] <= mana)
	{
	    mana -= manaCost [what];
	}
	else
	{
	    dis.sPrint (name + " didn't have enough mana to act!");
	    work = false;
	}


	if (work)
	{
	    if (what == 0) //pass
	    {
		dis.sPrint (name + " passes their turn");
	    }


	    if (what == 1) //strike
	    {
		dis.sPrint (name + " strikes " + opponent.getName ());
		deal (40, 2, 1, false);
		dis.animate (1, opponent);
	    }


	    if (what == 2) //slash
	    {
		dis.sPrint (name + " slashes " + opponent.getName ());
		deal (25, 3, 1, false);
		opponent.infect (4, 2);
	    }


	    if (what == 3) //block
	    {
		dis.sPrint (name + " blocks");
		infect (3, 2);
	    }


	    if (what == 4) //feint
	    {
		dis.sPrint (name + " feints " + opponent.getName ());
		opponent.infect (1, opponent.getEffects () [3]);
	    }


	    if (what == 5) //shiv
	    {
		dis.sPrint (name + " shivs " + opponent.getName ());
		deal (33, 1, 1, false);
		dis.animate (4, opponent);
	    }


	    if (what == 6) //crushing blow
	    {
		dis.sPrint (name + " takes a crushing blow at " + opponent.getName ());
		if (opponent.getEffects () [1] > 0)
		{
		    dis.sPrint ("It hits!");
		    deal (70, 1, 1, false);
		    opponent.resistance [2] += 0.2;
		}
		else
		{
		    dis.sPrint ("It fails!");
		}
	    }


	    if (what == 7) //contaminate
	    {
		dis.sPrint (name + " spreads poison on " + opponent.getName ());
		opponent.infect (6, 4);
		dis.animate (3, opponent);
	    }


	    if (what == 8) //bite
	    {
		dis.sPrint (name + " bites " + opponent.getName ());
		int temp5 = effects [5];
		int temp6 = effects [6];
		int[] opp = opponent.getEffects ();
		if (opp [5] != 0)
		    infect (5, opp [5]);
		if (opp [6] != 0)
		    infect (6, opp [6]);
		if (temp5 != 0)
		    opponent.infect (5, temp5);
		if (temp6 != 0)
		    opponent.infect (6, temp6);
		deal (30, 1, 1, false);
		dis.animate (2, opponent);
	    }


	    if (what == 9) //fly
	    {
		dis.sPrint (name + " takes flight");
		infect (7, 3);
	    }


	    if (what == 10) //hazerdous scratch
	    {
		dis.sPrint (name + " scratches " + opponent.getName ());
		deal (25, 3, 1, false);
		opponent.infect (6, 1);
		dis.animate (5, opponent);
	    }


	    if (what == 11) //lob
	    {
		dis.sPrint (name + " lobs a projectile at " + opponent.getName ());
		deal (45, 2, 2, true);
		dis.animate (1, opponent);
	    }


	    if (what == 12) //rend
	    {
		dis.sPrint (name + " rends " + opponent.getName ());
		opponent.infect (4, 3);
		opponent.infect (1, 1);
		dis.animate (6, opponent);
	    }


	    if (what == 13) //disarm
	    {
		dis.sPrint (name + " gets a safe grip on " + opponent.getName ());
		infect (3, 1);
		if (opponent.effects [9] > 0)
		{
		    opponent.effects [9] = 0;
		    dis.sPrint (opponent.getName () + " lost their thorns!");
		}
		dis.animate (7, opponent);
	    }
	    
	    if (what == 14) //sting
	    {
		dis.sPrint (name + " stings " + opponent.getName ());
		opponent.infect (10,2);
		deal (20, 7, 1, false);
		dis.animate (4, opponent);
	    }
	}
    }


    public void elapse ()
    {
	restore (((manacap - mana) / 10) + 1);

	if (effects [2] > 0) //burning
	{
	    dis.sPrint (name + " lost " + ((healthcap / 25) * effects [2]) + " health from burning");
	    health -= (healthcap / 25) * effects [2];
	    effects [2]--;
	}


	if (effects [4] > 0) //bleeding
	{
	    dis.sPrint (name + " lost " + (health / 10) + " health from bleeding");
	    health -= health / 10;
	    effects [4]--;
	}


	if (effects [5] > 0) //nausia
	{
	    effects [5]--;
	}


	if (effects [7] > 0) //airborne
	{
	    effects [7]--;
	}


	if (effects [8] > 0) //regenration
	{
	    dis.sPrint (name + " regenerated " + (healthcap / 10) + " health");
	    heal (healthcap / 10);
	    effects [8]--;
	}


	if (effects [10] > 0) //venom
	{
	    int amount = (int) (healthcap * 0.01 * effects [10]);
	    dis.sPrint (name + " loses " + amount + " health from venom");
	    health -= amount;
	    effects [10] = effects [10] / 2;
	}
    }


    public void deal (int damage, int type, int use, boolean range)
    {
	if (use == 1)
	{
	    damage = (int) (damage * strength * 0.01);
	}


	else
	{
	    damage = (int) (damage * arcana * 0.01);
	}


	if (effects [5] > 0) //nausia
	{
	    damage = (int) (damage * 0.8);
	}


	if (effects [6] > 0) //poison
	{
	    take ((int) (damage * 0.3), 7, range);
	    effects [6]--;
	}

	if (effects [10] > 0) //venom
	{
	    dis.sPrint (name + "'s venom worsened");
	    effects [10] += effects [10];
	}


	opponent.take (damage, type, range);
    }


    public void take (int damage, int type, boolean range)
    {
	damage = (int) (damage * resistance [type]);
	if (effects [1] > 0) //vulnerability
	{
	    damage = (int) (damage * 1.5);
	    effects [1]--;
	}


	if (effects [3] > 0) //resistance
	{
	    damage = (int) (damage * 0.5);
	    effects [3]--;
	}


	if (effects [7] > 0) //airborne
	{
	    if (!range)
	    {
		damage = 0;
	    }
	}

	if (effects [9] > 0) //thorns
	{
	    if (!range)
	    {
		opponent.take (damage / 2, 1, false);
		effects [9]--;
	    }
	}


	dis.sPrint (name + " took " + damage + " damage");
	health -= damage;
    }


    public void infect (int what, int last)
    {
	boolean applied = true;
	if (effects [what] < 0)
	{
	    last += effects [what];
	    if (last <= 0)
	    {
		applied = false;
	    }
	}


	if (applied)
	{
	    if (what == 1) //vulnerable
	    {
		if (effects [3] >= 0) //removing reistant
		{
		    effects [3] = 0;
		}
		if (applied)
		{
		    dis.sPrint (name + " is vulnerable for " + last + " attacks");
		}
	    }
	    if (what == 2) //burning
	    {
		if (applied)
		{
		    dis.sPrint (name + " is burning for " + last + " turns");
		}
	    }
	    if (what == 3) //resistant
	    {
		if (effects [3] >= 0) //removing vulnerable
		{
		    effects [1] = 0;
		}
		if (applied)
		{
		    dis.sPrint (name + " is resistant for " + last + " attacks");
		}
	    }
	    if (what == 4) //bleeding
	    {
		if (applied)
		{
		    dis.sPrint (name + " is bleeding for " + last + " turns");
		}
	    }
	    if (what == 5) //nausia
	    {
		if (applied)
		{
		    dis.sPrint (name + " is nasuious for " + last + " turns");
		}
	    }
	    if (what == 6) //poison
	    {
		if (applied)
		{
		    dis.sPrint (name + " is poisoned for " + last + " attacks");
		}
	    }
	    if (what == 7) //flight
	    {
		if (applied)
		{
		    dis.sPrint (name + " is airborn for " + last + " turns");
		}
	    }
	    if (what == 8) //regeneration
	    {
		if (applied)
		{
		    dis.sPrint (name + " is regenrating for " + last + " turns");
		}
	    }
	}


	if (applied)
	{
	    effects [what] += last;
	}
    }


    public void heal (int amount)
    {
	if (health + amount > healthcap)
	{
	    health = healthcap;
	}


	else
	{
	    health += amount;
	}
    }


    public void restore (int amount)
    {
	if (mana + amount > manacap)
	{
	    mana = manacap;
	}


	else
	{
	    mana += amount;
	}
    }


    public int getOrder ()
    {
	return speed * priorities [action];
    }


    public int getID ()
    {
	return ID;
    }


    public int getPos ()
    {
	return pos;
    }


    public String getName ()
    {
	return name;
    }


    public int getHealth ()
    {
	return health;
    }


    public int getHealthCap ()
    {
	return healthcap;
    }


    public int getMana ()
    {
	return mana;
    }


    public int getManaCap ()
    {
	return manacap;
    }


    public double[] getRes ()
    {
	return resistance;
    }


    public int[] getEffects ()
    {
	return effects;
    }


    public void initialize ()
    {
	priorities = new int [200];
	priorities [0] = 5; //pass
	priorities [1] = 3; //strike
	priorities [2] = 3; //slash
	priorities [3] = 4; //block
	priorities [4] = 1; //feint
	priorities [5] = 5; //shiv
	priorities [6] = 2; //crushing blow
	priorities [7] = 4; //contaminate
	priorities [8] = 4; //bite
	priorities [9] = 1; //fly
	priorities [10] = 4; //hazerdous scratch
	priorities [11] = 3; //lob
	priorities [12] = 2; //rend
	priorities [13] = 4; //disarm
	priorities [14] = 4; //sting

	manaCost = new int [200];
	manaCost [0] = 0; //pass
	manaCost [1] = 0; //stike
	manaCost [2] = 0; //slash
	manaCost [3] = 0; //block
	manaCost [4] = 0; //feint
	manaCost [5] = 0; //shiv
	manaCost [6] = 0; //crushing blow
	manaCost [7] = 0; //contaminate
	manaCost [8] = 0; //bite
	manaCost [9] = 0; //fly
	manaCost [10] = 0; //hazerdous scratch
	manaCost [11] = 5; //lob
	manaCost [12] = 0; //rend
	manaCost [13] = 0; //disarm
	manaCost [14] = 0; //sting

	moveNames = new String [200];
	moveNames [0] = "pass";
	moveNames [1] = "strike";
	moveNames [2] = "slash";
	moveNames [3] = "block";
	moveNames [4] = "fient";
	moveNames [5] = "shiv";
	moveNames [6] = "crushing blow";
	moveNames [7] = "contaminate";
	moveNames [8] = "bite";
	moveNames [9] = "fly";
	moveNames [10] = "hazerdous scratch";
	moveNames [11] = "lob";
	moveNames [12] = "rend";
	moveNames [13] = "disarm";
	moveNames [14] = "sting";
    }


    //these methods exist to ensure proper call functionality
    public int getWeap ()
    {
	return 0;
    }


    public int getLevel ()
    {
	return 0;
    }
}
