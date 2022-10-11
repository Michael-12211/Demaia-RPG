import java.awt.*;
import java.io.*;
import java.util.*;
import hsa.Console;

class overworld
{
    display dis;
    Console c;
    userData user;
    talk textBox;

    static String[] [] [] map; //x, y, z
    static int[] [] elements = new int [14] [14];
    static int[] [] characters = new int [14] [14];
    static int[] [] base = new int [14] [14];

    static int mx;
    static int my;
    static int mz;
    static int px;
    static int py;
    static int pz;

    static int cx;
    static int cy;
    static int face;

    static int safe;

    static String background;
    static String saver;
    static String area;

    static int[] [] encounters;
    static int[] [] dialogue;
    static int[] [] wall;
    static int[] [] connections;
    static String[] [] connectText;
    static boolean[] crossable;

    static inventory collect;

    public overworld (String back, String name, Console con) throws IOException
    {
	c = con;

	basics ();

	background = back;

	dis = new display (c);

	areaLoad (name);

	saver = "-";
    }


    public overworld (Console con, String whom) throws IOException
    {
	c = con;

	saver = whom;

	basics ();
	dis = new display (c);

	RandomAccessFile raf = new RandomAccessFile ("users/" + whom + "/essentials", "rw");
	raf.seek (0);
	user.setName (raf.readUTF ());
	raf.seek (50);
	background = raf.readUTF ();
	raf.seek (100);
	areaLoad (raf.readUTF ());
	raf.seek (150);
	px = raf.readInt ();
	raf.seek (160);
	py = raf.readInt ();
	raf.seek (170);
	pz = raf.readInt ();
	raf.seek (180);
	cx = raf.readInt ();
	raf.seek (190);
	cy = raf.readInt ();
	raf.seek (200);
	user.setRace (raf.readInt ());
	raf.seek (210);
	user.setLevel (raf.readInt ());
	raf.seek (220);
	user.setHealth (raf.readInt ());
	raf.seek (230);
	user.setMana (raf.readInt ());

	//reading stats
	/*
	raf.seek (200);
	user.setRace (raf.readInt ());
	raf.seek (210);
	user.setLevel (raf.readInt ());
	*/
	raf.seek (200);
	int race = raf.readInt ();
	raf.seek (210);
	int level = raf.readInt ();
	raf.seek (240);
	int helmet = raf.readInt ();
	raf.seek (250);
	int armor = raf.readInt ();
	raf.seek (260);
	int weapon = raf.readInt ();
	raf.seek (270);
	int boots = raf.readInt ();
	raf.seek (280);
	int accessory = raf.readInt ();
	raf.seek (290);
	int tool1 = raf.readInt ();
	raf.seek (300);
	int tool2 = raf.readInt ();
	raf.seek (310);
	int skill1 = raf.readInt ();
	raf.seek (320);
	int skill2 = raf.readInt ();
	raf.seek (330);
	int skill3 = raf.readInt ();
	raf.seek (340);
	int skill4 = raf.readInt ();
	raf.seek (350);
	int exp = raf.readInt ();
	user.setExp (exp);

	int[] skills = {skill1, skill2, skill3, skill4};
	battleSystem.stats replace = new battleSystem.stats (level, race, weapon, tool1, tool2, skills, helmet, armor, boots, accessory);
	user.setStats (replace);


	raf.close ();

	raf = new RandomAccessFile ("users/" + whom + "/inventory/helmets", "rw");
	for (int i = 0 ; i < 100 ; i++)
	{
	    raf.seek (i * 10);
	    collect.own [1] [i] = raf.readInt ();
	}
	raf.close ();
	raf = new RandomAccessFile ("users/" + whom + "/inventory/armor", "rw");
	for (int i = 0 ; i < 100 ; i++)
	{
	    raf.seek (i * 10);
	    collect.own [2] [i] = raf.readInt ();
	}
	raf.close ();
	raf = new RandomAccessFile ("users/" + whom + "/inventory/boots", "rw");
	for (int i = 0 ; i < 100 ; i++)
	{
	    raf.seek (i * 10);
	    collect.own [3] [i] = raf.readInt ();
	}
	raf.close ();
	raf = new RandomAccessFile ("users/" + whom + "/inventory/tools", "rw");
	for (int i = 0 ; i < 100 ; i++)
	{
	    raf.seek (i * 10);
	    collect.own [5] [i] = raf.readInt ();
	}
	raf.close ();
	raf = new RandomAccessFile ("users/" + whom + "/inventory/assorted", "rw");
	for (int i = 0 ; i < 100 ; i++)
	{
	    raf.seek (i * 10);
	    collect.own [6] [i] = raf.readInt ();
	}
	raf.close ();
	raf = new RandomAccessFile ("users/" + whom + "/inventory/skills", "rw");
	for (int i = 0 ; i < 100 ; i++)
	{
	    raf.seek (i * 10);
	    collect.own [7] [i] = raf.readInt ();
	}
	raf.close ();
    }


