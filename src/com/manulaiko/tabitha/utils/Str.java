package com.manulaiko.tabitha.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String helpers.
 * ===============
 *
 * Contains helpers for string processing.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
public class Str
{
    /**
     * Returns given string as snake_case.
     *
     * @param string String to parse.
     *
     * @return `string` as snake_case.
     */
    public static String snakeCase(String string)
    {
        List<String> array = new ArrayList<>();

        if(string.contains(" ")) {
            String[] split = string.split(" ");

            for(String s : split) {
                array.add(s);
            }

            return Str.snakeCase(array);
        }

        Pattern regexp  = Pattern.compile("/!([A-Z][A-Z0-9]*(?=$|[A-Z][a-z0-9])|[A-Za-z][a-z0-9]+)!/");
        Matcher matches = regexp.matcher(string);

        if(!matches.matches()) {
            return string;
        }

        while(matches.find()) {
            array.add(matches.group());
        }

        return Str.snakeCase(array);
    }

    /**
     * Returns given array of strings as snake_case.
     *
     * @param string String to parse.
     *
     * @return `string` as snake_case.
     */
    public static String snakeCase(Iterable<String> string)
    {
        List<String> array = new ArrayList<>();

        for(String s : string) {
            array.add(Str.toLowerCaseFirst(s));
        }

        return Str.implode(array, "_");
    }

    /**
     * Returns a string with given char in lower case.
     *
     * @param string String to parse.
     * @param index  Char index.
     *
     * @return `string` with char at `index` in lower case.
     */
    public static String toLowerCase(String string, int index)
    {
        if(
            string.length() == 0 ||
            index >= string.length()
        ) {
            return string;
        }
        char[] c = string.toCharArray();
        c[index] = Character.toLowerCase(c[index]);

        return new String(c);
    }

    /**
     * Returns a string with first char in lower case.
     *
     * @param string String to parse.
     *
     * @return `string` with first char in lower case
     */
    public static String toLowerCaseFirst(String string)
    {
        return Str.toLowerCase(string, 0);
    }

    /**
     * Joins an array with a string.
     *
     * @param array Array to implode.
     * @param glue  String to use to join each element.
     *
     * @return Joined array to string.
     */
    public static String implode(Iterable<String> array, String glue)
    {
        String str = "";

        for(String value : array) {
            str += glue + value;
        }

        str = str.substring(glue.length(), str.length());

        return str;
    }

    /**
     * Checks whether a string is an IP or not.
     *
     * @param ip IP to check.
     *
     * @return `true` if `ip` is a valid IPv4, `false` if not.
     */
    public static boolean isIP(String ip)
    {
        Pattern p = Pattern.compile(
                "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."  +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."  +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$"
        );

        return p.matcher(ip).matches();
    }
}
