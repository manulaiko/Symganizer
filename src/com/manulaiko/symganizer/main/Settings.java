package com.manulaiko.symganizer.main;

import java.io.File;

/**
 * Settings class.
 * ===============
 *
 * Contains settings that can be overridden at runtime.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class Settings
{
    /**
     * Whether we're running in debug mode or not.
     */
    public static boolean debug = false;

    /**
     * Path to the library.
     */
    public static File library = new File("./");
}
