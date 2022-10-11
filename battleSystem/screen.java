package battleSystem;

import java.awt.*;
import hsa.Console;

public class screen
{
    static Console c;

    Font small;
    Font nerm;

    String[] display;

    int bg;

    int[] [] hurtbox;

    Color purple = new Color (201, 48, 186);
    Color brown = new Color (128, 64, 0);
    Color beige = new Color (255, 225, 196);
    Color drkpurple = new Color (155, 0, 155);
    Color lgtbrown = new Color (217, 108, 0);
    Color drkgray = new Color (122, 122, 122);
    Color dread = new Color (62, 99, 39);
    Color sheen = new Color (80, 80, 71);
    Color teal = new Color (162, 255, 255);
    Color fur = new Color (230, 230, 230);
    Color hazel = new Color (170, 85, 0);
    Color bronze = new Color (195, 126, 56);
    Color scale = new Color (64, 182, 39);
    Color lgtGreen = new Color (151, 228, 135);
    Color wChest = new Color (244, 204, 227);

    public screen (Console ne, int background)
    {
	c = ne;
	bg = background;

	nerm = new Font ("calibri", 0, 12);
	small = new Font ("calibri", 0, 9);

	display = new String [10];
	for (int i = 0 ; i < display.length ; i++)
	{
	    display [i] = "";
	}
	initialize ();
    }


    public void sPrint (String text)
    {
	for (int i = 0 ; i < display.length - 1 ; i++)
	{
	    display [i] = display [i + 1];
	}
	display [display.length - 1] = text;

	showText ();

	delay (50);
    }


    public void showText ()
    {
	c.setFont (nerm);
	c.setColor (Color.white);
	c.fillRect (1150, 0, 400, 400);
	c.setColor (Color.black);
	for (int i = 0 ; i < display.length ; i++)
	{
	    //c.setCursor (2 * i, 150);
	    //c.print (display [i]);
	    c.drawString (display [i], 1150, 30 * i + 30);
	}
    }


    public void graphic (fighter A, fighter B)
    {
	c.clear ();

	background ();

	showText ();

	healthBar (A.getHealth (), A.getHealthCap (), 1, A.getName (), A.getLevel ());
	healthBar (B.getHealth (), B.getHealthCap (), 2, B.getName (), B.getLevel ());

	manaBar (A.getMana (), A.getManaCap (), 1);
	manaBar (B.getMana (), B.getManaCap (), 2);

	effectDisplay (A, 1);
	effectDisplay (B, 2);

	resDisplay (A, 1);
	resDisplay (B, 2);

	show (A, 1);
	show (B, 2);
    }


    public void healthBar (int current, int max, int position, String name, int level)
    {
	c.setColor (Color.red);
	c.fillRect (500 * position - 250, 600, 140, 20);
	c.setColor (Color.green);
	c.fillRect (500 * position - 250, 600, (int) (140 * ((double) current / (double) max)), 20);
	c.setColor (Color.black);
	c.setFont (nerm);
	c.drawString (name + "  lvl." + level, 500 * position - 240, 590);
    }


    public void manaBar (int current, int max, int position)
    {
	c.setColor (Color.black);
	c.fillRect (500 * position - 250, 620, 140, 10);
	c.setColor (Color.blue);
	c.fillRect (500 * position - 250, 620, (int) (140 * ((double) current / (double) max)), 10);
    }


    public void effectDisplay (fighter who, int position)
    {
	int[] effects = who.getEffects ();
	int num = 0;
	for (int i = 0 ; i < effects.length ; i++)
	{
	    if (effects [i] != 0)
	    {
		icon (i, 500 * position - 250 + num * 20, 660, effects [i]);
		num++;
	    }
	}
    }


    public void showOptions (int[] options, String[] names)
    {
	c.setFont (nerm);
	c.setColor (Color.black);
	for (int i = 0 ; i < options.length ; i++)
	{
	    c.drawString (i + ": " + names [options [i]], 20, 40 * i + 20);
	}
    }


