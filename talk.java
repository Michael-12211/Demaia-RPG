import java.awt.*;
import java.io.*;
import java.util.*;
import hsa.Console;

class talk
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
    static Color wChest = new Color (244, 204, 227);
    static Color scale = new Color (64, 182, 39);

    static Font nerm;

    Console c;

    public talk (Console con)
    {
	c = con;

	nerm = new Font ("calibri", 0, 20);
    }


    public void tPrint (int who, int ex, String text)
    {
	drawFace (730, 250, who, ex);
	c.setColor (Color.white);
	c.fillRect (850, 250, 700, 200);
	c.setColor (Color.black);
	c.setFont (nerm);
	c.drawString (text, 850, 300);
	c.getChar ();
    }


    public void drawFace (int x, int y, int who, int ex)
    {
	/*
	expression codes
	0: expressionless
	1: happy
	2: unsure
	3: angry
	4: surprised
	5: disapointed
	6: Eyes closed
	7: concerned
	8: eye roll
	9: confident
	*/
	c.setColor (Color.black);
	c.fillRect (x, y, 100, 100);
	if (who == 0) //disembodied
	{
	    c.setColor (Color.yellow);
	    int[] x1 = {x, x + 35, x + 50, x + 65, x + 100, x + 65, x + 50, x + 35};
	    int[] y1 = {y + 50, y + 35, y, y + 35, y + 50, y + 65, y + 100, y + 65};
	    c.fillPolygon (x1, y1, 8);
	}
	if (who > 0 & who < 10) //player
	{
	    if (who == 1) //dryad player
	    {
		c.setColor (bronze);
		c.fillArc (x + 10, y + 70, 30, 60, 0, 180);
		c.fillArc (x + 60, y + 70, 30, 60, 0, 180);
		c.fillRect (x + 30, y + 60, 40, 40);
		c.setColor (hazel);
		c.fillOval (x + 15, y + 10, 70, 80);
		c.setColor (brown);
		int[] x1 = {x + 30, x + 27, x + 20, x + 22, x + 25, x + 40, x + 60, x + 55, x + 80, x + 82, x + 78, x + 78, x + 82, x + 88, x + 85, x + 70, x + 50, x + 30, x + 18, x + 13, x + 15, x + 20, x + 26};
		int[] y1 = {y + 80, y + 70, y + 60, y + 40, y + 30, y + 25, y + 28, y + 24, y + 30, y + 40, y + 60, y + 70, y + 80, y + 50, y + 30, y + 14, y + 8, y + 14, y + 30, y + 50, y + 60, y + 78, y + 82};
		c.fillPolygon (x1, y1, 23);
		c.setColor (hazel);
		c.fillOval (x + 15, y + 20, 20, 30);
		c.fillOval (x + 65, y + 20, 20, 30);
		c.setColor (Color.black);
		c.drawLine (x + 48, y + 52, x + 46, y + 56);
		c.drawLine (x + 52, y + 52, x + 54, y + 56);
	    }
	    if (who == 2 || who == 3) //marapet player
	    {
		c.setColor (bronze);
		c.fillArc (x + 10, y + 70, 30, 60, 0, 180);
		c.fillArc (x + 60, y + 70, 30, 60, 0, 180);
		c.fillRect (x + 30, y + 60, 40, 40);
		if (who == 2)
		    c.setColor (hazel);
		else
		    c.setColor (fur);
		c.fillArc (x + 15, y + 15, 70, 70, 0, 180);
		c.fillArc (x + 15, y + 5, 140, 90, 180, 50);
		c.fillArc (x - 55, y + 5, 140, 90, 310, 50);
		c.fillArc (x + 10, y + 5, 80, 80, 250, 40);
		int[] x1 = {x + 19, x + 5, x + 32};
		int[] x2 = {x + 81, x + 95, x + 68};
		int[] y1 = {y + 35, y + 10, y + 20};
		c.fillPolygon (x1, y1, 3);
		c.fillPolygon (x2, y1, 3);
		c.setColor (Color.black);
		c.drawArc (x, y + 50, 100, 100, 70, 40);
		c.fillOval (x + 45, y + 55, 10, 5);
	    }
	    if (who == 6) //wyvern player
	    {
		c.setColor (fur);
		c.fillArc (x + 15, y + 40, 70, 120, 0, 180);
		c.setColor (wChest);
		c.fillArc (x + 25, y + 40, 50, 120, 0, 180);
		c.setColor (fur);
		int[] x1 = {x + 12, x + 50, x + 88, x + 60, x + 40};
		int[] y1 = {y + 16, y + 26, y + 16, y + 80, y + 80};
		c.fillPolygon (x1, y1, 5);
		c.fillOval (x + 15, y + 20, 70, 55);
		c.fillRect (x + 30, y + 50, 40, 30);
		c.setColor (Color.black);
		c.drawArc (x + 30, y + 50, 15, 15, 0, 180);
		c.drawArc (x + 55, y + 50, 15, 15, 0, 180);
		c.drawLine (x + 45, y + 58, x + 55, y + 58);
		c.setColor (bronze);
		c.fillArc (x + 12, y + 35, 76, 120, 160, 25);
		c.fillArc (x + 12, y + 35, 76, 120, 355, 25);
		c.fillArc (x - 14, y + 90, 64, 10, 270, 90);
		c.fillArc (x + 50, y + 90, 64, 10, 180, 90);
	    }


	    if (ex == 0) //expressionless
	    {
		c.setColor (Color.white);
		c.fillOval (x + 25, y + 35, 18, 8);
		c.fillOval (x + 57, y + 35, 18, 8);
		c.setColor (Color.black);
		c.fillOval (x + 31, y + 36, 6, 6);
		c.fillOval (x + 63, y + 36, 6, 6);
		c.drawLine (x + 40, y + 70, x + 60, y + 70);
		c.setColor (bronze);
		c.drawLine (x + 25, y + 31, x + 43, y + 31);
		c.drawLine (x + 57, y + 31, x + 75, y + 31);
	    }
	    if (ex == 1) //happy
	    {
		c.setColor (Color.white);
		c.fillOval (x + 25, y + 35, 18, 8);
		c.fillOval (x + 57, y + 35, 18, 8);
		c.setColor (Color.black);
		c.fillOval (x + 31, y + 36, 6, 6);
		c.fillOval (x + 63, y + 36, 6, 6);
		c.drawArc (x + 30, y + 50, 40, 20, 250, 40);
		c.setColor (bronze);
		c.drawLine (x + 25, y + 31, x + 43, y + 31);
		c.drawLine (x + 57, y + 31, x + 75, y + 31);
	    }
	    if (ex == 2) //unsure
	    {
		c.setColor (Color.white);
		c.fillOval (x + 25, y + 35, 18, 8);
		c.fillOval (x + 57, y + 35, 18, 8);
		c.setColor (Color.black);
		c.fillOval (x + 35, y + 36, 6, 6);
		c.fillOval (x + 67, y + 36, 6, 6);
		c.drawLine (x + 40, y + 70, x + 60, y + 70);
		c.setColor (bronze);
		c.drawLine (x + 25, y + 34, x + 43, y + 34);
		c.drawLine (x + 57, y + 34, x + 75, y + 34);
	    }
	    if (ex == 3) //angry
	    {
		c.setColor (Color.white);
		c.fillOval (x + 25, y + 35, 18, 8);
		c.fillOval (x + 57, y + 35, 18, 8);
		c.setColor (Color.black);
		c.fillOval (x + 31, y + 36, 6, 6);
		c.fillOval (x + 63, y + 36, 6, 6);
		c.drawArc (x, y + 75, 100, 80, 80, 20);
		c.setColor (bronze);
		c.drawLine (x + 25, y + 30, x + 43, y + 33);
		c.drawLine (x + 57, y + 33, x + 75, y + 30);
	    }
	    if (ex == 4) //surprise
	    {
		c.setColor (Color.white);
		c.fillOval (x + 25, y + 35, 18, 8);
		c.fillOval (x + 57, y + 35, 18, 8);
		c.setColor (Color.black);
		c.fillOval (x + 31, y + 36, 6, 6);
		c.fillOval (x + 63, y + 36, 6, 6);
		c.drawLine (x + 43, y + 70, x + 57, y + 70);
		c.setColor (bronze);
		c.drawLine (x + 25, y + 26, x + 43, y + 26);
		c.drawLine (x + 57, y + 26, x + 75, y + 26);
	    }
	    if (ex == 5) //disapointed
	    {
		c.setColor (Color.white);
		c.fillOval (x + 25, y + 35, 18, 8);
		c.fillOval (x + 57, y + 35, 18, 8);
		c.setColor (Color.black);
		c.fillOval (x + 31, y + 36, 6, 6);
		c.fillOval (x + 63, y + 36, 6, 6);
		c.drawLine (x + 40, y + 70, x + 60, y + 70);
		c.setColor (bronze);
		c.drawLine (x + 25, y + 34, x + 43, y + 34);
		c.drawLine (x + 57, y + 34, x + 75, y + 34);
	    }
	    if (ex == 6) //eyes closed
	    {
		c.setColor (Color.black);
		c.drawLine (x + 25, y + 40, x + 43, y + 40);
		c.drawLine (x + 57, y + 40, x + 75, y + 40);
		c.drawLine (x + 40, y + 70, x + 60, y + 70);
		c.setColor (bronze);
		c.drawLine (x + 25, y + 31, x + 43, y + 31);
		c.drawLine (x + 57, y + 31, x + 75, y + 31);
	    }
	    if (ex == 7) //concerrned
	    {
		c.setColor (Color.white);
		c.fillOval (x + 25, y + 35, 18, 8);
		c.fillOval (x + 57, y + 35, 18, 8);
		c.setColor (Color.black);
		c.fillOval (x + 31, y + 36, 6, 6);
		c.fillOval (x + 63, y + 36, 6, 6);
		c.drawLine (x + 40, y + 70, x + 60, y + 70);
		c.setColor (bronze);
		c.drawLine (x + 25, y + 34, x + 43, y + 28);
		c.drawLine (x + 57, y + 28, x + 75, y + 34);
	    }
	    if (ex == 8) //eye roll
	    {
		c.setColor (Color.white);
		c.fillOval (x + 25, y + 35, 18, 8);
		c.fillOval (x + 57, y + 35, 18, 8);
		c.setColor (Color.black);
		c.fillOval (x + 33, y + 35, 6, 6);
		c.fillOval (x + 65, y + 35, 6, 6);
		c.drawLine (x + 43, y + 70, x + 57, y + 70);
		c.setColor (bronze);
		c.drawLine (x + 25, y + 26, x + 43, y + 26);
		c.drawLine (x + 57, y + 26, x + 75, y + 26);
	    }
	    if (ex == 9) //confident
	    {
		c.setColor (Color.white);
		c.fillOval (x + 25, y + 35, 18, 8);
		c.fillOval (x + 57, y + 35, 18, 8);
		c.setColor (Color.black);
		c.fillOval (x + 31, y + 36, 6, 6);
		c.fillOval (x + 63, y + 36, 6, 6);
		c.drawArc (x + 30, y + 50, 40, 20, 250, 40);
		c.setColor (bronze);
		c.drawLine (x + 25, y + 27, x + 43, y + 31);
		c.drawLine (x + 57, y + 31, x + 75, y + 27);
	    }
	}

	if (who == 10) //mom
	{
	    c.setColor (drkpurple);
	    c.fillArc (x + 10, y + 75, 50, 50, 0, 180);
	    c.fillArc (x + 40, y + 75, 50, 50, 0, 180);
	    c.setColor (hazel);
	    c.fillArc (x + 20, y + 10, 60, 80, 180, 180);
	    c.fillArc (x + 20, y + 20, 60, 60, 0, 180);
	    int[] x1 = {x + 25, x + 15, x + 35};
	    int[] x2 = {x + 75, x + 85, x + 65};
	    int[] y1 = {y + 35, y + 10, y + 30};
	    c.fillPolygon (x1, y1, 3);
	    c.fillPolygon (x2, y1, 3);
	    c.setColor (Color.black);
	    c.drawArc (x, y + 55, 100, 100, 80, 20);
	    c.fillOval (x + 45, y + 60, 10, 5);

	    if (ex == 1) //happy
	    {
		c.setColor (Color.white);
		c.fillOval (x + 28, y + 40, 18, 8);
		c.fillOval (x + 54, y + 40, 18, 8);
		c.setColor (Color.black);
		c.fillOval (x + 34, y + 41, 6, 6);
		c.fillOval (x + 59, y + 41, 6, 6);
		c.drawArc (x, y, 100, 80, 250, 40);
	    }
	    if (ex == 2) //unsure
	    {
		c.setColor (Color.white);
		c.fillOval (x + 28, y + 40, 18, 8);
		c.fillOval (x + 54, y + 40, 18, 8);
		c.setColor (Color.black);
		c.fillOval (x + 37, y + 41, 6, 6);
		c.fillOval (x + 62, y + 41, 6, 6);
		c.drawLine (x + 40, y + 70, x + 60, y + 70);
		c.setColor (bronze);
		c.drawLine (x + 28, y + 38, x + 46, y + 38);
		c.drawLine (x + 72, y + 38, x + 54, y + 38);
	    }
	    if (ex == 5) //disapointed
	    {
		c.setColor (Color.white);
		c.fillOval (x + 28, y + 40, 18, 8);
		c.fillOval (x + 54, y + 40, 18, 8);
		c.setColor (Color.black);
		c.fillOval (x + 34, y + 41, 6, 6);
		c.fillOval (x + 59, y + 41, 6, 6);
		c.drawLine (x + 40, y + 70, x + 60, y + 70);
		c.setColor (bronze);
		c.drawLine (x + 28, y + 37, x + 46, y + 34);
		c.drawLine (x + 72, y + 37, x + 54, y + 34);
	    }
	}

	if (who == 11) //dad
	{
	    c.setColor (bronze);
	    c.fillArc (x + 5, y + 70, 30, 60, 0, 180);
	    c.fillArc (x + 65, y + 70, 30, 60, 0, 180);
	    c.fillRect (x + 30, y + 60, 40, 40);
	    c.setColor (hazel);
	    c.fillArc (x + 10, y + 20, 80, 80, 0, 180);
	    c.fillArc (x + 10, y + 30, 80, 60, 180, 180);
	    c.fillArc (x + 20, y + 25, 60, 70, 180, 180);
	    int[] x1 = {x + 15, x, x + 25};
	    int[] x2 = {x + 85, x + 100, x + 75};
	    int[] y1 = {y + 40, y + 30, y + 35};
	    c.fillPolygon (x1, y1, 3);
	    c.fillPolygon (x2, y1, 3);
	    c.setColor (Color.black);
	    c.drawArc (x, y + 55, 100, 100, 70, 40);
	    c.fillOval (x + 42, y + 60, 16, 8);

	    if (ex == 0) //expressionless
	    {
		c.setColor (Color.white);
		c.fillOval (x + 25, y + 40, 18, 8);
		c.fillOval (x + 57, y + 40, 18, 8);
		c.setColor (Color.black);
		c.fillOval (x + 31, y + 41, 6, 6);
		c.fillOval (x + 63, y + 41, 6, 6);
		c.drawLine (x + 40, y + 80, x + 60, y + 80);
		c.setColor (bronze);
		c.drawLine (x + 25, y + 38, x + 43, y + 38);
		c.drawLine (x + 57, y + 38, x + 75, y + 38);
	    }
	    if (ex == 3) //angry
	    {
		c.setColor (Color.white);
		c.fillOval (x + 25, y + 40, 18, 8);
		c.fillOval (x + 57, y + 40, 18, 8);
		c.setColor (Color.black);
		c.fillOval (x + 33, y + 41, 6, 6);
		c.fillOval (x + 65, y + 41, 6, 6);
		c.drawLine (x + 40, y + 80, x + 60, y + 80);
		c.setColor (bronze);
		c.drawLine (x + 25, y + 35, x + 43, y + 38);
		c.drawLine (x + 57, y + 38, x + 75, y + 35);
	    }
	}

	if (who == 12) //wyvern
	{
	    c.setColor (fur);
	    c.fillArc (x + 10, y + 40, 80, 120, 0, 180);
	    c.setColor (wChest);
	    c.fillArc (x + 20, y + 50, 60, 100, 0, 180);
	    c.setColor (fur);
	    int[] x1 = {x + 12, x + 50, x + 88, x + 60, x + 40};
	    int[] y1 = {y + 16, y + 26, y + 16, y + 80, y + 80};
	    c.fillPolygon (x1, y1, 5);
	    c.fillOval (x + 15, y + 20, 70, 55);
	    c.fillRect (x + 30, y + 50, 40, 30);
	    c.setColor (Color.black);
	    c.drawArc (x + 30, y + 50, 15, 15, 0, 180);
	    c.drawArc (x + 55, y + 50, 15, 15, 0, 180);
	    c.drawLine (x + 45, y + 58, x + 55, y + 58);

	    if (ex == 0) //expressionless
	    {
		c.setColor (Color.white);
		c.fillOval (x + 25, y + 37, 15, 6);
		c.fillOval (x + 60, y + 37, 15, 6);
		c.setColor (Color.black);
		c.fillOval (x + 30, y + 38, 5, 5);
		c.fillOval (x + 65, y + 38, 5, 5);
		c.drawLine (x + 40, y + 70, x + 60, y + 70);
		c.setColor (Color.gray);
		c.drawLine (x + 25, y + 32, x + 40, y + 32);
		c.drawLine (x + 60, y + 32, x + 75, y + 32);
	    }

	    if (ex == 5) //disapointed
	    {
		c.setColor (Color.white);
		c.fillOval (x + 25, y + 36, 15, 8);
		c.fillOval (x + 60, y + 36, 15, 8);
		c.setColor (Color.black);
		c.fillOval (x + 30, y + 38, 5, 5);
		c.fillOval (x + 65, y + 38, 5, 5);
		c.drawLine (x + 40, y + 75, x + 60, y + 75);
		c.setColor (Color.gray);
		c.drawLine (x + 25, y + 35, x + 40, y + 35);
		c.drawLine (x + 60, y + 35, x + 75, y + 35);
	    }
	}

	if (who == 13) //Goblin
	{
	    c.setColor (purple);
	    c.fillArc (x + 10, y + 70, 80, 60, 0, 180);
	    c.setColor (scale);
	    c.fillArc (x + 20, y + 25, 60, 50, 0, 180);
	    c.fillArc (x + 20, y + 10, 60, 80, 180, 180);
	    int[] x1 = {x + 10, x + 50, x + 90, x + 70, x + 30};
	    int[] y1 = {y + 20, y + 50, y + 20, y + 80, y + 80};
	    c.fillPolygon (x1, y1, 5);
	    c.setColor (Color.black);
	    c.drawLine (x + 45, y + 60, x + 50, y + 54);
	    c.drawLine (x + 55, y + 60, x + 50, y + 54);

	    if (ex == 0) //expressionless
	    {
		c.setColor (Color.white);
		c.fillOval (x + 30, y + 45, 14, 7);
		c.fillOval (x + 56, y + 45, 14, 7);
		c.setColor (Color.black);
		c.fillOval (x + 34, y + 45, 6, 6);
		c.fillOval (x + 60, y + 45, 6, 6);
		c.drawLine (x + 40, y + 75, x + 60, y + 75);
	    }

	    if (ex == 1) //happy
	    {
		c.setColor (Color.white);
		c.fillOval (x + 30, y + 45, 14, 7);
		c.fillOval (x + 56, y + 45, 14, 7);
		c.setColor (Color.black);
		c.fillOval (x + 34, y + 45, 6, 6);
		c.fillOval (x + 60, y + 45, 6, 6);
		c.drawArc (x + 10, y + 20, 80, 60, 250, 40);
	    }

	    if (ex == 3) //angry
	    {
		c.setColor (Color.white);
		c.fillOval (x + 30, y + 45, 14, 7);
		c.fillOval (x + 56, y + 45, 14, 7);
		c.setColor (Color.black);
		c.fillOval (x + 34, y + 45, 6, 6);
		c.fillOval (x + 60, y + 45, 6, 6);
		c.setColor (dread);
		c.drawLine (x + 30, y + 38, x + 44, y + 43);
		c.drawLine (x + 56, y + 43, x + 70, y + 38);
		c.setColor (Color.black);
		c.drawArc (x + 10, y + 75, 80, 60, 70, 40);
	    }

	    if (ex == 5) //disapointed
	    {
		c.setColor (Color.white);
		c.fillOval (x + 30, y + 45, 14, 7);
		c.fillOval (x + 56, y + 45, 14, 7);
		c.setColor (Color.black);
		c.fillOval (x + 34, y + 45, 6, 6);
		c.fillOval (x + 60, y + 45, 6, 6);
		c.setColor (dread);
		c.drawLine (x + 30, y + 43, x + 44, y + 43);
		c.drawLine (x + 56, y + 43, x + 70, y + 43);
		c.setColor (Color.black);
		c.drawLine (x + 40, y + 80, x + 55, y + 80);
	    }
	}

	if (who == 14) //dryad
	{
	    c.setColor (fur);
	    c.fillArc (x + 15, y + 80, 70, 40, 0, 180);
	    c.setColor (hazel);
	    c.fillOval (x + 20, y + 20, 60, 70);
	    c.fillOval (x + 16, y + 35, 10, 15);
	    c.fillOval (x + 74, y + 35, 10, 15);
	    c.setColor (brown);
	    int[] x1 = {x + 15, x + 23, x + 40, x + 47, x + 60, x + 77, x + 85, x + 62, x + 50, x + 38};
	    int[] y1 = {y + 55, y + 33, y + 17, y + 12, y + 17, y + 33, y + 55, y + 40, y + 35, y + 40};
	    c.fillPolygon (x1, y1, 10);
	    c.setColor (Color.black);
	    c.drawLine (x + 45, y + 65, x + 48, y + 60);
	    c.drawLine (x + 55, y + 65, x + 52, y + 60);

	    if (ex == 0) //expressionless
	    {
		c.setColor (Color.white);
		c.fillOval (x + 32, y + 47, 13, 7);
		c.fillOval (x + 55, y + 47, 13, 7);
		c.setColor (Color.black);
		c.fillOval (x + 36, y + 48, 5, 5);
		c.fillOval (x + 59, y + 48, 5, 5);
		c.drawLine (x + 40, y + 75, x + 60, y + 75);
	    }

	    if (ex == 1) //happy
	    {
		c.setColor (Color.white);
		c.fillOval (x + 32, y + 47, 13, 7);
		c.fillOval (x + 55, y + 47, 13, 7);
		c.setColor (Color.black);
		c.fillOval (x + 36, y + 48, 5, 5);
		c.fillOval (x + 59, y + 48, 5, 5);
		c.drawArc (x + 15, y + 35, 70, 50, 250, 40);
	    }

	    if (ex == 2) //unsure
	    {
		c.setColor (Color.white);
		c.fillOval (x + 32, y + 47, 13, 7);
		c.fillOval (x + 55, y + 47, 13, 7);
		c.setColor (Color.black);
		c.fillOval (x + 36, y + 48, 5, 5);
		c.fillOval (x + 59, y + 48, 5, 5);
		c.drawLine (x + 32, y + 46, x + 45, y + 46);
		c.drawLine (x + 68, y + 46, x + 55, y + 46);
		c.drawLine (x + 40, y + 71, x + 60, y + 71);
	    }

	    if (ex == 7) //conceerned
	    {
		c.setColor (Color.white);
		c.fillOval (x + 32, y + 47, 13, 7);
		c.fillOval (x + 55, y + 47, 13, 7);
		c.setColor (Color.black);
		c.fillOval (x + 36, y + 48, 5, 5);
		c.fillOval (x + 59, y + 48, 5, 5);
		c.drawLine (x + 32, y + 46, x + 45, y + 42);
		c.drawLine (x + 68, y + 44, x + 55, y + 40);
		c.drawLine (x + 40, y + 79, x + 60, y + 75);
	    }
	}
    }
}