    public void basics ()
    {
	encounters = new int [200] [4];
	dialogue = new int [200] [6];
	wall = new int [200] [6];
	crossable = new boolean [200];
	connections = new int [50] [7];
	connectText = new String [50] [2];

	mx = 0;
	my = 0;
	mz = 0;
	px = 0;
	py = 0;
	pz = 0;
	cx = 6;
	cy = 6;

	user = new userData ();

	textBox = new talk (c);

	collect = new inventory (c);

	safe = 0;
    }


    public void areaLoad (String name) throws IOException
    {
	area = name;
	BufferedReader sr = new BufferedReader (new FileReader ("maps/" + background + "/maps/" + name + "/map.txt"));
	mx = Integer.parseInt (sr.readLine ());
	my = Integer.parseInt (sr.readLine ());
	mz = Integer.parseInt (sr.readLine ());
	String line = sr.readLine ();
	String[] div = line.split (" ");
	//px = Integer.parseInt (div [0]);
	//py = Integer.parseInt (div [1]);
	//pz = Integer.parseInt (div [2]);
	map = new String [mx] [my] [mz];
	for (int z = 0 ; z < mz ; z++)
	{
	    if (z != 0)
	    {
		sr.readLine ();
	    }
	    for (int y = 0 ; y < my ; y++)
	    {
		line = sr.readLine ();
		div = line.split (" - ");
		for (int x = 0 ; x < mx ; x++)
		{
		    map [x] [y] [z] = div [x];
		}
	    }
	}
	sr.close ();

	//mapLoad ();

	interactionLoad (name);
    }


    public void interactionLoad (String name) throws IOException
    {
	for (int i = 0 ; i < encounters.length ; i++)
	{
	    for (int b = 0 ; b < encounters [b].length ; b++)
	    {
		encounters [i] [b] = 0;
	    }
	}
	for (int i = 0 ; i < dialogue.length ; i++)
	{
	    for (int b = 0 ; b < dialogue [b].length ; b++)
	    {
		dialogue [i] [b] = 0;
	    }
	}
	for (int i = 0 ; i < crossable.length ; i++)
	{
	    crossable [i] = false;
	}
	BufferedReader sr = new BufferedReader (new FileReader ("maps/" + background + "/maps/" + name + "/interactions.txt"));
	int quan = Integer.parseInt (sr.readLine ());
	int enc = 0;
	int dia = 0;
	int wa = 0;
	int con = 0;
	for (int i = 0 ; i < quan ; i++)
	{
	    String line = sr.readLine ();
	    String[] div = line.split (" ");
	    if (div [0].equals ("encounter"))
	    {
		/*
		encouner codes
		0: tile
		1: chance
		2: enemy
		3: level
		*/
		int tile = Integer.parseInt (div [1]);
		crossable [tile] = true;
		encounters [enc] [0] = tile;
		encounters [enc] [1] = Integer.parseInt (div [2]);
		encounters [enc] [2] = Integer.parseInt (div [3]);
		encounters [enc] [3] = Integer.parseInt (div [4]);
		enc++;
	    }
	    if (div [0].equals ("cross"))
	    {
		crossable [Integer.parseInt (div [1])] = true;
	    }
	    if (div [0].equals ("dialogue"))
	    {
		/*
		dialogue codes
		0: ID
		1: tile x
		2: tile y
		3: tile z
		4: x
		5: y
		*/
		dialogue [dia] [0] = Integer.parseInt (div [1]);
		dialogue [dia] [1] = Integer.parseInt (div [2]);
		dialogue [dia] [2] = Integer.parseInt (div [3]);
		dialogue [dia] [3] = Integer.parseInt (div [4]);
		dialogue [dia] [4] = Integer.parseInt (div [5]);
		dialogue [dia] [5] = Integer.parseInt (div [6]);
		dia++;
	    }
	    if (div [0].equals ("wall"))
	    {
		/*
		dialogue codes
		0: ID
		1: tile x
		2: tile y
		3: tile z
		4: horizontal / vertical
		5: line
		*/
		wall [dia] [0] = Integer.parseInt (div [1]);
		wall [dia] [1] = Integer.parseInt (div [2]);
		wall [dia] [2] = Integer.parseInt (div [3]);
		wall [dia] [3] = Integer.parseInt (div [4]);
		wall [dia] [4] = Integer.parseInt (div [5]);
		wall [dia] [5] = Integer.parseInt (div [6]);
		wa++;
	    }
	    if (div [0].equals ("connect"))
	    {
		/*
		connect codes
		1: map type
		2: map name
		3: entrance x
		4: entrance y
		5: entrance z
		6: exit x
		7: exit y
		8: exit z
		9: direction
		*/
		connectText [con] [0] = (div [1]);
		connectText [con] [1] = (div [2]);
		connections [con] [0] = Integer.parseInt (div [3]);
		connections [con] [1] = Integer.parseInt (div [4]);
		connections [con] [2] = Integer.parseInt (div [5]);
		connections [con] [3] = Integer.parseInt (div [6]);
		connections [con] [4] = Integer.parseInt (div [7]);
		connections [con] [5] = Integer.parseInt (div [8]);
		connections [con] [6] = Integer.parseInt (div [9]);
		con++;
	    }
	}
	sr.close ();
    }


