package com.manulaiko.symganizer.main;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.manulaiko.tabitha.Console;

/**
 * Symganizer class.
 * =================
 *
 * Contains the application logic.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class Symganizer
{
    /**
     * Library to use.
     */
    private File _library;

    /**
     * Constructor.
     *
     * @param library Library to use.
     */
    public Symganizer(File library)
    {
        this._library = library;
    }

    /**
     * Starts the logic for the given library.
     */
    public void start()
    {
        Console.println("I'm going to create the directories where the symlinks will be stored.");
        ContainerCreator containerCreator = new ContainerCreator();
        Map<String, File> containers = containerCreator.start();


        Console.println("Now I'm going to scan the library to get a list of the files/directories of it.");
        LibraryScanner scanner = new LibraryScanner();
        List<File> entries = scanner.scan(this._library);

        entries.forEach((f)->{
            EntryProcessor processor = new EntryProcessor(f, containers);
            processor.process();
        });

        Console.println("Finished processing library `"+ this._library.getAbsolutePath() +"`!");
    }
}
