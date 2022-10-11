class userData
{
    battleSystem.stats st;
    int currentHealth;
    int currentMana;
    int race;
    int exp;

    String name;

    public userData ()
    {
	name = "test";
	race = 2;
	int[] skills = {0, 0, 0, 0};
	st = new battleSystem.stats (1, race, 1, 0, 0, skills, 0, 0, 0, 0);
	currentHealth = st.getHealth ();
	currentMana = st.getMana ();
	exp = 0;
    }


    public void heal ()
    {
	currentHealth = st.health;
	currentMana = st.mana;
    }


    public battleSystem.stats getStats ()
    {
	return st;
    }


    public void setStats (battleSystem.stats nest)
    {
	st = nest;
    }


    public void setName (String neam)
    {
	name = neam;
    }


    public void setRace (int race)
    {
	st.setRace (race);
    }


    public int getRace ()
    {
	return st.race;
    }


    public void setLevel (int level)
    {
	st.setLevel (level);
    }


    public int getLevel ()
    {
	return st.level;
    }


    public int getHelmet ()
    {
	return st.head;
    }


    public int getArmor ()
    {
	return st.armor;
    }


    public int getWeapon ()
    {
	return st.weapon;
    }


    public int getBoots ()
    {
	return st.boots;
    }


    public int getAccessory ()
    {
	return st.accessory;
    }


    public int getTool1 ()
    {
	return st.tool1;
    }


    public int getTool2 ()
    {
	return st.tool2;
    }


    public int[] getSkills ()
    {
	return st.skills;
    }


    public void setHealth (int health)
    {
	currentHealth = health;
    }


    public int getHealth ()
    {
	return currentHealth;
    }


    public int getMaxHealth ()
    {
	return st.health;
    }


    public void setMana (int mana)
    {
	currentMana = mana;
    }


    public int getMana ()
    {
	return currentMana;
    }


    public int getMaxMana ()
    {
	return st.mana;
    }


    public String getName ()
    {
	return name;
    }


    public int getExp ()
    {
	return exp;
    }


    public void setExp (int e)
    {
	exp = e;
    }


    public void gainExp (int e)
    {
	exp += e;
	if (exp >= 10 * (int)(Math.pow (st.level, 1.5)))
	{
	    exp -= (int)(10 * Math.pow (st.level, 1.5));
	    st.setLevel (st.level + 1);
	}
    }
}