    public void resDisplay (fighter who, int position)
    {
	int x = 500 * position - 250;
	int y = 630;
	c.setFont (small);
	double[] res = who.getRes ();
	//pierce
	c.setColor (lgtbrown);
	int[] x1 = {x, x + 8, x + 6, x + 20, x + 12, x + 10, x + 2};
	int[] y1 = {y + 18, y + 10, y + 8, y, y + 14, y + 12, y + 20};
	c.fillPolygon (x1, y1, 7);
	c.setColor (Color.black);
	c.drawString ("" + res [1], x, y + 27);
	x += 20;
	//bludgeon
	c.setColor (brown);
	c.fillOval (x + 3, y, 14, 14);
	c.fillRect (x + 8, y + 10, 4, 10);
	c.setColor (Color.black);
	c.drawString ("" + res [2], x, y + 27);
	x += 20;
	//slash
	c.setColor (Color.red);
	int[] x2 = {x, x + 9, x + 20, x + 11};
	int[] y2 = {y + 20, y + 9, y, y + 11};
	c.fillPolygon (x2, y2, 4);
	c.setColor (Color.black);
	c.drawString ("" + res [3], x, y + 27);
	x += 20;
	//shock
	c.setColor (Color.yellow);
	int[] x3 = {x + 10, x + 11, x + 7, x + 10, x + 9, x + 13};
	int[] y3 = {y + 20, y + 11, y + 13, y, y + 9, y + 7};
	c.fillPolygon (x3, y3, 6);
	c.setColor (Color.black);
	c.drawString ("" + res [4], x, y + 27);
	x += 20;
	//burn
	c.setColor (Color.red);
	int[] x4 = {x, x + 3, x + 6, x + 10, x + 14, x + 17, x + 20};
	int[] y4 = {y + 10, y + 5, y + 7, y, y + 7, y + 5, y + 10};
	c.fillPolygon (x4, y4, 7);
	c.fillArc (x, y, 20, 20, 180, 180);
	c.setColor (Color.black);
	c.drawString ("" + res [5], x, y + 27);
	x += 20;
	//blast
	c.setColor (Color.red);
	int[] x5 = {x, x + 5, x, x + 10, x + 20, x + 15, x + 20, x + 10};
	int[] y5 = {y + 20, y + 10, y, y + 5, y, y + 10, y + 20, y + 15};
	c.fillPolygon (x5, y5, 8);
	c.setColor (Color.black);
	c.drawString ("" + res [6], x, y + 27);
	x += 20;
	//poison
	c.setColor (Color.green);
	c.fillOval (x + 5, y + 5, 10, 10);
	c.fillOval (x, y, 4, 4);
	c.fillOval (x, y + 12, 4, 4);
	c.fillOval (x + 16, y + 15, 4, 4);
	c.setColor (Color.black);
	c.drawString ("" + res [7], x, y + 27);
    }


    public void icon (int what, int x, int y, int quantity)
    {
	if (what == 1) //vulnerability
	{
	    c.setColor (Color.blue);
	    c.fillOval (x, y, 20, 20);
	    c.setColor (Color.white);
	    c.fillOval (x + 3, y + 3, 14, 14);
	    c.setColor (Color.blue);
	    c.fillOval (x + 8, y + 8, 4, 4);
	}
	if (what == 2) //burning
	{
	    c.setColor (Color.red);
	    int[] x1 = {x, x + 3, x + 6, x + 10, x + 14, x + 17, x + 20};
	    int[] y1 = {y + 10, y + 5, y + 7, y, y + 7, y + 5, y + 10};
	    c.fillPolygon (x1, y1, 7);
	    c.fillArc (x, y, 20, 20, 180, 180);
	}
	if (what == 3) //resistance
	{
	    c.setColor (Color.gray);
	    c.fillArc (x, y - 20, 20, 40, 180, 180);
	    c.setColor (brown);
	    c.fillArc (x + 3, y - 14, 14, 31, 180, 180);
	}
	if (what == 4) //bleeding
	{
	    c.setColor (Color.red);
	    c.fillOval (x, y + 5, 5, 5);
	    c.fillOval (x + 6, y + 15, 5, 5);
	    c.fillOval (x + 14, y + 7, 5, 5);
	}
	if (what == 5) //nausia
	{
	    c.setColor (dread);
	    c.fillOval (x, y, 20, 20);
	    c.setColor (Color.green);
	    c.fillArc (x + 5, y + 2, 10, 10, 270, 270);
	    c.fillOval (x + 7, y + 15, 6, 6);
	    c.setColor (dread);
	    c.fillOval (x + 7, y + 4, 6, 6);
	}
	if (what == 6) //poison
	{
	    c.setColor (Color.green);
	    c.fillOval (x, y + 5, 5, 5);
	    c.fillOval (x + 6, y + 15, 5, 5);
	    c.fillOval (x + 14, y + 7, 5, 5);
	}
	if (what == 7) //airborne
	{
	    c.setColor (Color.blue);
	    int[] x1 = {x + 5, x + 5, x, x + 10, x + 20, x + 15, x + 15};
	    int[] y1 = {y + 20, y + 10, y + 10, y, y + 10, y + 10, y + 20};
	    c.fillPolygon (x1, y1, 7);
	}
	if (what == 8) //regeneration
	{
	    c.setColor (Color.green);
	    c.fillRect (x + 5, y, 10, 20);
	    c.fillRect (x, y + 5, 20, 10);
	}
	if (what == 9) //thorns
	{
	    c.setColor (lgtGreen);
	    int[] x1 = {x, x + 7, x + 10, x + 13, x + 20, x + 13, x + 10, x + 7};
	    int[] y1 = {y + 10, y + 7, y, y + 7, y + 10, y + 13, y + 20, y + 13};
	    c.fillPolygon (x1, y1, 8);
	}
	if (what == 10) //venom
	{
	    c.setColor (drkpurple);
	    c.fillOval (x + 3, y + 6, 14, 14);
	    int[] x1 = {x + 5, x + 10, x + 15};
	    int[] y1 = {y + 8, y, y + 8};
	    c.fillPolygon (x1, y1, 3);
	}

	c.setColor (Color.black);
	c.setFont (nerm);
	c.drawString ("" + quantity, x + 3, y + 30);
    }


