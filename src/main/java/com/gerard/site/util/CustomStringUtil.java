package com.gerard.site.util;

import java.util.*;
import java.util.stream.Collectors;

public class CustomStringUtil {
    static final String WHITE_SPACE_SYMBOLS_REGEX = "\\s";
    static final String EMPTY_STRING = "";

    private CustomStringUtil(){
    }

    public static String[] splitByRegex(String row,
                                        String regex, boolean cleanWhiteSpaces) {
        String tempRow = cleanWhiteSpaces ? cleanAllWhiteSpaces(row) : row;
        String[] result = tempRow.split(regex, 0);
        return result;
    }

    static String cleanAllWhiteSpaces(String row) {
        String result = row.replaceAll(WHITE_SPACE_SYMBOLS_REGEX, EMPTY_STRING);
        return result;
    }

    public static <T extends Enum<T>> List<T> filterMatchedRowsByEnumElements(
            String[] rows, T[] enumValues) {
        List<T> matchedEnumNames = Arrays.stream(enumValues)
                .filter(value-> Arrays.toString(rows).toUpperCase().contains(value.name()))
                .collect(Collectors.toList());
        return matchedEnumNames;
    }

}
