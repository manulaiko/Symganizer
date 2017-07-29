package com.manulaiko.symganizer.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
     * Scan options.
     */
    private ScanOptions _scanOptions = new ScanOptions();

    /**
     * Library entries.
     */
    private List<File> _entries = new ArrayList<>();

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
        this._scanLibrary();
    }

    /**
     * Scans the library.
     */
    private void _scanLibrary()
    {
        Console.println("I'm going to scan the library to get a list of the files/directories of it.");

        this._scanOptions.askForSettings();

        Console.println("I'm going to start scanning `"+ this._library.getAbsolutePath() +"`, this will take a while...");
        this._doScan(this._library);
    }

    /**
     * Scans the library.
     *
     * @param library Library to scan.
     */
    private void _doScan(File library)
    {
        Console.debug("Scanning `"+ library.getAbsolutePath() +"` for directories...");

        File[] files = library.listFiles();
        if(files == null) {
            Console.debug("Nothing found in `"+ library.getAbsolutePath() +"`");

            return;
        }

        for(File f : files) {
            if(
                f.getName().equalsIgnoreCase(".")  ||
                f.getName().equalsIgnoreCase("..")
            ) {
                continue;
            }

            if(
                (f.isDirectory() && this._scanOptions.directories) ||
                (f.isFile()      && this._scanOptions.files)
            ) {
                this._entries.add(f);
                Console.debug("Found `"+ f.getAbsolutePath() +"`!");
            }

            if(
                this._scanOptions.recursively &&
                f.isDirectory()
            ) {
                this._doScan(f);
            }
        }

        Console.debug("Found "+ files.length +" entries in `"+ library.getAbsolutePath() +"`");
    }

    /**
     * Scan options class.
     * ===================
     *
     * Used to contain scan settings.
     *
     * @author Manulaiko <manulaiko@gmail.com>
     */
    private class ScanOptions
    {
        /**
         * Whether we're scanning directories or not.
         */
        public boolean directories = true;

        /**
         * Whether we're scanning files or not.
         */
        public boolean files = true;

        /**
         * Whether we're going to scan recursively or not.
         */
        public boolean recursively = true;

        /**
         * Asks and sets the scan options.
         */
        public void askForSettings()
        {
            Console.print("What should I search for? (directories/files/both): ");
            String option = Console.readLine();

            if(option.startsWith("d")) {
                this.files = false;
            }
            if(option.startsWith("f")) {
                this.directories = false;
            }

            if(this.directories) {
                Console.print("Should I search recursively in directories? (yes/no): ");

                this.recursively = Console.readBoolean();
            }

            Console.print("Do you want to change any settings? (yes/no): ");

            if(Console.readBoolean()) {
                this.askForSettings();
            }
        }
    }
}