    public void background ()
    {
	if (bg == 1) //meadow
	{
	    c.setColor (bronze);
	    c.fillRect (200, 400, 720, 80);
	    c.setColor (Color.green);
	    c.fillRect (200, 280, 720, 120);
	    c.setColor (Color.blue);
	    c.fillRect (200, 0, 720, 280);
	}
    }


    public void show (fighter who, int pos)
    {
	int ID = who.getID ();
	int weap = who.getWeap ();
	int x = 0;
	int y = 200;
	if (pos == 1)
	{
	    x = 250;
	}
	if (pos == 2)
	{
	    x = 750;
	}

	//ground is y+210 to y+250. Move sprites up and down to level out hight

	if (ID == 0) //blank?
	{
	    c.setColor (Color.black);
	    c.fillOval (x, y + 90, 120, 120);
	    c.setColor (Color.white);
	    c.fillArc (x + 30, y + 100, 60, 60, 270, 270);
	    c.fillOval (x + 45, y + 170, 30, 30);
	    c.setColor (Color.black);
	    c.fillOval (x + 45, y + 115, 30, 30);
	    c.setColor (Color.white);
	    c.fillOval (x + 30, y + 123, 15, 15);
	    c.fillOval (x + 53, y + 145, 15, 15);
	}

	if (ID == 1) //dryad player
	{
	    c.setColor (hazel); //face
	    c.fillOval (x + 40, y + 50, 40, 40);
	    c.setColor (brown);
	    int[] x2 = {x + 40, x + 39, x + 34, x + 50, x + 60, x + 80, x + 60, x + 55, x + 55};
	    int[] y2 = {y + 70, y + 67, y + 65, y + 50, y + 45, y + 55, y + 65, y + 80, y + 90};
	    c.fillPolygon (x2, y2, 9);
	    c.setColor (hazel);
	    c.fillOval (x + 52, y + 55, 15, 20);
	    c.setColor (Color.pink);
	    c.fillOval (x + 56, y + 60, 7, 10);
	    c.setColor (Color.white);
	    c.fillOval (x + 65, y + 64, 12, 7);
	    c.setColor (Color.black);
	    c.fillOval (x + 72, y + 65, 5, 5);
	    c.drawLine (x + 65, y + 82, x + 72, y + 83);
	    c.drawLine (x + 76, y + 72, x + 75, y + 76);
	    c.setColor (brown); //left hand
	    int[] x6 = {x + 42, x + 65, x + 100, x + 105, x + 62, x + 37};
	    int[] y6 = {y + 85, y + 110, y + 105, y + 112, y + 122, y + 95};
	    c.fillPolygon (x6, y6, 6);
	    c.setColor (hazel);
	    c.fillOval (x + 100, y + 98, 18, 18);
	    drawWeapon (weap, x + 109, y + 107);
	    c.setColor (hazel);
	    c.fillArc (x + 95, y + 100, 15, 5, 270, 180);
	    c.setColor (bronze); //shirt
	    int[] x1 = {x + 40, x + 55, x + 53, x + 45, x + 10, x + 20};
	    int[] y1 = {y + 70, y + 90, y + 100, y + 150, y + 150, y + 90};
	    c.fillPolygon (x1, y1, 6);
	    c.setColor (brown); //tail
	    int[] x5 = {x + 15, x, x - 12, x - 18, x - 25, x - 30, x - 26, x - 24, x - 30, x - 33, x - 35, x - 29, x - 31, x - 35, x - 29, x - 20, x - 16, x - 10, x + 3, x + 20};
	    int[] y5 = {y + 150, y + 170, y + 180, y + 181, y + 178, y + 160, y + 140, y + 120, y + 100, y + 100, y + 105, y + 120, y + 140, y + 160, y + 181, y + 187, y + 187, y + 184, y + 172, y + 151};
	    c.fillPolygon (x5, y5, 20);
	    c.setColor (brown); //right arm
	    int[] x4 = {x + 35, x + 5, x - 5, x + 5, x + 15, x + 42};
	    int[] y4 = {y + 80, y + 110, y + 145, y + 150, y + 120, y + 90};
	    c.fillPolygon (x4, y4, 6);
	    c.fillOval (x + 32, y + 78, 15, 15);
	    c.setColor (hazel);
	    c.fillOval (x - 10, y + 145, 18, 18);
	    c.setColor (Color.black); //pants
	    int[] x3 = {x + 45, x + 70, x + 53, x + 37, x + 54, x + 32, x + 22, x, x - 16, x + 6, x + 10};
	    int[] y3 = {y + 150, y + 190, y + 230, y + 230, y + 190, y + 170, y + 190, y + 230, y + 230, y + 185, y + 150};
	    c.fillPolygon (x3, y3, 11);
	    c.setColor (hazel); //feet
	    c.fillRect (x + 39, y + 230, 12, 8);
	    c.fillArc (x + 39, y + 233, 6, 4, 180, 90);
	    c.fillArc (x + 16, y + 230, 50, 20, 0, 90);
	    c.fillRect (x - 14, y + 230, 12, 8);
	    c.fillArc (x - 14, y + 233, 6, 4, 180, 90);
	    c.fillArc (x - 37, y + 230, 50, 20, 0, 90);
	}

	if (ID == 2 || ID == 3) //marapet player
	{
	    if (pos == 1) //facing right
	    {
		drawWeapon (weap, x + 122, y + 83);
		if (ID == 2) //face
		    c.setColor (hazel);
		else
		    c.setColor (fur);
		c.fillOval (x + 50, y + 5, 40, 40);
		c.fillOval (x + 45, y + 25, 27, 50);
		int[] x3 = {x + 55, x + 45, x + 64};
		int[] y3 = {y + 20, y, y + 7};
		c.fillPolygon (x3, y3, 3);
		c.fillRect (x + 70, y + 25, 22, 17);
		c.fillArc (x + 86, y + 29, 12, 12, 270, 90);
		c.fillRect (x + 92, y + 30, 6, 5);
		c.setColor (Color.black);
		c.fillArc (x + 86, y + 25, 12, 12, 0, 90);
		c.drawLine (x + 86, y + 35, x + 96, y + 35);
		c.setColor (Color.white);
		c.fillOval (x + 70, y + 15, 15, 10);
		c.setColor (Color.black);
		c.fillOval (x + 80, y + 18, 5, 5);
		c.setColor (bronze);
		c.drawLine (x + 70, y + 12, x + 80, y + 12);
		c.setColor (bronze); //shirt
		int[] x1 = {x + 40, x + 48, x + 72, x + 77, x + 76, x + 70, x + 40};
		int[] y1 = {y + 50, y + 30, y + 50, y + 70, y + 78, y + 120, y + 120};
		c.fillPolygon (x1, y1, 7);
		c.setColor (brown); //pants
		int[] x2 = {x + 40, x + 70, x + 90, x + 80, x + 60, x + 70, x + 55, x + 50, x + 30, x + 10, x + 30};
		int[] y2 = {y + 120, y + 120, y + 170, y + 220, y + 220, y + 170, y + 150, y + 175, y + 220, y + 220, y + 175};
		c.fillPolygon (x2, y2, 11);
		if (ID == 2) //feet
		    c.setColor (hazel);
		else
		    c.setColor (fur);
		c.fillRect (x + 10, y + 220, 16, 5);
		c.fillArc (x + 10, y + 210, 10, 25, 180, 90);
		c.fillArc (x - 15, y + 220, 60, 30, 0, 90);
		c.fillRect (x + 60, y + 220, 16, 5);
		c.fillArc (x + 60, y + 210, 10, 25, 180, 90);
		c.fillArc (x + 35, y + 220, 60, 30, 0, 90);
		c.setColor (brown);
		c.fillRect (x + 10, y + 235, 40, 5);
		c.fillRect (x + 30, y + 222, 5, 13);
		c.fillRect (x + 60, y + 235, 40, 5);
		c.fillRect (x + 80, y + 222, 5, 13);
		if (ID == 2) //tail
		    c.setColor (hazel);
		else
		    c.setColor (fur);
		int[] x4 = {x + 42, x + 50, x + 25, x, x + 22};
		int[] y4 = {y + 122, y + 140, y + 180, y + 200, y + 170};
		c.fillPolygon (x4, y4, 5);
		if (ID == 2) //arms
		    c.setColor (hazel);
		else
		    c.setColor (fur);
		int[] x5 = {x + 50, x + 80, x + 119, x + 114, x + 85, x + 59};
		int[] y5 = {y + 60, y + 95, y + 90, y + 80, y + 80, y + 50};
		c.fillPolygon (x5, y5, 6);
		c.fillOval (x + 112, y + 73, 20, 20);
		c.fillOval (x + 50, y + 50, 12, 12);
	    }
	}

	if (ID == 4) //goblin player
	{
	}

	if (ID == 5) //kobold player
	{
	}

	if (ID == 6) //wyvern player
	{
	    c.setColor (fur); //face
	    c.fillOval (x + 50, y + 5, 40, 40);
	    int[] x3 = {x + 55, x + 45, x + 64};
	    int[] y3 = {y + 20, y, y + 5};
	    c.fillPolygon (x3, y3, 3);
	    c.fillRect (x + 70, y + 25, 35, 12);
	    c.fillOval (x + 95, y + 32, 10, 10);
	    c.fillOval (x + 99, y + 20, 6, 10);
	    c.fillRect (x + 70, y + 37, 30, 5);
	    //c.fillRect (x + 70, y + 25, 22, 17);
	    //c.fillArc (x + 86, y + 29, 12, 12, 270, 90);
	    //c.fillRect (x + 92, y + 30, 6, 5);
	    c.setColor (Color.black);
	    c.drawLine (x + 95, y + 35, x + 105, y + 35);
	    c.setColor (Color.white);
	    c.fillOval (x + 70, y + 15, 15, 10);
	    c.setColor (Color.black);
	    c.fillOval (x + 80, y + 18, 5, 5);
	    c.setColor (bronze);
	    c.drawLine (x + 70, y + 12, x + 80, y + 12);
	    c.setColor (fur); //body
	    int[] x1 = {x + 75, x + 60, x + 50, x + 50, x + 55, x + 65, x + 67, x + 60, x + 35, x + 20, x - 40 /**/, x + 15, x + 30, x + 30, x + 20, x + 15, x + 13, x + 13, x + 18, x + 26, x + 40, x + 55};
	    int[] y1 = {y + 42, y + 60, y + 85, y + 110, y + 135, y + 155, y + 180, y + 200, y + 225, y + 235, y + 235 /**/, y + 210, y + 190, y + 170, y + 150, y + 130, y + 110, y + 90, y + 70, y + 50, y + 30, y + 10};
	    c.fillPolygon (x1, y1, 22);
	    c.setColor (wChest);
	    int[] x4 = {x + 75, x + 60, x + 50, x + 50, x + 55, x + 65, x + 67, x + 60, x + 35, x + 20, x + 30, x + 53, x + 60, x + 55, x + 45, x + 40, x + 40, x + 53};
	    int[] y4 = {y + 42, y + 60, y + 85, y + 110, y + 135, y + 155, y + 180, y + 200, y + 225, y + 235, y + 225, y + 200, y + 180, y + 155, y + 135, y + 110, y + 85, y + 60};
	    c.fillPolygon (x4, y4, 18);
	    c.setColor (bronze);
	    int[] x2 = {x + 45, x + 55, x + 60, x + 50, x + 37, x + 22, x + 12, x + 15, x + 23};
	    int[] y2 = {y + 20, y + 40, y + 60, y + 70, y + 80, y + 90, y + 90, y + 70, y + 50};
	    c.fillPolygon (x2, y2, 9);
	    c.setColor (fur); //legs
	    int[] x5 = {x + 45, x + 50, x + 47, x + 50, x + 82, x + 77, x + 65, x + 68, x + 55};
	    int[] y5 = {y + 185, y + 210, y + 232, y + 235, y + 235, y + 230, y + 225, y + 203, y + 170};
	    c.fillPolygon (x5, y5, 9);
	    drawWeapon (weap, x + 97, y + 97); //arms
	    c.setColor (fur);
	    c.fillOval (x + 34, y + 42, 20, 20);
	    int[] x6 = {x + 37, x + 60, x + 90, x + 98, x + 68, x + 45};
	    int[] y6 = {y + 55, y + 88, y + 100, y + 95, y + 80, y + 50};
	    c.fillPolygon (x6, y6, 6);
	    c.fillOval (x + 87, y + 87, 20, 20);
	}

	if (ID == 7) //atlantean player
	{
	}

	if (ID == 8) //leon player
	{
	}

	if (ID == 9) //demon player
	{
	}

	if (ID == 10) //crop squirrel
	{
	    if (pos == 2)
	    {
		c.setColor (hazel); //body
		c.fillOval (x + 20, y + 180, 30, 20);
		c.fillOval (x + 35, y + 190, 50, 20);
		int[] x1 = {x + 75, x + 75, x + 85, x + 110, x + 120, x + 110, x + 94};
		int[] y1 = {y + 205, y + 195, y + 185, y + 170, y + 180, y + 190, y + 189};
		c.fillPolygon (x1, y1, 7);
		c.fillRect (x + 40, y + 200, 5, 14);
		c.fillRect (x + 47, y + 200, 5, 14);
		c.fillRect (x + 70, y + 200, 5, 14);
		c.fillRect (x + 77, y + 200, 5, 14);
		c.fillOval (x + 40, y + 177, 10, 10);
		c.setColor (Color.black);
		c.fillOval (x + 25, y + 184, 5, 5);
	    }
	}

	if (ID == 11) //wyric
	{
	    if (pos == 2)
	    {
		c.setColor (fur);
		c.fillArc (x - 62, y + 105, 20, 20, 170, 100);
		c.fillArc (x - 30, y + 205, 20, 20, 90, 90);
		c.setColor (scale);
		int[] x1 = {x, x + 60, x + 100, x + 170, x + 90, x + 50, x};
		int[] y1 = {y + 100, y + 110, y + 190, y + 210, y + 210, y + 130, y + 130};
		c.fillPolygon (x1, y1, 7);
		int[] x2 = {x, x - 20, x - 38, x - 60, x - 62, x - 35, x - 55, x - 50, x - 20, x};
		int[] y2 = {y + 100, y + 90, y + 100, y + 110, y + 114, y + 120, y + 135, y + 138, y + 133, y + 130};
		c.fillPolygon (x2, y2, 10);
		c.setColor (dread);
		int[] x3 = {x + 10, x, x - 20, x - 20, x + 5, x + 15};
		int[] y3 = {y + 110, y + 150, y + 205, y + 215, y + 152, y + 110};
		c.fillPolygon (x3, y3, 6);
		c.setColor (lgtGreen);
		c.fillArc (x - 95, y + 87, 200, 130, 256, 70);
		c.setColor (sheen);
		c.fillArc (x - 38, y + 100, 15, 8, 200, 180);
	    }
	}

	if (ID == 12) //treant twig
	{
	    c.setColor (brown);
	    int[] x1 = {x - 30, x, x + 10, x - 10, x + 12, x + 18, x + 22, x + 23, x + 10, x + 20, x};
	    int[] y1 = {y + 230, y + 200, y + 190, y + 175, y + 180, y + 150, y + 145, y + 180, y + 200, y + 230, y + 210};
	    c.fillPolygon (x1, y1, 11);
	    c.setColor (Color.black);
	    c.fillOval (x + 16, y + 155, 5, 5);
	    c.fillOval (x + 20, y + 160, 5, 5);
	    c.drawLine (x + 17, y + 166, x + 23, y + 169);
	    c.setColor (dread);
	    c.fillOval (x - 12, y + 163, 6, 15);
	}

	if (ID == 13) //woodrend
	{
	    if (pos == 2)
	    {
		c.setColor (fur);
		c.fillArc (x - 90, y + 205, 40, 10, 0, 180);
		c.setColor (brown);
		int[] x1 = {x - 70, x - 40, x + 60, x + 100, x + 170, x + 90, x + 50, x - 40, x - 70};
		int[] y1 = {y + 140, y + 130, y + 150, y + 190, y + 210, y + 210, y + 170, y + 180, y + 170};
		c.fillPolygon (x1, y1, 9);
		int[] x2 = {x - 50, x - 40, x - 50, x - 25, x - 15, x - 25};
		int[] y2 = {y + 170, y + 190, y + 210, y + 210, y + 190, y + 170};
		c.fillPolygon (x2, y2, 6);
		c.fillArc (x - 70, y + 200, 40, 20, 0, 180);
		int[] x3 = {x + 40, x + 30, x + 40, x + 55, x + 45, x + 55};
		int[] y3 = {y + 160, y + 185, y + 210, y + 210, y + 185, y + 160};
		c.fillPolygon (x3, y3, 6);
		c.fillArc (x + 25, y + 200, 30, 20, 0, 180);
		c.setColor (Color.white);
		int[] x5 = {x - 125, x - 122, x - 119};
		int[] y5 = {y + 153, y + 165, y + 155};
		c.fillPolygon (x5, y5, 3);
		c.setColor (brown);
		int[] x4 = {x - 70, x - 90, x - 108, x - 130, x - 132, x - 105, x - 125, x - 120, x - 90, x - 70};
		int[] y4 = {y + 140, y + 135, y + 140, y + 150, y + 154, y + 160, y + 165, y + 168, y + 169, y + 170};
		c.fillPolygon (x4, y4, 10);
		c.setColor (Color.black);
		c.fillOval (x - 100, y + 145, 10, 5);
		c.drawLine (x - 100, y + 143, x - 90, y + 140);
	    }
	}

	if (ID == 14) //bees
	{
	    for (int i = 0 ; i < 7 ; i++)
	    {
		int x1 = x + (int) (Math.random () * 200) - 100;
		int y1 = y + (int) (Math.random () * 200);
		addition (1, x1, y1);
	    }
	}
    }


