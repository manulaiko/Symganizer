package com.manulaiko.symganizer.main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import com.manulaiko.tabitha.Console;

/**
 * File processor class.
 * =====================
 *
 * Processes the library entries.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class EntryProcessor
{
    /**
     * Entry to process.
     */
    private File _entry;

    /**
     * Containers.
     */
    private Map<String, File> _containers;

    /**
     * Constructor.
     *
     * @param entry Entry to process.
     */
    public EntryProcessor(File entry, Map<String, File> containers)
    {
        this._entry = entry;
        this._containers = containers;
    }

    /**
     * Processes the entry.
     */
    public void process()
    {
        Console.println("Now I'm going to make the symlinks for `"+ this._entry.getAbsolutePath() +"`.");
        do {
            this._createSymlinks();
        } while(this._askForMoreSymlinks());
    }

    /**
     * Creates the symlinks of an entry to a container.
     */
    private void _createSymlinks()
    {
        Console.print("Enter the identifier of the directory: ");
        String name = Console.readLine();

        if(!this._containers.containsKey(name)) {
            Console.println("`"+ name +"` is not a pre-configured directory!");

            return;
        }

        File path = this._askForTarget(this._containers.get(name));

        try {
            Path target = Paths.get(this._entry.getAbsolutePath());
            Path link  = Paths.get(path.getAbsolutePath()).normalize().relativize(target);

            Files.createSymbolicLink(link, target);
        } catch(IOException e) {
            Console.println("Couldn't create symlink `"+ path.getAbsolutePath() +"` -> `"+ this._entry.getAbsolutePath() +"`!");
            Console.print(e);
        } catch(UnsupportedOperationException e) {
            Console.println("It seems your filesystem doesn't support symlinks.");
            Console.println("No need to continue, bye!");

            System.exit(0);
        }

        Console.println("Created symlink `"+ path.getAbsolutePath() +"` -> `"+ this._entry.getAbsolutePath() +"`");
    }

    /**
     * Ask the user for the symlink name.
     *
     * @param container Path to the symlink container.
     *
     * @return Full path to the symlink.
     */
    private File _askForTarget(File container)
    {
        Console.print("Enter the name of the symlink (default `"+ this._entry.getName() +"`): ");
        String name = Console.readLine();

        File path = new File(container.getAbsolutePath() + File.separatorChar + name);

        Console.println("Symlink will be created at `"+ path.getAbsolutePath() +"`");
        Console.print("Do you want to change it? (yes/no): ");
        if(Console.readBoolean()) {
            return this._askForTarget(container);
        }

        return path;
    }

    /**
     * Asks the user if he wants to keep creating symlinks.
     *
     * @return Whether the user wants to create more symlinks or not.
     */
    private boolean _askForMoreSymlinks()
    {
        Console.print("Do you want to create another symlink? (yes/no): ");

        return Console.readBoolean();
    }
}
