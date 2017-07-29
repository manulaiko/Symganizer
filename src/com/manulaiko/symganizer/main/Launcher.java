package com.manulaiko.symganizer.main;

import com.manulaiko.tabitha.Console;
import com.manulaiko.tabitha.utils.ArgumentParser;

/**
 * Launcher class.
 * ===============
 *
 * Application's entry point.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class Launcher
{
    /**
     * Application version.
     */
    public static final String VERSION = "0.0.0";

    /**
     * Main method.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args)
    {
        Launcher._parseArguments(args);

        Console.println("Symganizer v"+ Launcher.VERSION +" by Manulaiko.");
        Console.println(Console.LINE_EQ);
    }

    /**
     * Parses command line arguments.
     *
     * @param args Command line arguments.
     */
    private static void _parseArguments(String[] args)
    {
        ArgumentParser ap = new ArgumentParser(args);

        ap.parse();
    }
}
