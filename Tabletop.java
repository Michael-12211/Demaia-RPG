// The "Tabletop" class.
import java.awt.*;
import java.io.*;
import java.util.*;
import hsa.Console;

//white square added to block the view of UI

public class Tabletop
{
    static Console c;           // The output console
    static display dis;

    static String background;
    static int hold;
    static int holdType;
    static int ch;

    static int[] [] quick = new int [6] [10];
    static int[] [] type = new int [6] [10];
    //1: element, 2: character, 3: base

    static int[] [] elements = new int [14] [14];
    static int[] [] characters = new int [14] [14];
    static int[] [] base = new int [14] [14];

    static boolean adventure;
    static String[] [] [] map; //x, y, z
    static int mx;
    static int my;
    static int mz;
    static int px;
    static int py;
    static int pz;

    public static void main (String[] args) throws IOException
    {

	c = new Console (35, 187);
	dis = new display (c);

	hold = 0;
	holdType = 0;

	adventure = false;
	mx = 0;
	my = 0;
	mz = 0;
	px = 0;
	py = 0;
	pz = 0;

	for (int x = 0 ; x < 14 ; x++)
	{
	    for (int y = 0 ; y < 14 ; y++)
	    {
		elements [x] [y] = 0;
		characters [x] [y] = 0;
		base [x] [y] = 0;
	    }
	}
	for (int b = 0 ; b < 6 ; b++)
	{
	    for (int i = 0 ; i < 10 ; i++)
	    {
		quick [b] [i] = 0;
		type [b] [i] = 0;
		//quickfill
		quick [b] [i] = i + ((b / 3) * 10);
		type [b] [i] = (b % 3) + 1;
	    }
	}
	//presetting specific quicks
	for (int i = 20 ; i < 30 ; i++)
	{
	    quick [4] [i - 20] = i;
	    type [4] [i - 20] = 1;
	}
	quick [0] [1] = -1;
	type [0] [1] = 1;
	quick [4] [6] = 30;
	type [4] [6] = 1;
	quick [0] [2] = 31;
	type [0] [2] = 1;
	quick [0] [2] = 32;
	type [0] [2] = 1;
	quick [5] [4] = 34;
	type [5] [4] = 1;
	quick [5] [5] = 33;
	type [5] [5] = 1;
	quick [5] [6] = 35;
	type [5] [6] = 1;
	//quick [1] [3] = 4;
	//type [1] [3] = 4;
	//end of presetting quicks

	//main game
	while (true)
	{
	    c.clear ();
	    c.println ("What is the group for this tile?");
	    background = c.readString ();
	    for (int b = 0 ; b < 14 ; b++)
	    {
		for (int i = 0 ; i < 14 ; i++)
		{
		    /*
		    if (background.equals ("plain") || background.equals ("garden"))
		    {
			base [b] [i] = 1;
		    }

		    if (background.equals ("forest"))
		    {
			base [b] [i] = 2;
		    }
		    */
		    elements [i] [b] = 0;
		    characters [i] [b] = 0;
		    base [i] [b] = 0;
		}
	    }
	    c.println ("Would you like to use a template (t), a map (m), or a blank?");
	    char chice = c.getChar ();
	    if (chice == 't')
	    {
		c.println ("What is the name of the template?");
		String name = c.readString ();
		c.println ("What number of the template?");
		int number = c.readInt ();

		make (name, number);
	    }
	    else if (chice == 'm')
	    {
		c.println ("What is the name of the map?");
		String name = c.readLine ();

		BufferedReader sr = new BufferedReader (new FileReader ("maps/" + background + "/maps/" + name + "/map.txt"));
		mx = Integer.parseInt (sr.readLine ());
		my = Integer.parseInt (sr.readLine ());
		mz = Integer.parseInt (sr.readLine ());
		String line = sr.readLine ();
		String[] div = line.split (" ");
		px = Integer.parseInt (div [0]);
		py = Integer.parseInt (div [1]);
		pz = Integer.parseInt (div [2]);
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

		adventure = true;
		mapLoad ();
	    }
	    else
	    {
		c.println ("What backgound do you want?");
		int bg = c.readInt ();
		for (int b = 0 ; b < 14 ; b++)
		{
		    for (int i = 0 ; i < 14 ; i++)
		    {
			base [b] [i] = bg;
		    }
		}
	    }

	    dis.drawMap (base, elements, characters);

	    int cx = 6;
	    int cy = 6;
	    ch = 0;
	    char choice = ' ';

	    /*
	    options
	    w a s d: move cursor
	    q e: change hand
	    space: pick up / place
	    f: make
	    x: inventory
	    z: clear tile
	    =: save
	    i j k l: scroll the map
	    u o: vertical map scrolling
	    */

	    while (choice != 'p')
	    {
		drawCursor (cx, cy);
		choice = c.getChar ();
		if (choice == 'w' && cy != 0)
		{
		    dis.drawTile (cx, cy, base, elements, characters);
		    cy--;
		}
		if (choice == 'a' && cx != 0)
		{
		    dis.drawTile (cx, cy, base, elements, characters);
		    cx--;
		}
		if (choice == 's' && cy != 13)
		{
		    dis.drawTile (cx, cy, base, elements, characters);
		    cy++;
		}
		if (choice == 'd' && cx != 13)
		{
		    dis.drawTile (cx, cy, base, elements, characters);
		    cx++;
		}
		if (choice == 'q' && ch != 0)
		{
		    ch--;
		    drawHand ();
		}
		if (choice == 'e' && ch != 5)
		{
		    ch++;
		    drawHand ();
		}
		if (choice == 'z')
		{
		    elements [cx] [cy] = 0;
		    characters [cx] [cy] = 0;
		    dis.drawMap (base, elements, characters);
		}
		if (choice == '=')
		{
		    save ();
		    dis.drawMap (base, elements, characters);
		}
		if (adventure)
		{
		    if (choice == 'i' && py != 0)
		    {
			py--;
			mapLoad ();
			dis.drawMap (base, elements, characters);
			drawHand ();
		    }
		    if (choice == 'j' && px != 0)
		    {
			px--;
			mapLoad ();
			dis.drawMap (base, elements, characters);
			drawHand ();
		    }
		    if (choice == 'k' && py != my - 1)
		    {
			py++;
			mapLoad ();
			dis.drawMap (base, elements, characters);
			drawHand ();
		    }
		    if (choice == 'l' && px != mx - 1)
		    {
			px++;
			mapLoad ();
			dis.drawMap (base, elements, characters);
			drawHand ();
		    }
		    if (choice == 'u' && pz != 0)
		    {
			pz--;
			mapLoad ();
			dis.drawMap (base, elements, characters);
			drawHand ();
		    }
		    if (choice == 'o' && pz != mz - 1)
		    {
			pz++;
			mapLoad ();
			dis.drawMap (base, elements, characters);
			drawHand ();
		    }
		}
		int use = checker (choice);
		if (use >= 0)
		{
		    if (type [ch] [use] == 1)
		    {
			elements [cx] [cy] = quick [ch] [use];
		    }
		    if (type [ch] [use] == 2)
		    {
			characters [cx] [cy] = quick [ch] [use];
		    }
		    if (type [ch] [use] == 3)
		    {
			base [cx] [cy] = quick [ch] [use];
		    }
		    dis.drawMap (base, elements, characters);
		    drawHand ();
		}
	    }
	}
    } // main method


