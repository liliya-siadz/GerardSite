package com.gerard.site.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This util services {@code String} objects .
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 */
public class CustomStringUtil {
    private static final String WHITE_SPACE_SYMBOLS_REGEX = "\\s";
    private static final String EMPTY_STRING = "";

    private CustomStringUtil() {
    }

    /**
     * Splits string around matches of the given regular expression
     * and cleans whitespaces if specified flag was set to true,
     * this method calls method {@code split(String,int)} of class
     * String {@link String#split(String, int)} .
     *
     * @param row              row to split
     * @param regex            regex pattern to use for splitting
     * @param cleanWhiteSpaces flag for cleaning whitespaces,
     *                         if true - method will be clean whitespaces while
     *                         splitting,
     *                         if false - otherwise
     * @return the array of strings computed by splitting this string
     * around matches of the given regular expression
     */
    public static String[] splitByRegex(String row,
                                        String regex, boolean cleanWhiteSpaces) {
        String tempRow = cleanWhiteSpaces
                         ? cleanAllWhiteSpaces(row)
                         : row;
        String[] result = tempRow.split(regex, 0);
        return result;
    }

    /**
     * Clean all whitespace from specified row by replacing
     * them to empty string {@link CustomStringUtil#EMPTY_STRING} .
     *
     * @param row row to clean whitespaces
     * @return result of whitespace cleaning
     */
    public static String cleanAllWhiteSpaces(String row) {
        String result = row.replaceAll(WHITE_SPACE_SYMBOLS_REGEX, EMPTY_STRING);
        return result;
    }

    /**
     * Intersects array of rows to array of enum values .
     *
     * @param rows       array of rows to execute intersection with enum values
     * @param enumValues array of enum values
     *                   to intersects with provided array rows
     * @param <T>        type of enum
     * @return returns set of matched enum values
     */
    public static <T extends Enum<T>> Set<T> getMatchedEnumValuesSet(
            String[] rows, T[] enumValues) {
        Set<T> matchedEnumNames = Arrays.stream(enumValues)
                .filter(value -> Arrays.toString(rows).toUpperCase().contains(value.name()))
                .collect(Collectors.toSet());
        return matchedEnumNames;
    }
}