    public void drawMap ()
    {
	dis.drawMap (base, elements, characters);
	for (int i = 0 ; i < dialogue.length ; i++)
	{
	    if (dialogue [i] [1] == px && dialogue [i] [2] == py && dialogue [i] [3] == pz && dialogue [i] [0] != 0)
	    {
		dis.sparkle (dialogue [i] [4], dialogue [i] [5]);
	    }
	}
	dis.miniMap (mx, my, mz, px, py, pz);
    }


    public void play () throws IOException
    {
	mapLoad ();
	int choice = 0;
	drawMap ();
	while (user.getHealth () > 0)
	{
	    dis.drawPlayer (1, cx, cy);

	    choice = c.getChar ();
	    if (choice == 'w')
	    {
		face = 1;
		if (cy != 0 && traverse (elements [cx] [cy - 1], cx, cy - 1))
		{
		    dis.drawTile (cx, cy, base, elements, characters);
		    cy--;
		    enter (base [cx] [cy]);
		    continue;
		}
		else if (cy == 0)
		{
		    connect ();
		}
	    }
	    if (choice == 'a')
	    {
		face = 2;
		if (cx != 0 && traverse (elements [cx - 1] [cy], cx - 1, cy))
		{
		    dis.drawTile (cx, cy, base, elements, characters);
		    cx--;
		    enter (base [cx] [cy]);
		    continue;
		}
		else if (cx == 0)
		{
		    connect ();
		}
	    }
	    if (choice == 's')
	    {
		face = 3;
		if (cy != 13 && traverse (elements [cx] [cy + 1], cx, cy + 1))
		{
		    dis.drawTile (cx, cy, base, elements, characters);
		    cy++;
		    enter (base [cx] [cy]);
		    continue;
		}
		else if (cy == 13)
		{
		    connect ();
		}
	    }
	    if (choice == 'd')
	    {
		face = 4;
		if (cx != 13 && traverse (elements [cx + 1] [cy], cx + 1, cy))
		{
		    dis.drawTile (cx, cy, base, elements, characters);
		    cx++;
		    enter (base [cx] [cy]);
		    continue;
		}
		else if (cx == 13)
		{
		    connect ();
		}
	    }
	    if (choice == 'w' && cy == 0 && py != 0)
	    {
		py--;
		mapLoad ();
		drawMap ();
		cy = 13;
	    }
	    if (choice == 'a' && cx == 0 && px != 0)
	    {
		px--;
		mapLoad ();
		drawMap ();
		cx = 13;
	    }
	    if (choice == 's' && cy == 13 && py != my - 1)
	    {
		py++;
		mapLoad ();
		drawMap ();
		cy = 0;
	    }
	    if (choice == 'd' && cx == 13 && px != mx - 1)
	    {
		px++;
		mapLoad ();
		drawMap ();
		cx = 0;
	    }
	    if (choice == 'f')
	    {
		if (face == 1 && cy != 0)
		{
		    interact (cx, cy - 1);
		}
		if (face == 2 && cx != 0)
		{
		    interact (cx - 1, cy);
		}
		if (face == 3 && cy != 13)
		{
		    interact (cx, cy + 1);
		}
		if (face == 4 && cx != 13)
		{
		    interact (cx + 1, cy);
		}
	    }
	    if (choice == 'e')
	    {
		collect.equip (user);
		drawMap ();
	    }
	    if (choice == '=')
	    {
		save ();
	    }
	}
    }