    public static void save () throws IOException
    {
	c.println ("What name will you save this under?");
	String name = c.readString ();
	c.println ("What number will you save this under?");
	int number = c.readInt ();

	RandomAccessFile raf = new RandomAccessFile ("maps/" + background + "/" + name, "rw");
	raf.seek (3000 * number);
	for (int x = 0 ; x < 14 ; x++)
	{
	    for (int y = 0 ; y < 14 ; y++)
	    {
		raf.writeInt (base [x] [y]);
		raf.writeInt (elements [x] [y]);
		raf.writeInt (characters [x] [y]);
	    }
	}
	raf.close ();
    }


    public static void make (String name, int number) throws IOException
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


    public static void mapLoad () throws IOException
    {
	String[] div = map [px] [py] [pz].split (" ");
	make (div [0], Integer.parseInt (div [1]));
    }


    /*
    public static void drawMap ()
    {
	c.clear ();
	for (int x = 0 ; x < 14 ; x++)
	{
	    for (int y = 0 ; y < 14 ; y++)
	    {
		drawTile (x, y);
	    }
	}
	drawHand ();
    }
    */


    public static void drawHand ()
    {
	c.setColor (Color.white);
	c.fillRect (750, 0, 500, 1000);
	for (int b = 0 ; b < 6 ; b++)
	{
	    for (int i = 0 ; i < 10 ; i++)
	    {
		if (type [b] [i] == 1) //elemens
		{
		    dis.drawE (quick [b] [i], 16 + b, i + 1);
		}
		if (type [b] [i] == 2) //characters
		{
		    dis.drawC (quick [b] [i], 16 + b, i + 1);
		}
		if (type [b] [i] == 3) //base
		{
		    dis.drawB (quick [b] [i], 16 + b, i + 1);
		}
	    }
	}
	c.setColor (Color.red);
	c.fillRect (810 + 50 * ch, 20, 30, 30);
	c.setColor(Color.white);
	//c.fillRect(750,0,500,1000); //comment out to view UI
    }


