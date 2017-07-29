package com.manulaiko.symganizer.main;

import java.io.File;

import com.manulaiko.tabitha.Console;
import com.manulaiko.tabitha.utils.ArgumentParser;

import com.manulaiko.symganizer.main.arguments.Debug;
import com.manulaiko.symganizer.main.arguments.Help;
import com.manulaiko.symganizer.main.arguments.Library;

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

        Launcher.start();
    }

    /**
     * Parses command line arguments.
     *
     * @param args Command line arguments.
     */
    private static void _parseArguments(String[] args)
    {
        ArgumentParser ap = new ArgumentParser(args);

        ap.add(new Debug());
        ap.add(new Library());
        ap.add(new Help());

        ap.parse();
    }

    /**
     * Starts the application.
     */
    public static void start()
    {
        if(Settings.library.getPath().equals(".")) {
            Launcher._askForLibraryPath();
        }
    }

    /**
     * Asks for the path to the library.
     */
    private static void _askForLibraryPath()
    {
        Console.println("Current library path is `"+ Settings.library.getAbsolutePath() +"`");
        Console.print("Do you want to change it? (yes/no): ");

        if(!Console.readBoolean()) {
            return;
        }

        Console.print("Enter path to the library: ");
        String library = Console.readLine();

        if(Launcher.checkLibraryPath(library)) {
            Settings.library = new File(library);
        }

        Console.println("Path to library: `"+ Settings.library.getAbsolutePath() +"`");
        Console.print("Do you want to use this path as the library? (yes/no): ");

        if(Console.readBoolean()) {
            Launcher._askForLibraryPath();
        }
    }

    /**
     * Checks if the given path is a valid library path.
     *
     * @param path Path to library.
     *
     * @return Whether `path` is a valid library path or not.
     */
    public static boolean checkLibraryPath(String path)
    {
        File library = new File(path);
        if(!library.exists()) {
            Console.println("Library path does not exist!");

            return false;
        }
        if(!library.isDirectory()) {
            Console.println("Library path is not a directory!");

            return false;
        }

        return true;
    }
}