    public boolean traverse (int tile, int x, int y) throws IOException
    {
	boolean ret = true;
	if ((tile == 0 || tile < -1) && characters [x] [y] == 0)
	{
	}
	else if (tile != -1 && crossable [tile])
	{
	}
	else
	    ret = false;

	for (int i = 0 ; i < wall.length ; i++)
	{
	    if (wall [i] [1] == px && wall [i] [2] == py && wall [i] [3] == pz)
	    {
		if ((wall [i] [4] == 0 && y == wall [i] [5]) || (wall [i] [4] == 1 && x == wall [i] [5]))
		{
		    boolean red = true;
		    red = read (wall [i] [0]);
		    if (!red)
		    {
			ret = false;
		    }
		}
	    }
	}

	return ret;
    }


    public void enter (int tile)
    {
	/*
	if (safe > 0)
	{
	    safe--;
	}
	for (int i = 0 ; i < encounters.length ; i++)
	{
	    if (encounters [i] [0] == tile)
	    {
		if ((int) (Math.random () * 100) < encounters [i] [1] && safe == 0)
		{
		    fight (encounters [i] [2], encounters [i] [3]);
		    drawMap ();
		    safe = 10;
		}
	    }
	}
	*/
	for (int i = 0 ; i < encounters.length ; i++)
	{
	    if (encounters [i] [0] == tile)
	    {
		if ((int) (Math.random () * encounters [i] [1] * safe) > 500)
		{
		    fight (encounters [i] [2], encounters [i] [3]);
		    drawMap ();
		    safe = 0;
		}
		else
		{
		    safe++;
		}
	    }
	}
    }


    public void fight (int monster, int level)
    {
	battleSystem.screen dis = new battleSystem.screen (c, 1);

	battleSystem.fighter fightA = new battleSystem.player (dis, user.getName (), user.getRace (), 1, user.getStats (), user.getHealth (), user.getMana ());
	battleSystem.fighter fightB = new battleSystem.enemy (dis, "", monster, 2, level);
	fightA.setOpponent (fightB);
	fightB.setOpponent (fightA);

	battleSystem.combat battle = new battleSystem.combat (fightA, fightB);

	int[] out = battle.fight (dis);

	user.setHealth (out [0]);
	user.setMana (out [1]);

	loot (monster, level);
    }


    public void loot (int monster, int level)
    {
	int exp = 0;
	c.clear ();
	if (monster == 10) //crop squirrel
	{
	    exp = (level);
	}
	if (monster == 11) //wyric
	{
	    exp = (level * 4);
	}
	if (monster == 12) //treant twig
	{
	    exp = (level * 2);
	}
	if (monster == 13) //woodrend
	{
	    exp = (level * 5);
	}
	c.println ("You got " + exp + " exp");
	user.gainExp (exp);
    }


    public void interact (int x, int y) throws IOException
    {
	for (int i = 0 ; i < dialogue.length ; i++)
	{
	    if (dialogue [i] [1] == px && dialogue [i] [2] == py && dialogue [i] [3] == pz && dialogue [i] [0] != 0)
	    {
		if (dialogue [i] [4] == x && dialogue [i] [5] == y)
		{
		    read (dialogue [i] [0]);
		}
	    }
	}
	drawMap ();
    }