    public static void drawTile (int x, int y)
    {
	drawB (base [x] [y], x, y);
	drawE (elements [x] [y], x, y);
	drawC (characters [x] [y], x, y);
    }


    public static void drawB (int what, int x, int y)
    {
	/*
	base codes:
	1: light grass
	2: dark grass
	3: flowered ground
	4: trail
	5: brick
	6: white brick
	7: whood planks
	8: water
	9: stone
	10: shadow hole
	11: rough ground
	12: cliffside
	13: tile floor
	*/
	Color purple = new Color (201, 48, 186);
	Color brown = new Color (128, 64, 0);
	Color beige = new Color (255, 225, 196);
	Color drkpurple = new Color (155, 0, 155);
	Color lgtbrown = new Color (217, 108, 0);
	Color drkgray = new Color (122, 122, 122);
	Color dread = new Color (62, 99, 39);
	Color sheen = new Color (80, 80, 71);
	Color teal = new Color (162, 255, 255);
	Color fur = new Color (236, 236, 236);
	Color hazel = new Color (170, 85, 0);
	Color bronze = new Color (195, 126, 56);
	Color orange = new Color (255, 128, 0);
	Color leave = new Color (61, 137, 52);

	x = x * 50;
	y = y * 50;

	c.setColor (Color.white);
	c.fillRect (x, y, 50, 50);

	if (what == 1) //plain
	{
	    c.setColor (Color.green);
	    c.fillRect (x, y, 50, 50);
	    for (int i = 0 ; i < 3 ; i++)
	    {
		drawA (2, (int) (Math.random () * 40) + x, (int) (Math.random () * 40) + y);
	    }
	}
	if (what == 2) //forest
	{
	    c.setColor (dread);
	    c.fillRect (x, y, 50, 50);
	    for (int i = 0 ; i < 3 ; i++)
	    {
		drawA (2, (int) (Math.random () * 40) + x, (int) (Math.random () * 40) + y);
	    }
	}
	if (what == 3) //flowered ground
	{
	    c.setColor (Color.green);
	    c.fillRect (x, y, 50, 50);
	    drawA (1, (int) (Math.random () * 20) + x, (int) (Math.random () * 20) + y);
	}
	if (what == 4) //trail
	{
	    c.setColor (hazel);
	    c.fillRect (x, y, 50, 50);
	    c.setColor (bronze);
	    for (int i = 0 ; i < 5 ; i++)
	    {
		c.fillOval ((int) (Math.random () * 47) + x, (int) (Math.random () * 47) + y, 3, 3);
	    }
	}
	if (what == 5) //brick
	{
	    c.setColor (Color.red);
	    c.fillRect (x, y, 50, 50);
	    c.setColor (Color.black);
	    c.drawLine (x, y, x + 50, y);
	    c.drawLine (x, y + 25, x + 50, y + 25);
	    c.drawLine (x + 17, y, x + 17, y + 26);
	    c.drawLine (x + 33, y + 25, x + 33, y + 50);
	}
	if (what == 6) //white brick
	{
	    c.setColor (fur);
	    c.fillRect (x, y, 50, 50);
	    c.setColor (Color.black);
	    c.drawLine (x, y, x + 50, y);
	    c.drawLine (x, y + 25, x + 50, y + 25);
	    c.drawLine (x + 17, y, x + 17, y + 26);
	    c.drawLine (x + 33, y + 25, x + 33, y + 50);
	}
	if (what == 7) //wood planks
	{
	    c.setColor (brown);
	    c.fillRect (x, y, 50, 50);
	    c.setColor (Color.black);
	    c.drawLine (x, y, x, y + 50);
	    c.drawLine (x + 13, y, x + 13, y + 50);
	    c.drawLine (x + 25, y, x + 25, y + 50);
	    c.drawLine (x + 37, y, x + 37, y + 50);
	    for (int i = 0 ; i < 4 ; i++)
	    {
		int h = (int) (Math.random () * 50);
		c.drawLine (x + (int) (i * 12.5), y + h, x + (int) ((i + 1) * 12.5), y + h);
	    }
	}
	if (what == 8) //water
	{
	    c.setColor (Color.blue);
	    c.fillRect (x, y, 50, 50);
	    for (int i = 0 ; i < 2 ; i++)
	    {
		drawA (5, (int) (Math.random () * 30) + x, (int) (Math.random () * 40) + y);
	    }
	}
	if (what == 9) //stone
	{
	    c.setColor (Color.gray);
	    c.fillRect (x, y, 50, 50);
	    drawA (6, (int) (Math.random () * 35) + x, (int) (Math.random () * 35) + y);
	}
	if (what == 10) //shadow hole
	{
	    c.setColor (Color.black);
	    c.fillRect (x, y, 50, 50);
	}
	if (what == 11) //rough ground
	{
	    c.setColor (hazel);
	    c.fillRect (x, y, 50, 50);
	    for (int i = 0 ; i < 3 ; i++)
	    {
		drawA (7, x + (int) (Math.random () * 30), y + (int) (Math.random () * 45));
	    }
	}
	if (what == 12) //cliffside
	{
	    c.setColor (hazel);
	    c.fillRect (x, y, 50, 50);
	    c.setColor (sheen);
	    for (int i = 0 ; i < 5 ; i++)
	    {
		c.fillOval (x + (int) (Math.random () * 43), y + (int) (Math.random () * 43), 7, 7);
	    }
	}
	if (what == 13) //tile floor
	{
	    c.setColor (Color.black);
	    c.fillRect (x, y, 50, 50);
	    c.setColor (fur);
	    c.fillRect (x + 1, y + 1, 23, 23);
	    c.fillRect (x + 26, y + 1, 23, 23);
	    c.fillRect (x + 26, y + 26, 23, 23);
	    c.fillRect (x + 1, y + 26, 23, 23);
	}
    }


