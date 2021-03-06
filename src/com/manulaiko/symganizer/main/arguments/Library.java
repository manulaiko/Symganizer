package com.manulaiko.symganizer.main.arguments;

import java.io.File;

import com.manulaiko.tabitha.Console;
import com.manulaiko.tabitha.utils.Argument;

import com.manulaiko.symganizer.main.Launcher;
import com.manulaiko.symganizer.main.Settings;

/**
 * Library argument.
 * =================
 *
 * Sets the path to the library.
 *
 * Example:
 *
 * ```
 * java -jar bin/symganizer.jar -l=/media/Animus/Animus
 * ```
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class Library extends Argument
{
    /**
     * Checks that the argument can be handled
     * by this instance.
     *
     * @param arg Argument.
     *
     * @return `true` if the argument can be handled by this instance, `false` if not.
     */
    @Override
    public boolean canHandle(String arg)
    {
        return (
                arg.equalsIgnoreCase("-l") ||
                arg.equalsIgnoreCase("--library")
        );
    }

    /**
     * Handles the argument.
     */
    @Override
    public void handle()
    {
        if(super.value().isEmpty()) {
            this._printUsage();

            return;
        }

        if(!Launcher.checkLibraryPath(super.value())) {
            this._printUsage();

            return;
        }

        Settings.library = new File(super.value());

        Console.debug("Path to library: `"+ Settings.library.getAbsolutePath() +"`");
    }

    /**
     * Prints argument usage.
     */
    private void _printUsage()
    {
        Console.println("Usage:");
        Console.println("    java -jar bin/symganizer.jar -l=/media/Animus/Animus");
        Console.println();
        Console.println("Using `"+ Settings.library.getAbsolutePath() +"` as default library!");
    }
}