    public boolean read (int quote) throws IOException
    {
	boolean in = true;
	boolean ret = true;
	BufferedReader sr = new BufferedReader (new FileReader ("maps/" + background + "/maps/" + area + "/dialogue.txt"));
	while (true) //searches all lines
	{
	    String text = sr.readLine ();
	    if (text.equals ("new_" + quote)) //find the dialogue
	    {
		while (true)
		{
		    text = sr.readLine ();
		    if (text.equals ("end_" + quote))
		    {
			break;
		    }
		    String[] div = text.split ("_");
		    if (div [0].equals ("check"))
		    {
			RandomAccessFile raf = new RandomAccessFile ("users/" + saver + "/progress", "rw");
			raf.seek (10 * Integer.parseInt (div [1]));
			int prog = raf.readInt ();
			raf.close ();
			if (div [2].equals ("="))
			{
			    if (prog == Integer.parseInt (div [3]))
			    {
				in = true;
			    }
			    else
			    {
				in = false;
			    }
			}
			if (div [2].equals (">"))
			{
			    if (prog > Integer.parseInt (div [3]))
			    {
				in = true;
			    }
			    else
			    {
				in = false;
			    }
			}
			if (div [2].equals ("<"))
			{
			    if (prog < Integer.parseInt (div [3]))
			    {
				in = true;
			    }
			    else
			    {
				in = false;
			    }
			}
			if (div [2].equals ("!"))
			{
			    in = true;
			}
		    }
		    if (in) //if the line has passed the established conditions
		    {
			int get = command (div);
			if (get == 1) //false
			{
			    ret = false;
			}
		    }
		}
		break; //no longer needs to search
	    }
	}
	sr.close ();

	c.setColor (Color.white);
	c.fillRect (730, 250, 700, 200);

	return ret;
    }


    public int command (String[] com) throws IOException
    {
	int ret = 0;
	if (com [0].equals ("print"))
	{
	    /*
	    print codes
	    0: print
	    1: who is talking
	    2: their expression
	    3: their text
	    */
	    int who = Integer.parseInt (com [1]);
	    if (who == -1) //player
	    {
		who = user.getRace ();
	    }
	    int express = Integer.parseInt (com [2]);
	    textBox.tPrint (who, express, com [3]);
	}
	if (com [0].equals ("gain"))
	{
	    int type = Integer.parseInt (com [1]);
	    int ID = Integer.parseInt (com [2]);
	    int quantity = Integer.parseInt (com [3]);
	    collect.own [type] [ID] += quantity;
	}
	if (com [0].equals ("acheive"))
	{
	    /*
	    print codes
	    0: acheive
	    1: which section
	    2: what it becomes
	    */
	    RandomAccessFile raf = new RandomAccessFile ("users/" + saver + "/progress", "rw");
	    raf.seek (10 * Integer.parseInt (com [1]));
	    raf.writeInt (Integer.parseInt (com [2]));
	    raf.close ();
	    save ();
	}
	if (com [0].equals ("fight"))
	{
	    fight (Integer.parseInt (com [1]), Integer.parseInt (com [2]));
	    drawMap ();
	}
	if (com [0].equals ("heal"))
	{
	    user.heal ();
	}
	if (com [0].equals ("equip"))
	{
	    collect.equip (user);
	}
	if (com [0].equals ("no"))
	{
	    ret = 1;
	}
	return ret;
	/*
	ret codes
	1: false
	*/
    }


    public void make (String name, int number) throws IOException
    {
	if (name.equals ("null"))
	{
	    for (int x = 0 ; x < 14 ; x++)
	    {
		for (int y = 0 ; y < 14 ; y++)
		{
		    base [x] [y] = 0;
		    characters [x] [y] = 0;
		    elements [x] [y] = 0;
		}
	    }
	}
	else
	{
	    RandomAccessFile raf = new RandomAccessFile ("maps/" + background + "/" + name, "rw");
	    raf.seek (3000 * number);
	    for (int x = 0 ; x < 14 ; x++)
	    {
		for (int y = 0 ; y < 14 ; y++)
		{
		    base [x] [y] = raf.readInt ();
		    elements [x] [y] = raf.readInt ();
		    characters [x] [y] = raf.readInt ();
		}
	    }
	    raf.close ();
	}
    }