    public static void drawE (int what, int x, int y)
    {

	/*
	element codes:
	1: red flower
	2: blue flower
	3: orange flower
	4: brambles
	5: skull
	6: bones
	7: collumn head
	8: collumn
	9: collumn base
	10: chest
	11: hive
	12: window 1
	13: window 2
	14: glass pillar
	15: fence
	16: table 1
	17: table 2
	18: table 3
	19: table 4
	20: log
	21: shrub
	22: table 5
	23: table 6
	24: table 7
	25: table 8
	26: pillow
	27: crystal
	28: bookshelf
	*/
	Color purple = new Color (201, 48, 186);
	Color brown = new Color (128, 64, 0);
	Color beige = new Color (255, 225, 196);
	Color drkpurple = new Color (155, 0, 155);
	Color lgtbrown = new Color (217, 108, 0);
	Color drkgray = new Color (122, 122, 122);
	Color dread = new Color (62, 99, 39);
	Color sheen = new Color (80, 80, 71);
	Color teal = new Color (162, 255, 255);
	Color fur = new Color (236, 236, 236);
	Color hazel = new Color (170, 85, 0);
	Color bronze = new Color (195, 126, 56);
	Color orange = new Color (255, 128, 0);
	Color leave = new Color (61, 137, 52);

	x = x * 50;
	y = y * 50;

	if (what == 1) //red flower
	{
	    c.setColor (Color.red);
	    c.fillOval (x, y, 30, 30);
	    c.fillOval (x, y + 20, 30, 30);
	    c.fillOval (x + 20, y, 30, 30);
	    c.fillOval (x + 20, y + 20, 30, 30);
	    c.setColor (Color.yellow);
	    c.fillOval (x + 10, y + 10, 30, 30);
	}
	if (what == 2) //blue flower
	{
	    c.setColor (Color.blue);
	    c.fillOval (x, y, 30, 30);
	    c.fillOval (x, y + 20, 30, 30);
	    c.fillOval (x + 20, y, 30, 30);
	    c.fillOval (x + 20, y + 20, 30, 30);
	    c.setColor (Color.yellow);
	    c.fillOval (x + 10, y + 10, 30, 30);
	}
	if (what == 3) //orange flower
	{
	    c.setColor (orange);
	    c.fillOval (x, y, 30, 30);
	    c.fillOval (x, y + 20, 30, 30);
	    c.fillOval (x + 20, y, 30, 30);
	    c.fillOval (x + 20, y + 20, 30, 30);
	    c.setColor (Color.yellow);
	    c.fillOval (x + 10, y + 10, 30, 30);
	}
	if (what == 4) //brambles
	{
	    c.setColor (dread);
	    for (int i = 0 ; i < 15 ; i++)
	    {
		c.fillOval ((int) (Math.random () * 40) + x, (int) (Math.random () * 40) + y, 10, 10);
	    }
	    c.fillOval (x + 10, y + 10, 10, 30);
	    c.fillOval (x + 15, y + 20, 25, 10);
	    c.fillOval (x + 10, y + 10, 30, 10);
	    c.fillOval (x + 30, y + 15, 6, 30);
	    c.fillOval (x + 20, y + 28, 10, 25);
	    c.fillOval (x - 3, y + 20, 25, 10);
	    c.fillOval (x + 20, y - 3, 10, 25);
	    c.fillOval (x + 28, y + 20, 25, 10);
	    c.setColor (fur);
	    for (int i = 0 ; i < 7 ; i++)
	    {
		c.fillStar ((int) (Math.random () * 45) + x, (int) (Math.random () * 45) + y, 5, 5);
	    }
	}
	if (what == 5) //skull
	{
	    c.setColor (fur);
	    c.fillOval (x + 10, y + 10, 30, 20);
	    c.fillRect (x + 15, y + 20, 20, 20);
	    c.setColor (Color.black);
	    c.fillOval (x + 15, y + 15, 8, 8);
	    c.fillOval (x + 27, y + 15, 8, 8);
	    c.drawLine (x + 15, y + 34, x + 35, y + 34);
	    c.drawLine (x + 20, y + 28, x + 20, y + 40);
	    c.drawLine (x + 25, y + 28, x + 25, y + 40);
	    c.drawLine (x + 30, y + 28, x + 30, y + 40);
	}
	if (what == 6) //bones
	{
	    for (int i = 0 ; i < 4 ; i++)
	    {
		drawA (3, (int) (Math.random () * 42) + x, (int) (Math.random () * 38) + y);
	    }
	    for (int i = 0 ; i < 3 ; i++)
	    {
		drawA (4, (int) (Math.random () * 40) + x, (int) (Math.random () * 40) + y);
	    }
	}
	if (what == 7) //collumn head
	{
	    c.setColor (fur);
	    c.fillRect (x + 10, y, 30, 50);
	    c.setColor (Color.black);
	    c.drawLine (x + 20, y, x + 20, y + 50);
	    c.drawLine (x + 30, y, x + 30, y + 50);
	    c.setColor (fur);
	    c.fillRect (x + 5, y, 40, 10);
	    c.fillOval (x, y, 10, 10);
	    c.fillOval (x + 40, y, 10, 10);
	}
	if (what == 8) //collumn
	{
	    c.setColor (fur);
	    c.fillRect (x + 10, y, 30, 50);
	    c.setColor (Color.black);
	    c.drawLine (x + 20, y, x + 20, y + 50);
	    c.drawLine (x + 30, y, x + 30, y + 50);
	}
	if (what == 9) //collumn base
	{
	    c.setColor (fur);
	    c.fillRect (x + 10, y, 30, 50);
	    c.setColor (Color.black);
	    c.drawLine (x + 20, y, x + 20, y + 50);
	    c.drawLine (x + 30, y, x + 30, y + 50);
	    c.setColor (fur);
	    c.fillRect (x + 5, y + 40, 40, 10);
	    c.fillOval (x, y + 40, 10, 10);
	    c.fillOval (x + 40, y + 40, 10, 10);
	}
	if (what == 10) //chest
	{
	    c.setColor (brown);
	    c.fillRect (x, y + 10, 50, 40);
	    c.setColor (Color.gray);
	    c.fillRect (x, y + 25, 50, 5);
	    c.fillRect (x + 20, y + 23, 10, 10);
	    c.setColor (Color.black);
	    c.fillRect (x + 23, y + 26, 4, 4);
	}
	if (what == 11) //hive
	{
	    c.setColor (Color.yellow);
	    c.fillRect (x + 10, y + 15, 30, 35);
	    c.setColor (Color.black);
	    c.fillArc (x + 20, y + 40, 10, 20, 0, 180);
	    c.drawLine (x + 10, y + 25, x + 40, y + 25);
	    c.drawLine (x + 10, y + 35, x + 40, y + 35);
	}
	if (what == 12) //window 1
	{
	    c.setColor (teal);
	    c.fillRect (x, y + 23, 50, 4);
	}
	if (what == 13) //window 2
	{
	    c.setColor (teal);
	    c.fillRect (x + 23, y, 4, 50);
	}
	if (what == 14) //glass pillar
	{
	    c.setColor (teal);
	    c.fillOval (x, y, 50, 50);
	}
	if (what == 15) //fence
	{
	    c.setColor (bronze);
	    c.fillRect (x + 10, y, 10, 50);
	    c.fillRect (x + 30, y, 10, 50);
	    c.fillRect (x, y + 10, 50, 10);
	    c.fillRect (x, y + 30, 50, 10);
	}
	if (what == 16) //table 1
	{
	    c.setColor (bronze);
	    c.fillArc (x, y, 100, 100, 90, 90);
	    c.setColor (brown);
	    c.fillArc (x + 10, y + 10, 80, 80, 90, 90);
	}
	if (what == 17) //table 2
	{
	    c.setColor (bronze);
	    c.fillArc (x - 50, y, 100, 100, 0, 90);
	    c.setColor (brown);
	    c.fillArc (x - 40, y + 10, 80, 80, 0, 90);
	}
	if (what == 18) //table 3
	{
	    c.setColor (bronze);
	    c.fillArc (x, y - 50, 100, 100, 180, 90);
	    c.setColor (brown);
	    c.fillArc (x + 10, y - 40, 80, 80, 180, 90);
	}
	if (what == 19) //table 4
	{
	    c.setColor (bronze);
	    c.fillArc (x - 50, y - 50, 100, 100, 270, 90);
	    c.setColor (brown);
	    c.fillArc (x - 40, y - 40, 80, 80, 270, 90);
	}
	if (what == 20) //log
	{
	    c.setColor (brown);
	    c.fillRect (x + 10, y, 30, 50);
	    c.setColor (Color.black);
	    for (int i = 0 ; i < 2 ; i++)
	    {
		c.drawLine (x + 20 + (10 * i), y + (int) (Math.random () * 25), x + 20 + (10 * i), y + (int) (Math.random () * 25) + 25);
	    }
	}
	if (what == 21) //shrub
	{
	    c.setColor (leave);
	    c.fillOval (x - 3, y - 3, 30, 30);
	    c.fillOval (x + 10, y - 3, 30, 30);
	    c.fillOval (x + 20, y - 3, 30, 30);
	    c.fillOval (x + 20, y + 10, 30, 30);
	    c.fillOval (x + 20, y + 20, 30, 30);
	    c.fillOval (x + 10, y + 20, 30, 30);
	    c.fillOval (x - 3, y + 20, 30, 30);
	    c.fillOval (x - 3, y + 10, 30, 30);
	}
	if (what == 22) //table 5
	{
	    c.setColor (brown);
	    c.fillRect (x, y, 50, 50);
	    c.setColor (bronze);
	    c.fillRect (x, y, 50, 10);
	}
	if (what == 23) //table 6
	{
	    c.setColor (brown);
	    c.fillRect (x, y, 50, 50);
	    c.setColor (bronze);
	    c.fillRect (x + 40, y, 10, 50);
	}
	if (what == 24) //table 7
	{
	    c.setColor (brown);
	    c.fillRect (x, y, 50, 50);
	    c.setColor (bronze);
	    c.fillRect (x, y + 40, 50, 10);
	}
	if (what == 25) //table 8
	{
	    c.setColor (brown);
	    c.fillRect (x, y, 50, 50);
	    c.setColor (bronze);
	    c.fillRect (x, y, 10, 50);
	}
	if (what == 26) //pillow
	{
	    c.setColor (Color.white);
	    c.fillRect (x + 5, y + 10, 40, 30);
	}
	if (what == 27) //crystal
	{
	    c.setColor (Color.red);
	    int[] x1 = {x + 10, x + 5, x + 5, x + 9, x + 20, x + 17, x + 25, x + 33, x + 30, x + 41, x + 45, x + 45, x + 40};
	    int[] y1 = {y + 50, y + 35, y + 30, y + 27, y + 40, y + 20, y + 12, y + 20, y + 40, y + 27, y + 30, y + 35, y + 50};
	    c.fillPolygon (x1, y1, 13);
	}
	if (what == 28) //bookshelf
	{
	    c.setColor (bronze);
	    c.fillRect (x, y, 50, 50);
	    c.setColor (Color.black);
	    c.fillRect (x + 5, y + 3, 40, 20);
	    c.fillRect (x + 5, y + 27, 40, 20);
	    for (int l = 0 ; l < 2 ; l++)
	    {
		int b = 5;
		while (true)
		{
		    b += (int) (Math.random () * 15) + 5;
		    if (b > 40)
			break;
		    drawA (8, b + x, y + 3 + 24 * l);
		}
	    }
	}
    }