    public void drawWeapon (int what, int x, int y)
    {
	if (what == 1) //basic cudgel
	{
	    c.setColor (brown);
	    c.fillArc (x - 10, y - 40, 20, 20, 0, 180);
	    int[] x1 = {x - 10, x - 3, x + 3, x + 10};
	    int[] y1 = {y - 30, y + 5, y + 5, y - 30};
	    c.fillPolygon (x1, y1, 4);
	}
    }


    public void animate (int what, fighter targ)
    {
	int x;
	int y = 200;
	int pos = targ.getPos ();
	if (pos == 1)
	{
	    x = 250;
	}


	else
	{
	    x = 750;
	}


	int[] off = hurtbox [targ.getID ()];
	x += off [0];
	y += off [1];

	if (what == 1) //bludgeon hit
	{
	    c.setColor (Color.red);
	    c.fillOval (x - 10, y - 10, 20, 20);
	    delay (200);
	    int[] x1 = {x - 40, x - 10, x + 5, x + 10, x + 40, x + 13, x, x - 7};
	    int[] y1 = {y, y - 10, y - 40, y - 8, y + 5, y + 6, y + 40, y + 11};
	    c.fillPolygon (x1, y1, 8);
	    delay (400);
	}


	if (what == 2) //bite
	{
	    c.setColor (sheen);
	    int[] x1 = {x - 30, x - 20, x - 10, x, x + 10, x + 20, x + 30, x + 30, x - 30};
	    int[] y1 = {y - 30, y - 20, y - 30, y - 20, y - 30, y - 20, y - 30, y - 40, y - 40};
	    int[] y2 = {y + 20, y + 30, y + 20, y + 30, y + 20, y + 30, y + 20, y + 40, y + 40};
	    c.fillPolygon (x1, y1, 9);
	    c.fillPolygon (x1, y2, 9);
	    delay (500);
	    int[] y3 = {y - 6, y + 4, y - 6, y + 4, y - 6, y + 4, y - 6, y - 40, y - 40};
	    int[] y4 = {y - 4, y + 6, y - 4, y + 6, y - 4, y + 6, y - 4, y + 40, y + 40};
	    c.fillPolygon (x1, y3, 9);
	    c.fillPolygon (x1, y4, 9);
	    delay (400);
	}


	if (what == 3) //powder
	{
	    c.setColor (dread);
	    for (int q = 0 ; q < 3 ; q++)
	    {
		for (int i = 0 ; i < 6 ; i++)
		{
		    c.fillOval (x + (int) (Math.random () * 120) - 60, y + (int) (Math.random () * 40) - (30 * (3 - q)), 6, 6);
		}
		delay (200);
	    }
	}


	if (what == 4) //stab
	{
	    c.setColor (Color.red);
	    if (pos == 1)
	    {
		c.fillRect (x + 40, y - 3, 30, 6);
		delay (150);
		c.fillRect (x, y - 1, 40, 2);
		delay (400);
	    }
	    else
	    {
		c.fillRect (x - 70, y - 3, 30, 6);
		delay (150);
		c.fillRect (x - 40, y - 1, 40, 2);
		delay (400);
	    }
	}


	if (what == 5) //hazerdoues scratch
	{
	    c.setColor (dread);
	    c.drawLine (x, y - 50, x - 25, y - 25);
	    c.drawLine (x + 25, y - 25, x, y);
	    c.drawLine (x + 50, y, x + 25, y + 25);
	    delay (150);
	    c.drawLine (x - 25, y - 25, x - 50, y);
	    c.drawLine (x, y, x - 25, y + 25);
	    c.drawLine (x + 25, y + 25, x, y + 50);
	    delay (300);
	}

	if (what == 6) //rend
	{
	    c.setColor (Color.red);
	    c.drawLine (x, y - 20, x + 20, y - 60);
	    delay (150);
	    c.drawLine (x, y - 20, x, y + 20);
	    delay (150);
	    c.drawLine (x, y + 20, x + 20, y + 60);
	    delay (300);
	}

	if (what == 7) //disarm
	{
	    c.setColor (Color.blue);
	    c.fillOval (x - 10, y - 10, 20, 20);
	    delay (150);
	    c.drawOval (x - 20, y - 20, 40, 40);
	    delay (150);
	    c.drawOval (x - 30, y - 30, 60, 60);
	    delay (250);
	    c.setColor (Color.red);
	    c.fillOval (x - 10, y - 10, 20, 20);
	    delay (250);
	}
    }