    public void connect () throws IOException
    {
	for (int i = 0 ; i < connections.length ; i++)
	{
	    if (px == connections [i] [0] && py == connections [i] [1] && pz == connections [i] [2]) //right tile
	    {
		if (face == connections [i] [6]) //facing the right direction
		{
		    px = connections [i] [3];
		    py = connections [i] [4];
		    pz = connections [i] [5];
		    background = connectText [i] [0];
		    areaLoad (connectText [i] [1]);
		    if (face == 1) //up
		    {
			py--;
		    }
		    if (face == 2) //left
		    {
			px++;
		    }
		    if (face == 3) //down
		    {
			py++;
		    }
		    if (face == 4) //right
		    {
			px--;
		    }
		}
	    }
	}
    }


    public void mapLoad () throws IOException
    {
	String[] div = map [px] [py] [pz].split (" ");
	make (div [0], Integer.parseInt (div [1]));
    }


    public void save () throws IOException
    {
	RandomAccessFile raf = new RandomAccessFile ("users/" + saver + "/essentials", "rw");
	raf.seek (0);
	raf.writeUTF (user.getName ());
	raf.seek (50);
	raf.writeUTF (background);
	raf.seek (100);
	raf.writeUTF (area);
	raf.seek (150);
	raf.writeInt (px);
	raf.seek (160);
	raf.writeInt (py);
	raf.seek (170);
	raf.writeInt (pz);
	raf.seek (180);
	raf.writeInt (cx);
	raf.seek (190);
	raf.writeInt (cy);
	raf.seek (220);
	raf.writeInt (user.getHealth ());
	raf.seek (230);
	raf.writeInt (user.getMana ());

	//stats
	raf.seek (200);
	raf.writeInt (user.getRace ());
	raf.seek (210);
	raf.writeInt (user.getLevel ());
	raf.seek (240);
	raf.writeInt (user.getHelmet ());
	raf.seek (250);
	raf.writeInt (user.getArmor ());
	raf.seek (260);
	raf.writeInt (user.getWeapon ());
	raf.seek (270);
	raf.writeInt (user.getBoots ());
	raf.seek (280);
	raf.writeInt (user.getAccessory ());
	raf.seek (290);
	raf.writeInt (user.getTool1 ());
	raf.seek (300);
	raf.writeInt (user.getTool2 ());
	int[] skills = user.getSkills ();
	raf.seek (310);
	raf.writeInt (skills [0]);
	raf.seek (320);
	raf.writeInt (skills [1]);
	raf.seek (330);
	raf.writeInt (skills [2]);
	raf.seek (340);
	raf.writeInt (skills [3]);
	raf.seek (350);
	raf.writeInt (user.getExp ());



	raf.close ();

	raf = new RandomAccessFile ("users/" + saver + "/inventory/helmets", "rw");
	for (int i = 0 ; i < 100 ; i++)
	{
	    raf.seek (i * 10);
	    raf.writeInt (collect.own [1] [i]);
	}
	raf.close ();
	raf = new RandomAccessFile ("users/" + saver + "/inventory/armor", "rw");
	for (int i = 0 ; i < 100 ; i++)
	{
	    raf.seek (i * 10);
	    raf.writeInt (collect.own [2] [i]);
	}
	raf.close ();
	raf = new RandomAccessFile ("users/" + saver + "/inventory/boots", "rw");
	for (int i = 0 ; i < 100 ; i++)
	{
	    raf.seek (i * 10);
	    raf.writeInt (collect.own [3] [i]);
	}
	raf.close ();
	raf = new RandomAccessFile ("users/" + saver + "/inventory/tools", "rw");
	for (int i = 0 ; i < 100 ; i++)
	{
	    raf.seek (i * 10);
	    raf.writeInt (collect.own [5] [i]);
	}
	raf.close ();
	raf = new RandomAccessFile ("users/" + saver + "/inventory/assorted", "rw");
	for (int i = 0 ; i < 100 ; i++)
	{
	    raf.seek (i * 10);
	    raf.writeInt (collect.own [6] [i]);
	}
	raf.close ();
	raf = new RandomAccessFile ("users/" + saver + "/inventory/skills", "rw");
	for (int i = 0 ; i < 100 ; i++)
	{
	    raf.seek (i * 10);
	    raf.writeInt (collect.own [7] [i]);
	}
	raf.close ();
    }
}
