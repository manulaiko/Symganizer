package com.manulaiko.symganizer.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.manulaiko.tabitha.Console;

/**
 * Library scanner class.
 * ======================
 *
 * Scans the library.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class LibraryScanner
{
    /**
     * Library entries.
     */
    private List<File> _entries = new ArrayList<>();

    /**
     * Scan options.
     */
    private ScanOptions _scanOptions = new ScanOptions();

    /**
     * Scans the library.
     *
     * @param library Library to scan.
     *
     * @return List with library entries.
     */
    public List<File> scan(File library)
    {
        Console.println("I'm going to scan the library to get a list of the files/directories of it.");

        this._scanOptions.askForSettings();

        Console.println("I'm going to start scanning `"+ library.getAbsolutePath() +"`, this will take a while...");
        this._doScan(library);

        return this._entries;
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
            this._processFile(f);
        }

        Console.debug("Found "+ this._entries.size() +" entries in `"+ library.getAbsolutePath() +"`");
    }

    /**
     * Processes a file from the library.
     *
     * @param file File to process.
     */
    private void _processFile(File file)
    {

        if(
            file.getName().equalsIgnoreCase(".")  ||
            file.getName().equalsIgnoreCase("..")
        ) {
            return;
        }

        if(
            (file.isDirectory() && this._scanOptions.directories) ||
            (file.isFile()      && this._scanOptions.files)
        ) {
            this._entries.add(file);
            Console.debug("Found `"+ file.getAbsolutePath() +"`!");
        }

        if(
            this._scanOptions.recursively &&
            file.isDirectory()
        ) {
            this._doScan(file);
        }
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
