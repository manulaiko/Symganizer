package com.manulaiko.symganizer.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
        LibraryScanner scanner = new LibraryScanner();
        this._entries = scanner.scan(this._library);
    }
}