    public void addition (int what, int x, int y)
    {
	if (what == 1) //bee
	{
	    c.setColor (Color.yellow);
	    c.fillOval (x, y, 15, 10);
	    c.setColor (Color.black);
	    c.drawLine (x + 5, y + 2, x + 5, y + 8);
	    c.drawLine (x + 10, y + 2, x + 10, y + 8);
	    c.fillOval (x + 1, y + 4, 2, 2);
	    c.setColor (teal);
	    c.fillOval (x + 7, y - 2, 6, 3);
	}
    }


    public void initialize ()
    {
	hurtbox = new int [200] [2]; //x, y
	for (int i = 0 ; i < hurtbox.length ; i++)
	{
	    hurtbox [i] [0] = 60;
	    hurtbox [i] [1] = 100;
	}


	hurtbox [1] [0] = 30; //dryad player
	hurtbox [1] [1] = 120;
	//marapet players use standard
	hurtbox [6] [0] = 50; //wyvern player
	hurtbox [6] [1] = 90;
	hurtbox [10] [0] = 60; //crop squirrel
	hurtbox [10] [1] = 200;
	hurtbox [12] [0] = 5; //treant twig
	hurtbox [12] [1] = 190;
	hurtbox [13] [0] = -30; //woodrend
	hurtbox [13] [1] = 150;
	hurtbox [14] [0] = 0; //bees
	hurtbox [14] [1] = 100;
    }


    public int charGet ()
    {
	char select;
	while (true)
	{
	    select = c.getChar ();
	    if (select == '0' || select == '1' || select == '2' || select == '3' || select == '4' || select == '5' || select == '6' || select == '7' || select == '8')
	    {
		return (((int) (select)) - 48);
	    }
	}
    }


    public static void delay (int millisecs)
    {
	try
	{
	    Thread.currentThread ().sleep (millisecs);
	}


	catch (InterruptedException e)
	{
	}
    }
}
