import java.awt.*;
import java.io.*;
import java.util.*;
import hsa.Console;

class display
{
    static Color purple = new Color (201, 48, 186);
    static Color brown = new Color (128, 64, 0);
    static Color beige = new Color (255, 225, 196);
    static Color drkpurple = new Color (155, 0, 155);
    static Color lgtbrown = new Color (217, 108, 0);
    static Color drkgray = new Color (122, 122, 122);
    static Color dread = new Color (62, 99, 39);
    static Color sheen = new Color (80, 80, 71);
    static Color teal = new Color (162, 255, 255);
    static Color fur = new Color (236, 236, 236);
    static Color hazel = new Color (170, 85, 0);
    static Color bronze = new Color (195, 126, 56);
    static Color orange = new Color (255, 128, 0);
    static Color leave = new Color (61, 137, 52);
    static Color drkBrown = new Color (98, 49, 0);
    static Color wChest = new Color (244, 204, 227);
    static Color scale = new Color (64, 182, 39);

    static Console c;

    public display (Console con)
    {
	c = con;
    }


    public static void drawMap (int[] [] base, int[] [] elements, int[] [] characters)
    {
	c.clear ();
	for (int x = 0 ; x < 14 ; x++)
	{
	    for (int y = 0 ; y < 14 ; y++)
	    {
		drawTile (x, y, base, elements, characters);
	    }
	}
    }