    public static void drawC (int what, int x, int y)
    {
	/*
	character codes:
	1: hooded figure
	*/
	Color purple = new Color (201, 48, 186);
	Color brown = new Color (128, 64, 0);
	Color beige = new Color (255, 225, 196);
	Color drkpurple = new Color (155, 0, 155);
	Color lgtbrown = new Color (217, 108, 0);
	Color drkgray = new Color (122, 122, 122);
	Color dread = new Color (62, 99, 39);
	Color sheen = new Color (80, 80, 71);
	Color teal = new Color (162, 255, 255);
	Color fur = new Color (236, 236, 236);
	Color hazel = new Color (170, 85, 0);
	Color bronze = new Color (195, 126, 56);
	Color orange = new Color (255, 128, 0);
	Color leave = new Color (61, 137, 52);

	x = x * 50;
	y = y * 50;

	if (what == 1) //hooded figure
	{
	    c.setColor (purple);
	    c.fillRect (x + 5, y + 20, 40, 30);
	    c.fillOval (x + 5, y, 40, 40);
	    c.setColor (Color.black);
	    c.fillOval (x + 10, y + 5, 30, 30);
	    c.setColor (Color.white);
	    c.fillOval (x + 15, y + 20, 7, 7);
	    c.fillOval (x + 28, y + 20, 7, 7);
	}

    }


