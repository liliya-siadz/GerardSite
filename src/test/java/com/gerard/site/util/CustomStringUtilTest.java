package com.gerard.site.util;

import com.gerard.site.localization.Language;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.*;

public class CustomStringUtilTest {

    @DataProvider(name = "dataProviderTestSplitByRegex")
    public Object[][] dataProviderTestSplitByRegex() {
        return new Object[][]{
                {"en, en-US, en-cockney, i-cherokee, x-pig-latin", ",", true,
                        new String[]{"en", "en-US", "en-cockney", "i-cherokee", "x-pig-latin"}},
                {"a1da1d1d1da1d", "1", false,
                        new String[]{"a", "da", "d", "d", "da", "d"}},
                {"asdwddd3d", "1", false,
                        new String[]{"asdwddd3d"}}};
    }

    @Test(dataProvider = "dataProviderTestSplitByRegex")
    public void testSplitByRegex(String row, String regex, boolean cleanWhiteSpaces,
                                 String[] expected) {
        String[] actual = CustomStringUtil.splitByRegex(row, regex, cleanWhiteSpaces);
        assertEquals(actual, expected);
    }

    @Test
    public void testDistinctRowsEnumMatcher() {
        Set<Language> actual = CustomStringUtil.getMatchedEnumValuesSet(
                new String[]{"en", "ru", "ru", ""}, Language.values());
        Set<Language> expected = Set.of(Language.EN, Language.RU);
        assertEquals(actual, expected);
    }
}

