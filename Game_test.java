// The "Game_test" class.
import java.awt.*;
import java.io.*;
import java.util.*;
import hsa.Console;

public class Game_test
{
    static Console c;           // The output console

    public static void main (String[] args) throws IOException
    {
	c = new Console (35, 187);

	overworld test = new overworld ("farm", "family farm", c);

	test.play ();
    } // main method
} // Game_test class
