package com.manulaiko.symganizer.main.arguments;

import com.manulaiko.tabitha.Console;
import com.manulaiko.tabitha.utils.Argument;

import com.manulaiko.symganizer.main.Launcher;

/**
 * Help argument.
 * ==============
 *
 * Prints help page and exits.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class Help extends Argument
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
                arg.equalsIgnoreCase("-h") ||
                arg.equalsIgnoreCase("--help")
        );
    }

    /**
     * Handles the argument.
     */
    @Override
    public void handle()
    {
        Console.println(
                "Symganizer v"+ Launcher.VERSION +" by Manulaiko\n",
                Console.LINE_EQ +"\n",
                "Throw all your shit to a folder and organize it with symlinks 'cuz yolo.\n",
                "\n",
                "Usage:\n",
                "    java -jar bin/symganizer (args)\n",
                "\n",
                "Available arguments:\n",
                "    -h      --help         Displays this page and exits.\n",
                "    -d      --debug        Enables debug mode.\n",
                "    -l=path --library=path Sets the path to the library.\n",
                "\n",
                "GitHub repo: https://github.com/manulaiko/Symganizer\n"
        );

        System.exit(0);
    }
}
