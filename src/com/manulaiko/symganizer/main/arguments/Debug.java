package com.manulaiko.symganizer.main.arguments;

import com.manulaiko.tabitha.Console;
import com.manulaiko.tabitha.utils.Argument;

import com.manulaiko.symganizer.main.Settings;

/**
 * Debug argument.
 * ===============
 *
 * Enables debug mode.
 *
 * Usage:
 *
 * ```
 * java -jar bin/synganizer.jar -d
 * ```
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class Debug extends Argument
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
                arg.equalsIgnoreCase("-d") ||
                arg.equalsIgnoreCase("--debug")
        );
    }

    /**
     * Handles the argument.
     */
    @Override
    public void handle()
    {
        Settings.debug = true;
        Console.debug = true;
    }
}
