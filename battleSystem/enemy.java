package battleSystem;

public class enemy extends fighter
{
    int[] options;

    public enemy (screen display, String title, int id, int position, int level)
    {
	super (display, title, id, position);
	options = new int [10];
	lvl = level;
	train ();
    }


    public void train ()
    {
	if (ID == 10) //crop squirrel
	{
	    name = "crop squirrel";
	    options [0] = 8; //bite
	    health = 35 + lvl * 4;
	    healthcap = 35 + lvl * 4;
	    arcana = 0;
	    mana = 5;
	    manacap = 5;
	    strength = 15 + lvl * 3;
	    speed = 60 + 5 * lvl;
	    resistance [2] = 1.2;
	    resistance [6] = 0.7;
	}
	if (ID == 11) //wyric
	{
	    name = "wyric";
	    options [0] = 9; //fly
	    options [1] = 2; //slash
	    options [2] = 8; //bite
	    health = 50 + lvl * 8;
	    healthcap = 50 + lvl * 8;
	    arcana = 0;
	    mana = 5;
	    manacap = 5;
	    strength = 30 + lvl * 6;
	    speed = 30 + 2 * lvl;
	    resistance [1] = 1.3;
	    resistance [4] = 0.8;
	    resistance [5] = 0.8;
	    resistance [7] = 0.6;
	}
	if (ID == 12) //treant twig
	{
	    name = "trant twig";
	    options [0] = 10; //hazerdous scratch
	    health = 60 + lvl * 5;
	    healthcap = 60 + lvl * 5;
	    arcana = 20;
	    mana = 10;
	    manacap = 10;
	    strength = 5 + lvl;
	    speed = 20 + 2 * lvl;
	    resistance [2] = 0.6;
	    resistance [3] = 1.2;
	    resistance [5] = 2.0;
	    resistance [6] = 0.7;
	    resistance [7] = 0.1;
	    effects [8] += 5; //regeneration
	}
	if (ID == 13) //woodrend
	{
	    name = "woodrend";
	    options [0] = 12; //rend
	    options [1] = 2; //slash
	    options [2] = 6; //crushing blow
	    health = 65 + lvl * 10;
	    healthcap = 65 + lvl * 10;
	    arcana = 30 + lvl * 5;
	    mana = 5;
	    manacap = 5;
	    strength = 25 + lvl * 5;
	    speed = 15 + 1 * lvl;
	    resistance [2] = 1.1;
	    resistance [3] = 0.6;
	    resistance [4] = 0.8;
	    resistance [7] = 1.1;
	}
	if (ID == 14) //bees
	{
	    name = "bees";
	    options [0] = 7; //contaminate
	    options [1] = 9; //fly
	    options [2] = 14; //sting
	    health = 30 + lvl * 4;
	    healthcap = 30 + lvl * 4;
	    arcana = 0;
	    mana = 5;
	    manacap = 5;
	    strength = 10 + lvl * 2;
	    speed = 100 + 9 * lvl;
	    resistance [1] = 0.1;
	    resistance [2] = 0.1;
	    resistance [3] = 0.6;
	    resistance [4] = 1.5;
	    resistance [7] = 0.5;
	}
    }


    public int choice ()
    {
	int[] actWeight = new int [options.length];
	int totWeight = 0;
	for (int i = 0 ; i < options.length ; i++)
	{
	    int weight = need (options [i]);
	    actWeight [i] = weight;
	    totWeight += weight;
	}

	if (totWeight == 0)
	{
	    return 0;
	}
	else
	{
	    int choose = (int) (Math.random () * totWeight);
	    for (int i = 0 ; i < options.length ; i++)
	    {
		choose -= actWeight [i];
		if (choose < 0)
		{
		    return options [i];
		}
	    }
	    return 0;
	}
    }


    public int need (int what)
    {
	int want = 5;
	if (what == 0) //empty
	{
	    want = 0;
	}
	if (what == 1) //strike
	{
	}
	if (what == 2) //slash
	{
	}
	if (what == 3) //block
	{
	}
	if (what == 4) //fient
	{
	}
	if (what == 5) //shiv
	{
	}
	if (what == 6) //crushing blow
	{
	    if (opponent.effects [4] == 0)
	    {
		want = 0;
	    }
	    else if (opponent.effects [4] > 1)
	    {
		want += 5;
	    }
	    else
	    {
		want += 2;
	    }
	}
	if (what == 7) //contaminate
	{
	}
	if (what == 8) //bite
	{
	    int rationale = effects [5] + effects [6] - opponent.getEffects () [5] + opponent.getEffects () [6];
	    if (rationale > 0)
	    {
		want += rationale;
	    }
	    else if (rationale < 0)
	    {
		want = 1;
	    }
	}
	if (what == 9) //fly
	{
	    if (effects [7] > 0)
	    {
		want -= 4;
	    }
	}
	if (what == 10) //hazerdous scratch
	{
	}
	if (what == 11) //lob
	{
	}
	if (what == 12) //rend
	{
	    if (opponent.effects [1] > 0)
	    {
		want -= 4;
	    }
	    else if (opponent.effects [4] > 0)
	    {
		want -= 2;
	    }
	    else if (opponent.health / opponent.healthcap > 0.5)
	    {
		want += 5;
	    }
	}

	if (want < 0)
	    want = 0;
	return want;
    }


    public int getLevel ()
    {
	return lvl;
    }
}


