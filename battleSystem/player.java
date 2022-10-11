package battleSystem;

public class player extends fighter
{
    int[] options;
    int weap;
    int race;
    int head;
    int armor;
    int boots;
    int accessory;
    int level;

    public player (screen display, String title, int id, int position, stats st, int h, int m)
    {
	super (display, title, id, position);
	options = new int [9];
	for (int i = 0 ; i < options.length ; i++)
	{
	    options [i] = 0;
	}
	gearUp (st);
	health = h;
	mana = m;
	level = st.level;
    }


    public int choice ()
    {
	dis.showOptions (options, moveNames);
	int select = dis.charGet ();
	return options [select];
    }


    public void raceE (int race)
    {
	if (race == 1) //dryad
	{
	    effects [1] = -1;
	}
    }


    public void gearUp (stats st)
    {
	options = st.options;
	resistance = st.resistance;
	strength = st.attack;
	mana = st.mana;
	manacap = st.mana;
	health = st.health;
	healthcap = st.health;
	speed = st.speed;
	arcana = st.arcana;
	weap = st.weapon;
	race = st.race;
	head = st.head;
	armor = st.armor;
	boots = st.boots;
	accessory = st.accessory;
    }


    public int getWeap ()
    {
	return weap;
    }


    public int getLevel ()
    {
	return level;
    }
}