    public static void drawA (int what, int x, int y)  //additions not ties to the grid
    {
	/*
	character codes:
	1: flower
	2: grass tuft
	3: bone
	4: shards
	5: wave
	6: crack
	7: zag
	8: book
	*/
	Color purple = new Color (201, 48, 186);
	Color brown = new Color (128, 64, 0);
	Color beige = new Color (255, 225, 196);
	Color drkpurple = new Color (155, 0, 155);
	Color lgtbrown = new Color (217, 108, 0);
	Color drkgray = new Color (122, 122, 122);
	Color dread = new Color (62, 99, 39);
	Color sheen = new Color (80, 80, 71);
	Color teal = new Color (162, 255, 255);
	Color fur = new Color (236, 236, 236);
	Color hazel = new Color (170, 85, 0);
	Color bronze = new Color (195, 126, 56);
	Color orange = new Color (255, 128, 0);
	Color leave = new Color (61, 137, 52);

	if (what == 1) //flower
	{
	    c.setColor (Color.red);
	    c.fillOval (x, y, 20, 20);
	    c.fillOval (x + 10, y, 20, 20);
	    c.fillOval (x, y + 10, 20, 20);
	    c.fillOval (x + 10, y + 10, 20, 20);
	    c.setColor (Color.yellow);
	    c.fillOval (x + 5, y + 5, 20, 20);
	}

	if (what == 2) //grass tuft
	{
	    c.setColor (Color.black);
	    c.drawArc (x - 5, y, 10, 20, 0, 90);
	    c.drawArc (x - 10, y, 20, 20, 0, 90);
	}

	if (what == 3) //bone
	{
	    c.setColor (Color.white);
	    c.fillRect (x + 2, y + 2, 4, 8);
	    c.fillOval (x, y, 4, 4);
	    c.fillOval (x + 4, y, 4, 4);
	    c.fillOval (x, y + 8, 4, 4);
	    c.fillOval (x + 4, y + 8, 4, 4);
	}

	if (what == 4) //shards
	{
	    for (int i = 0 ; i < 5 ; i++)
	    {
		c.fillStar ((int) (Math.random () * 8) + x, (int) (Math.random () * 8) + y, 2, 2);
	    }
	}
	if (what == 5) //waves
	{
	    c.setColor (Color.black);
	    c.drawArc (x, y, 10, 10, 0, 180);
	    c.drawArc (x + 10, y, 10, 10, 180, 180);
	}
	if (what == 6) //crack
	{
	    c.setColor (Color.black);
	    c.fillOval (x + 6, y + 6, 3, 3);
	    for (int i = 0 ; i < 3 ; i++)
	    {
		c.drawLine (x + 7, y + 7, x + (int) (Math.random () * 15), y + (int) (Math.random () * 15));
	    }
	}
	if (what == 7) //zag
	{
	    c.setColor (Color.black);
	    c.drawLine (x, y + 3, x + 5, y);
	    c.drawLine (x + 5, y, x + 10, y + 3);
	    c.drawLine (x + 10, y + 3, x + 15, y);
	    c.drawLine (x + 15, y, x + 20, y + 3);
	}
	if (what == 8) //book
	{
	    int col = (int) (Math.random () * 4);
	    if (col == 1)
		c.setColor (brown);
	    else if (col == 2)
		c.setColor (dread);
	    else if (col == 3)
		c.setColor (Color.red);
	    else
		c.setColor (hazel);
	    c.fillRect (x, y, 5, 20);
	    if ((int) (Math.random () * 3) == 0)
	    {
		c.setColor (Color.yellow);
		c.fillRect (x + 1, y + 5, 3, 2);
	    }
	}
    }


    public static void drawCursor (int x, int y)
    {
	if (hold == 0)
	{
	    x = x * 50;
	    y = y * 50;
	    c.setColor (Color.blue);
	    c.fillRect (x, y, 10, 5);
	    c.fillRect (x, y, 5, 10);
	    c.fillRect (x, y + 45, 10, 5);
	    c.fillRect (x, y + 40, 5, 10);
	    c.fillRect (x + 40, y, 10, 5);
	    c.fillRect (x + 45, y, 5, 10);
	    c.fillRect (x + 40, y + 45, 10, 5);
	    c.fillRect (x + 45, y + 40, 5, 10);
	}
    }


    public static int checker (char value)
    {
	if (value == '0' || value == '1' || value == '2' || value == '3' || value == '4' || value == '5' || value == '6' || value == '7' || value == '8' || value == '9')
	{
	    return ((int) (value) - 48);
	}


	else
	    return -1;
    }
} // Tabletop class