    public static void drawTile (int x, int y, int[] [] base, int[] [] elements, int[] [] characters)
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
	-1: invisible wall
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
	29: crops
	30: sign
	31: dresser
	32: bush
	33: wooden wall
	34: door
	45: thatch roof
	*/

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
	    c.fillRect (x + 10, y, 30, 35);
	    c.setColor (Color.black);
	    c.fillArc (x + 20, y + 25, 10, 20, 0, 180);
	    c.drawLine (x + 10, y + 10, x + 40, y + 10);
	    c.drawLine (x + 10, y + 20, x + 40, y + 20);
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
	if (what == 29) //crops
	{
	    for (int i = 0 ; i < 5 ; i++)
	    {
		drawA (9, x + (int) (Math.random () * 43), y + 10 * i);
	    }
	}
	if (what == 30) //sign
	{
	    c.setColor (brown);
	    c.fillRect (x, y, 50, 40);
	    c.fillRect (x + 20, y + 40, 10, 10);
	    c.setColor (Color.black);
	    c.drawLine (x + 10, y + 10, x + 40, y + 10);
	    c.drawLine (x + 10, y + 20, x + 40, y + 20);
	    c.drawLine (x + 10, y + 30, x + 40, y + 30);
	}
	if (what == 31) //dresser
	{
	    c.setColor (bronze);
	    c.fillRect (x, y, 50, 5);
	    c.setColor (drkBrown);
	    c.fillRect (x + 5, y + 5, 40, 40);
	    c.fillRect (x + 5, y + 45, 5, 5);
	    c.fillRect (x + 40, y + 45, 5, 5);
	    c.setColor (Color.black);
	    c.drawRect (x + 10, y + 15, 30, 20);
	    c.setColor (bronze);
	    c.fillRect (x + 20, y + 20, 10, 5);
	}
	if (what == 32) //bush
	{
	    c.setColor (leave);
	    c.fillOval (x, y + 20, 30, 30);
	    c.fillOval (x + 20, y + 20, 30, 30);
	    c.fillOval (x + 10, y + 10, 30, 30);
	    c.fillRect (x + 15, y + 30, 20, 20);
	}
	if (what == 33) //wooden wall
	{
	    c.setColor (brown);
	    c.fillRect (x, y, 50, 50);
	    c.setColor (Color.black);
	    c.drawLine (x + 25, y, x + 25, y + 50);
	    c.drawLine (x, y, x, y + 50);
	    drawA (7, x + 2 + ((int)(Math.random()*2))*25, y + 5 + (int) (Math.random () * 40));
	}
	if (what == 34) //door
	{
	    c.setColor (Color.black);
	    c.fillRect (x, y - 50, 50, 100);
	    c.setColor (brown);
	    c.fillRect (x + 3, y - 47, 44, 94);
	    c.setColor (lgtbrown);
	    c.fillOval (x + 10, y - 20, 10, 10);
	}
	if (what == 35) //thatch roof
	{
	    c.setColor (leave);
	    c.fillRect (x, y, 50, 50);
	    for (int i = 0 ; i < 3 ; i++)
	    {
		int x1 = x + (int) (Math.random () * 44);
		int y1 = y + (int) (Math.random () * 32);
		c.setColor (Color.black);
		c.drawOval (x1, y1, 6, 18);
		c.setColor (leave);
		c.fillOval (x1 + 1, y1, 5, 18);
	    }
	}
    }


    public static void drawC (int what, int x, int y)
    {
	/*
	character codes:
	1: hooded figure
	2: wyvern
	3: Goblin, F
	*/

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

	if (what == 2) //small wyvern
	{
	    c.setColor (fur); //body
	    int[] x2 = {x + 20, x + 10, x + 10, x + 20, x + 30, x + 40, x + 40, x + 30};
	    int[] y1 = {y - 35, y - 10, y + 25, y + 40, y + 40, y + 25, y - 10, y - 35};
	    c.fillPolygon (x2, y1, 8);
	    c.setColor (wChest);
	    int[] x1 = {x + 20, x + 17, x + 17, x + 20, x + 30, x + 33, x + 33, x + 30};
	    c.fillPolygon (x1, y1, 8);
	    c.setColor (fur); //legs
	    int[] x3 = {x + 10, x + 8, x + 8, x, x + 18, x + 18, x + 20};
	    int[] x4 = {x + 40, x + 42, x + 42, x + 50, x + 32, x + 32, x + 30};
	    int[] y2 = {y + 25, y + 32, y + 45, y + 50, y + 50, y + 45, y + 40};
	    c.fillPolygon (x3, y2, 7);
	    c.fillPolygon (x4, y2, 7);
	    c.setColor (fur); //face
	    int[] x5 = {x + 9, x + 25, x + 41, x + 25};
	    int[] y5 = {y - 42, y - 35, y - 42, y - 20};
	    c.fillPolygon (x5, y5, 4);
	    c.fillOval (x + 11, y - 40, 28, 20);
	    c.fillRect (x + 15, y - 31, 20, 13);
	    c.setColor (Color.black);
	    c.drawArc (x + 15, y - 31, 8, 8, 0, 180);
	    c.drawArc (x + 27, y - 31, 8, 8, 0, 180);
	    c.drawLine (x + 23, y - 27, x + 27, y - 27);
	    c.drawLine (x + 20, y - 21, x + 30, y - 21);
	    c.setColor (Color.white);
	    c.fillOval (x + 15, y - 38, 8, 5);
	    c.fillOval (x + 27, y - 38, 8, 5);
	    c.setColor (Color.black);
	    c.fillOval (x + 18, y - 36, 3, 3);
	    c.fillOval (x + 29, y - 36, 3, 3);
	    c.setColor (fur); //arms
	    int[] x6 = {x + 10, x + 3, x + 3, x + 8, x + 8, x + 10};
	    int[] x7 = {x + 40, x + 47, x + 47, x + 42, x + 42, x + 40};
	    int[] y6 = {y - 10, y + 5, y + 15, y + 15, y + 8, y - 3};
	    c.fillPolygon (x6, y6, 6);
	    c.fillPolygon (x7, y6, 6);
	    c.fillOval (x + 1, y + 13, 9, 9);
	    c.fillOval (x + 40, y + 13, 9, 9);
	}

	if (what == 3) //Goblin, F
	{
	    c.setColor (dread);
	    c.fillOval (x + 15, y - 18, 20, 8);
	    c.setColor (purple);
	    c.fillArc (x - 20, y - 15, 60, 70, 260, 190);
	    c.fillArc (x + 10, y - 15, 60, 70, 90, 190);
	    c.setColor (Color.black);
	    c.drawArc (x + 10, y - 15, 60, 70, 110, 170);
	    c.setColor (lgtbrown);
	    c.fillRect (x + 10, y, 30, 20);
	    c.setColor (purple);
	    c.fillArc (x + 1, y - 15, 18, 20, 90, 90);
	    c.fillArc (x + 31, y - 15, 18, 20, 0, 90);
	    c.setColor (scale);
	    c.fillRect (x + 2, y - 5, 5, 20);
	    c.fillRect (x + 43, y - 5, 5, 20);
	    c.fillOval (x + 1, y + 12, 7, 7);
	    c.fillOval (x + 42, y + 12, 7, 7);
	    c.setColor (scale);
	    c.fillArc (x + 15, y - 42, 20, 30, 180, 180);
	    c.fillArc (x + 15, y - 37, 20, 20, 0, 180);
	    int[] x1 = {x + 10, x + 25, x + 40, x + 30, x + 20};
	    int[] y1 = {y - 37, y - 30, y - 37, y - 20, y - 20};
	    c.fillPolygon (x1, y1, 5);
	    c.setColor (Color.white);
	    c.fillOval (x + 17, y - 30, 6, 3);
	    c.fillOval (x + 27, y - 30, 6, 3);
	    c.setColor (Color.black);
	    c.fillOval (x + 19, y - 30, 3, 3);
	    c.fillOval (x + 28, y - 30, 3, 3);
	    c.drawLine (x + 23, y - 23, x + 25, y - 25);
	    c.drawLine (x + 27, y - 23, x + 25, y - 25);
	    c.drawArc (x + 10, y - 48, 30, 30, 260, 20);
	}

	if (what == 4) //Goblin, M
	{
	}

	if (what == 5) //Dryad, M
	{
	    c.setColor (brown);
	    int[] x1 = {x + 10, x + 25, x + 40, x + 30, x + 20};
	    int[] y1 = {y - 27, y - 20, y - 27, y - 40, y - 40};
	    c.fillPolygon (x1, y1, 5);
	    c.setColor (hazel);
	    c.fillOval (x + 15, y - 18, 20, 8);
	    c.setColor (fur);
	    c.fillArc (x - 20, y - 15, 60, 70, 0, 90);
	    c.fillArc (x + 10, y - 15, 60, 70, 90, 90);
	    c.setColor (Color.black);
	    c.drawArc (x + 10, y - 15, 60, 70, 110, 70);
	    c.setColor (fur);
	    c.fillArc (x + 2, y - 15, 18, 20, 90, 90);
	    c.fillArc (x + 30, y - 15, 18, 20, 0, 90);
	    c.setColor (hazel);
	    c.fillRect (x + 3, y - 5, 5, 20);
	    c.fillRect (x + 42, y - 5, 5, 20);
	    c.fillOval (x + 2, y + 12, 7, 7);
	    c.fillOval (x + 41, y + 12, 7, 7);
	    c.fillArc (x + 15, y - 42, 20, 30, 180, 180);
	    c.fillArc (x + 15, y - 37, 20, 20, 0, 180);
	    c.setColor (brown);
	    c.fillRect (x + 20, y - 38, 10, 4);
	    c.setColor (Color.white);
	    c.fillOval (x + 17, y - 30, 6, 3);
	    c.fillOval (x + 27, y - 30, 6, 3);
	    c.setColor (Color.black);
	    c.fillOval (x + 19, y - 30, 3, 3);
	    c.fillOval (x + 28, y - 30, 3, 3);
	    c.drawLine (x + 23, y - 23, x + 25, y - 25);
	    c.drawLine (x + 27, y - 23, x + 25, y - 25);
	    c.drawArc (x + 10, y - 48, 30, 30, 260, 20);
	    c.setColor (Color.black);
	    int[] x2 = {x + 7, x + 10, x + 40, x + 43, x + 30, x + 25, x + 20};
	    int[] y2 = {y + 50, y + 13, y + 13, y + 50, y + 50, y + 23, y + 50};
	    c.fillPolygon (x2, y2, 7);
	    c.setColor (hazel);
	    c.fillArc (x + 8, y + 45, 13, 10, 0, 180);
	    c.fillArc (x + 30, y + 45, 13, 10, 0, 180);
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
	9: crop
	*/

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
	if (what == 9) //crop
	{
	    c.setColor (leave);
	    c.fillRect (x + 1, y, 5, 15);
	    c.setColor (Color.yellow);
	    c.fillOval (x, y - 10, 7, 10);
	}
    }


    public void drawPlayer (int who, int x, int y)
    {
	x = x * 50;
	y = y * 50;

	//basic
	c.setColor (Color.yellow);
	c.fillOval (x, y, 50, 50);
    }


    public void miniMap (int px, int py, int pz, int x, int y, int z)
    {
	c.setColor (Color.black);
	for (int lx = 1 ; lx <= px ; lx++)
	{
	    for (int ly = 1 ; ly <= py ; ly++)
	    {
		c.drawRect (700 + lx * 20, ly * 20, 20, 20);
	    }
	}
	c.setColor (Color.blue);
	c.fillRect (700 + (x + 1) * 20, (y + 1) * 20, 20, 20);
    }


    public void sparkle (int x, int y)
    {
	c.setColor (Color.yellow);
	/*
	for (int i = 0 ; i < 6 ; i++)
	{
	    c.fillStar ((int) (Math.random () * 45) + (x * 50), (int) (Math.random () * 45) + (y * 50), 5, 5);
	}
	*/
	c.fillStar (x * 50, y * 50 + 40, 10, 10);
    }
}
