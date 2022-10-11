package battleSystem;

public class combat
{
    fighter p1;
    fighter p2;

    public combat (fighter A, fighter B)
    {
	p1 = A;
	p2 = B;
    }


    public int [] fight (screen dis)
    {
	dis.graphic (p1, p2);
	while (p1.getHealth () > 0 && p2.getHealth () > 0)
	{
	    p1.turn ();
	    p2.turn ();

	    dis.graphic (p1, p2);

	    if (p1.getOrder () > p2.getOrder ())
	    {
		p1.enact ();
		dis.graphic (p1, p2);
		p2.enact ();
	    }
	    else
	    {
		p2.enact ();
		dis.graphic (p1, p2);
		p1.enact ();
	    }
	    dis.graphic (p1, p2);

	    p1.elapse ();
	    p2.elapse ();

	    dis.sPrint ("******************");

	    dis.graphic (p1, p2);
	}

	if (p1.getHealth () <= 0 && p2.getHealth () <= 0)
	{
	    dis.sPrint ("The battle is a tie");
	}
	else if (p1.getHealth () <= 0)
	{
	    dis.sPrint ("The winner is " + p2.getName ());
	}
	else
	{
	    dis.sPrint ("The winner is " + p1.getName ());
	}
	dis.c.getChar();
	int[] ret = {p1.getHealth (), p1.getMana () };
	return ret;
    }
}
