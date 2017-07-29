package com.manulaiko.symganizer.main;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.manulaiko.tabitha.Console;

/**
 * Container creator class.
 * ========================
 *
 * Creates the directories where the symlinks will be stored.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class ContainerCreator
{
    /**
     * Containers created.
     */
    private Map<String, File> _containers = new HashMap<>();

    /**
     * Starts the creator.
     *
     * @return Map with created containers.
     */
    public Map<String, File> start()
    {
        do {
            this._askForContainer();
        } while(this._askForMorContainers());

        return this._containers;
    }

    /**
     * Asks for containers to the user.
     */
    private void _askForContainer()
    {
        Console.print("Enter the path to the directory (will be created if not exists): ");
        File path = new File(Console.readLine());

        if(!this._isValidPath(path)) {
            return;
        }

        Console.print("Now enter a short name to identify it later when creating the symlinks: ");
        String name = Console.readLine();

        if(this._containers.containsKey(name)) {
            Console.println("`"+ name +"` already exists and points to `"+ this._containers.get(name).getAbsolutePath() +"`.");
            Console.print("Do you want to overwrite it? (yes/no): ");

            if(!Console.readBoolean()) {
                return;
            }
        }

        this._containers.put(name, path);
        Console.debug("New container created: `"+ name +"` -> `"+ path.getAbsolutePath() +"`!");
    }

    /**
     * Checks whether the path is a valid path or not.
     *
     * @param path Path to check.
     *
     * @return Whether path is a valid path or not.
     */
    private boolean _isValidPath(File path)
    {
        if(
            !path.exists() &&
            !path.mkdirs()
        ) {
            Console.println("Couldn't create `"+ path.getAbsolutePath() +"`!");
            Console.println("Check permissions or whatever you think it can be.");

            return false;
        }

        if(path.isFile()) {
            Console.println("`"+ path.getAbsolutePath() +"` is a file!");
            Console.println("You need to specify a directory to save the symlinks to, if it doesn't exist, it will be created.");

            return false;
        }

        return true;
    }

    /**
     * Asks the user if he wants to create more containers.
     *
     * @return Whether the user wants to create more containers or not.
     */
    private boolean _askForMorContainers()
    {
        Console.println("There are currently "+ this._containers.size() +" directories.");
        Console.print("Do you want to add more? (yes/no): ");

        return Console.readBoolean();
    }
}
